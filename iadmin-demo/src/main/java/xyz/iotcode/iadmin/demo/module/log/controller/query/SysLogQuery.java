package xyz.iotcode.iadmin.demo.module.log.controller.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import xyz.iotcode.iadmin.core.base.dto.TimeDTO;
import xyz.iotcode.iadmin.core.wrapper.QueryCondition;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysLog查询对象", description="查询对象")
public class SysLogQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @QueryCondition
    private Integer id;

    @ApiModelProperty(value = "说明")
    @QueryCondition
    private String description;

    @ApiModelProperty(value = "日志类型")
    @QueryCondition
    private String logType;

    @ApiModelProperty(value = "操作方法")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private String method;

    @ApiModelProperty(value = "ip")
    @QueryCondition
    private String requestIp;

    @ApiModelProperty(value = "用户名")
    @QueryCondition
    private String username;

    @ApiModelProperty(value = "日志级别（1一般，2敏感，3危险）")
    @QueryCondition
    private Integer logLevel;

    @ApiModelProperty(value = "时间排序")
    @QueryCondition(condition = QueryCondition.Condition.DEFAULT, sort = QueryCondition.Sort.AUTO, field = "create_time")
    private int timeSort = 1;
}
