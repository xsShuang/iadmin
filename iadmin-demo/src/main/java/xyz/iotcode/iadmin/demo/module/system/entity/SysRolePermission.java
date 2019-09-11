package xyz.iotcode.iadmin.demo.module.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 角色权限
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role_permission")
@ApiModel(value="SysRolePermission对象", description="角色权限")
public class SysRolePermission extends Model<SysRolePermission> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotNull(message = "角色id不能为空")
    @ApiModelProperty(value = "角色id")
    @TableField("role_id")
    private Integer roleId;

    @NotNull(message = "权限id不能为空")
    @ApiModelProperty(value = "权限id")
    @TableField("permission_id")
    private Integer permissionId;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
