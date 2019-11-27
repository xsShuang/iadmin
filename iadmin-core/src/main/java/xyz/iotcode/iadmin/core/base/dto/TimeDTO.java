package xyz.iotcode.iadmin.core.base.dto;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import xyz.iotcode.iadmin.core.wrapper.QueryCondition;

import java.util.Date;

/**
 * @author Administrator
 */
@Data
public class TimeDTO extends PageDTO {

    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @QueryCondition(condition = QueryCondition.Condition.GE, field = "create_time")
    private Date beginTime;

    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @QueryCondition(condition = QueryCondition.Condition.LE, field = "create_time")
    private Date endTime;

    public static void setBeginAndEndTime(TimeDTO dto){
        if (ObjectUtil.isNotNull(dto.getBeginTime())){
            dto.setBeginTime(DateUtil.beginOfDay(dto.getBeginTime()));
        }
        if (ObjectUtil.isNotNull(dto.getEndTime())){
            dto.setEndTime(DateUtil.endOfDay(dto.getEndTime()));
        }
    }
}
