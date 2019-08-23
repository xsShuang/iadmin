package xyz.iotcode.iadmin.demo.system.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.iotcode.iadmin.demo.system.entity.SysRolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色权限 Mapper 接口
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-23
 */
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {

    boolean saveBatch(@Param("list") List<SysRolePermission> sysRolePermissions);

}
