package xyz.iotcode.iadmin.demo.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import xyz.iotcode.iadmin.common.exception.MyRuntimeException;
import xyz.iotcode.iadmin.demo.system.controller.query.SysRoleQuery;
import xyz.iotcode.iadmin.demo.system.entity.SysRole;
import xyz.iotcode.iadmin.demo.system.entity.SysRolePermission;
import xyz.iotcode.iadmin.demo.system.mapper.SysRoleMapper;
import xyz.iotcode.iadmin.demo.system.service.SysRolePermissionService;
import xyz.iotcode.iadmin.demo.system.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.iotcode.iadmin.core.wrapper.WrapperFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-23
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRolePermissionService sysRolePermissionService;
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public IPage<SysRole> ipage(SysRoleQuery query) {
        return this.page(query.createPage(), new WrapperFactory<SysRole>().create(query));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean isave(SysRole param) {
        SysRole one = this.getOne(new LambdaQueryWrapper<SysRole>().eq(SysRole::getLabel, param.getLabel()));
        if (one!=null){
            throw new MyRuntimeException("角色标识已存在");
        }
        this.save(param);
        if (CollectionUtil.isNotEmpty(param.getPermissionIdList())){
            sysRolePermissionService.removeByRoleId(param.getRoleId());
            List<SysRolePermission> sysRolePermissionList = new ArrayList<>();
            for (Integer integer : param.getPermissionIdList()) {
                sysRolePermissionList.add(new SysRolePermission().setRoleId(param.getRoleId()).setPermissionId(integer));
            }
            sysRolePermissionService.saveBatch(sysRolePermissionList);
        }
        return true;
    }

    @Override
    public boolean iupdate(SysRole param) {
        return this.updateById(param);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean iremove(List<Integer> list) {
        for (Integer integer : list) {
            this.iremove(integer);
        }
        return true;
    }

    @Override
    public SysRole igetById(Integer id) {
        return this.getById(id);
    }

    @Override
    public boolean iremove(Integer id) {
        return this.removeById(id);
    }

    @Override
    public List<SysRole> getByPermissionId(Integer id) {
        return sysRoleMapper.getByPermissionId(id);
    }
}
