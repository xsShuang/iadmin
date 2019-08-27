package xyz.iotcode.iadmin.demo.module.log.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

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
@TableName("sys_log")
@ApiModel(value="SysLog对象", description="")
public class SysLog extends Model<SysLog> {

    private static final long serialVersionUID=1L;

    @NotNull(message = "不能为空")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "说明")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "错误详情")
    @TableField("exception_detail")
    private String exceptionDetail;

    @ApiModelProperty(value = "日志类型")
    @TableField("log_type")
    private String logType;

    @ApiModelProperty(value = "操作方法")
    @TableField("method")
    private String method;

    @ApiModelProperty(value = "参数")
    @TableField("params")
    private String params;

    @ApiModelProperty(value = "ip")
    @TableField("request_ip")
    private String requestIp;

    @ApiModelProperty(value = "用时")
    @TableField("time")
    private Long time;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "开始时间")
    @TableField("begin_time")
    private Date beginTime;

    @ApiModelProperty(value = "结束时间")
    @TableField("end_time")
    private Date endTime;

    @ApiModelProperty(value = "日志级别（1一般，2敏感，3危险）")
    @TableField("log_level")
    private Integer logLevel;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
