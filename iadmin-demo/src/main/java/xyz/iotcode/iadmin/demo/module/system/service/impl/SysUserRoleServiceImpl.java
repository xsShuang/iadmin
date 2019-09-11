package xyz.iotcode.iadmin.demo.module.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import xyz.iotcode.iadmin.demo.module.system.controller.query.SysUserRoleQuery;
import xyz.iotcode.iadmin.demo.module.system.entity.SysRolePermission;
import xyz.iotcode.iadmin.demo.module.system.entity.SysUserRole;
import xyz.iotcode.iadmin.demo.module.system.mapper.SysUserRoleMapper;
import xyz.iotcode.iadmin.demo.module.system.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.iotcode.iadmin.core.wrapper.WrapperFactory;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-23
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper ;

    @Override
    public boolean removeByUserId(Integer userId) {
        return this.remove(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, userId));
    }

    @Override
    public boolean saveBatch(@NotEmpty List<SysUserRole> sysUserRoles) {
        return sysUserRoleMapper.saveBatch(sysUserRoles);
    }
}
