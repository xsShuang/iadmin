package xyz.iotcode.iadmin.demo.system.controller.query;

import xyz.iotcode.iadmin.core.wrapper.QueryCondition;
import xyz.iotcode.iadmin.core.base.dto.PageDTO;
import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value="SysPermission查询对象", description="权限查询对象")
public class SysPermissionQuery extends PageDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @QueryCondition
    private Integer id;


    @ApiModelProperty(value = "授权标识")
    @QueryCondition
    private String permissionCode;


    @ApiModelProperty(value = "名称")
    @QueryCondition
    private String permissionName;


    @ApiModelProperty(value = "模块")
    @QueryCondition
    private Integer parentId;


    @ApiModelProperty(value = "排序号")
    @QueryCondition
    private Integer sort;


    @ApiModelProperty(value = "创建时间")
    @QueryCondition
    private Date createTime;


    @ApiModelProperty(value = "url")
    @QueryCondition
    private String url;


    @ApiModelProperty(value = "状态（0不需要验证，1需要登录，2需要验证）")
    @QueryCondition
    private Integer state;


    @ApiModelProperty(value = "请求方式")
    @QueryCondition
    private String requestWay;

}
