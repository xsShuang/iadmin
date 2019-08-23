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
 * 权限
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_permission")
@ApiModel(value="SysPermission对象", description="权限")
public class SysPermission extends Model<SysPermission> {

    private static final long serialVersionUID=1L;

    @NotNull(message = "不能为空")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotBlank(message = "授权标识不能为空")
    @ApiModelProperty(value = "授权标识")
    @TableField("permission_code")
    private String permissionCode;

    @NotBlank(message = "名称不能为空")
    @ApiModelProperty(value = "名称")
    @TableField("permission_name")
    private String permissionName;

    @ApiModelProperty(value = "模块")
    @TableField("parent_id")
    private Integer parentId;

    @NotNull(message = "排序号不能为空")
    @ApiModelProperty(value = "排序号")
    @TableField("sort")
    private Integer sort;

    @NotNull(message = "创建时间不能为空")
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "url")
    @TableField("url")
    private String url;

    @NotNull(message = "状态不能为空")
    @ApiModelProperty(value = "状态（0不需要验证，1需要登录，2需要验证）")
    @TableField("state")
    private Integer state;

    @ApiModelProperty(value = "请求方式")
    @TableField("request_way")
    private String requestWay;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
