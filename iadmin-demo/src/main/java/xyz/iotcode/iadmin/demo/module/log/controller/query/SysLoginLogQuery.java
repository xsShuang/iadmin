package xyz.iotcode.iadmin.demo.module.log.controller.query;

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
 * 
 * </p>
 *
 * @author 谢霜
 * @since 2019-09-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysLoginLog查询对象", description="查询对象")
public class SysLoginLogQuery extends PageDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @QueryCondition
    private Integer id;


    @ApiModelProperty(value = "用户名")
    @QueryCondition
    private String username;


    @ApiModelProperty(value = "登录时间")
    @QueryCondition
    private Date loginTime;


    @ApiModelProperty(value = "登录地址")
    @QueryCondition
    private String address;


    @ApiModelProperty(value = "登录ip")
    @QueryCondition
    private String ip;


    @ApiModelProperty(value = "操作系统")
    @QueryCondition
    private String system;


    @ApiModelProperty(value = "登录浏览器")
    @QueryCondition
    private String browser;


    @ApiModelProperty(value = "创建时间")
    @QueryCondition
    private Date createTime;

}
