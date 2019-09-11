package xyz.iotcode.iadmin.demo.module.system.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.iotcode.iadmin.demo.module.system.entity.SysRolePermission;
import xyz.iotcode.iadmin.demo.module.system.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户角色 Mapper 接口
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-23
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    boolean saveBatch(@Param("list") List<SysUserRole> sysUserRoles);

}
