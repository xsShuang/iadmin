package xyz.iotcode.iadmin.core.base.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.iotcode.iadmin.core.wrapper.QueryCondition;

import java.util.Date;

/**
 * @author Administrator
 */
@Data
public class TimeDTO extends PageDTO {

    @ApiModelProperty(value = "开始时间")
    @QueryCondition(condition = QueryCondition.Condition.GE, field = "create_time")
    private Date beginTime;

    @ApiModelProperty(value = "结束时间")
    @QueryCondition(condition = QueryCondition.Condition.LE, field = "create_time")
    private Date endTime;
}
