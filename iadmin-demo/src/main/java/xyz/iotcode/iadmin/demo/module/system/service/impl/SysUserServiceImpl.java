package xyz.iotcode.iadmin.demo.module.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import xyz.iotcode.iadmin.common.exception.MyRuntimeException;
import xyz.iotcode.iadmin.demo.module.system.controller.query.SysUserQuery;
import xyz.iotcode.iadmin.demo.module.system.entity.SysRolePermission;
import xyz.iotcode.iadmin.demo.module.system.entity.SysUser;
import xyz.iotcode.iadmin.demo.module.system.entity.SysUserRole;
import xyz.iotcode.iadmin.demo.module.system.mapper.SysUserMapper;
import xyz.iotcode.iadmin.demo.module.system.service.SysUserRoleService;
import xyz.iotcode.iadmin.demo.module.system.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.iotcode.iadmin.core.wrapper.WrapperFactory;
import xyz.iotcode.iadmin.demo.security.provider.AuthenticationProviderImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-23
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public IPage<SysUser> ipage(SysUserQuery query) {
        return this.page(query.createPage(), new WrapperFactory<SysUser>().create(query));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean isaveOrUpdate(SysUser param) {
        if (param.getId()==null){
            SysUser one = this.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, param.getUsername()));
            if (one!=null){
                throw new MyRuntimeException("用户名已存在");
            }
            if (StrUtil.isBlank(param.getPassword())){
                param.setPassword("123456");
            }
            param.setPassword(SecureUtil.md5(AuthenticationProviderImpl.BEGIN_SALT+param.getPassword().trim()+AuthenticationProviderImpl.END_SALT));
        }else {
            param.setUsername(null);
            param.setPassword(null);
        }
        this.saveOrUpdate(param);
        if (CollectionUtil.isEmpty(param.getRoleIds())){
            param.setRoleIds(new HashSet<>());
        }
        // 所有用户都必须默认一个“普通用户”角色
        param.getRoleIds().add(2);
        sysUserRoleService.removeByUserId(param.getId());
        List<SysUserRole> sysUserRoles = new ArrayList<>();
        for (Integer integer : param.getRoleIds()) {
            sysUserRoles.add(new SysUserRole().setUserId(param.getId()).setRoleId(integer));
        }
        sysUserRoleService.saveBatch(sysUserRoles);
        return true;
    }

    @Override
    public SysUser igetById(Integer id) {
        return this.getById(id);
    }

    @Override
    public boolean iremove(Integer id) {
        return this.removeById(id);
    }
}
