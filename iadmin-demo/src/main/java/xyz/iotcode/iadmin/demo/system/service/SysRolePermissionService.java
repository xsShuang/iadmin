package xyz.iotcode.iadmin.demo.system.service;

import xyz.iotcode.iadmin.demo.system.entity.SysRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
/**
 * <p>
 * 角色权限 服务类
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-23
 */
public interface SysRolePermissionService extends IService<SysRolePermission> {

    boolean removeByRoleId(Integer roleId);

    boolean saveBatch(List<SysRolePermission> sysRolePermissionList);
}
