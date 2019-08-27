package xyz.iotcode.iadmin.demo.module.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
@ApiModel(value="SysRole对象", description="角色")
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID=1L;

    @NotNull(message = "角色id不能为空")
    @ApiModelProperty(value = "角色id")
    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

    @NotBlank(message = "角色名称不能为空")
    @ApiModelProperty(value = "角色名称")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty(value = "备注")
    @TableField("comments")
    private String comments;

    @NotNull(message = "创建时间不能为空")
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @NotNull(message = "修改时间不能为空")
    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    private Date updateTime;

    @NotBlank(message = "角色标识不能为空")
    @ApiModelProperty(value = "角色标识")
    @TableField("label")
    private String label;

    @ApiModelProperty(value = "权限id集合")
    @TableField(exist = false)
    private List<Integer> permissionIdList;

    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}