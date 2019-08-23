package xyz.iotcode.iadmin.demo.system.entity;

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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户角色
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user_role")
@ApiModel(value="SysUserRole对象", description="用户角色")
public class SysUserRole extends Model<SysUserRole> {

    private static final long serialVersionUID=1L;

    @NotNull(message = "不能为空")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Integer userId;

    @NotNull(message = "角色id不能为空")
    @ApiModelProperty(value = "角色id")
    @TableField("role_id")
    private Integer roleId;

    @TableField("create_time")
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
