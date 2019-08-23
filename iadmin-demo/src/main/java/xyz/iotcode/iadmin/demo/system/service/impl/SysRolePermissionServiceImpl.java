package xyz.iotcode.iadmin.demo.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import xyz.iotcode.iadmin.common.exception.MyRuntimeException;
import xyz.iotcode.iadmin.demo.system.controller.query.SysRolePermissionQuery;
import xyz.iotcode.iadmin.demo.system.entity.SysRolePermission;
import xyz.iotcode.iadmin.demo.system.mapper.SysRolePermissionMapper;
import xyz.iotcode.iadmin.demo.system.service.SysRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.iotcode.iadmin.core.wrapper.WrapperFactory;

import javax.validation.constraints.NotEmpty;
import java.util.List;

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
}
