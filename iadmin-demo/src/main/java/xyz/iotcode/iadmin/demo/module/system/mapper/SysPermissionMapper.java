package xyz.iotcode.iadmin.demo.module.system.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.iotcode.iadmin.demo.module.system.entity.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.iotcode.iadmin.demo.module.system.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-23
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    List<SysPermission> getByUserId(@Param("id") Integer id);

    List<SysPermission> getByRoleId(@Param("id") Integer id);
}
