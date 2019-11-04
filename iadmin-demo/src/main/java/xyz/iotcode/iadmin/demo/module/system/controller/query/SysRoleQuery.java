package xyz.iotcode.iadmin.demo.module.system.controller.query;

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
 * 角色
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysRole查询对象", description="角色查询对象")
public class SysRoleQuery extends PageDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "角色id")
    @QueryCondition
    private Integer id;


    @ApiModelProperty(value = "角色名称")
    @QueryCondition
    private String roleName;


    @ApiModelProperty(value = "备注")
    @QueryCondition
    private String comments;


    @ApiModelProperty(value = "创建时间")
    @QueryCondition
    private Date createTime;


    @ApiModelProperty(value = "修改时间")
    @QueryCondition
    private Date updateTime;


    @ApiModelProperty(value = "角色标识")
    @QueryCondition
    private String label;

}
