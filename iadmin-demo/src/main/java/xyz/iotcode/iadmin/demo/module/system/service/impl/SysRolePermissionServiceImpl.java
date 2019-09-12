package xyz.iotcode.iadmin.demo.module.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.iotcode.iadmin.demo.module.system.entity.SysPermission;
import xyz.iotcode.iadmin.demo.module.system.entity.SysRolePermission;
import xyz.iotcode.iadmin.demo.module.system.mapper.SysRolePermissionMapper;
import xyz.iotcode.iadmin.demo.module.system.service.SysRolePermissionService;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色权限 服务实现类
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-23
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public boolean removeByRoleId(Integer roleId) {
        return this.remove(new LambdaQueryWrapper<SysRolePermission>().eq(SysRolePermission::getRoleId, roleId));
    }

    @Override
    public boolean saveBatch(@NotEmpty List<SysRolePermission> sysRolePermissionList) {
        return sysRolePermissionMapper.saveBatch(sysRolePermissionList);
    }

    @Override
    public Set<SysPermission> getByRoleId(Integer roleId) {
        return sysRolePermissionMapper.getByRoleId(roleId);
    }
}
