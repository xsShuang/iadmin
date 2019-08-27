package xyz.iotcode.iadmin.demo.module.system.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.iotcode.iadmin.demo.module.system.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-23
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> getByPermissionId(@Param("id") Integer id);

    List<SysRole> getByUserId(@Param("id") Integer id);
}
