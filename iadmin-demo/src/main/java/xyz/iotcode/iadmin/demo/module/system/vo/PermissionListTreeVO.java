package xyz.iotcode.iadmin.demo.module.system.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.iotcode.iadmin.common.tree.Tree;
import xyz.iotcode.iadmin.common.validated.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author xieshuang
 * @date 2019-11-27 14:40
 */
@Data
@ApiModel("权限列表树结构VO")
public class PermissionListTreeVO extends Tree {

    @NotBlank(message = "授权标识不能为空")
    @ApiModelProperty(value = "授权标识")
    @TableField("permission_code")
    private String permissionCode;

    @NotBlank(message = "名称不能为空")
    @ApiModelProperty(value = "名称")
    @TableField("permission_name")
    private String permissionName;

    @NotNull(message = "排序号不能为空")
    @ApiModelProperty(value = "排序号")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "url")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "请求方式")
    @TableField("request_way")
    private String requestWay;

    @NotNull(message = "权限类型不能为空")
    @ApiModelProperty(value = "1路由，2操作")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "组件地址")
    @TableField("component_url")
    private String componentUrl;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    private Date updateTime;
}
