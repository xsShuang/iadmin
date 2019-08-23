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
 * 用户角色
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysUserRole查询对象", description="用户角色查询对象")
public class SysUserRoleQuery extends PageDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @QueryCondition
    private Integer id;


    @ApiModelProperty(value = "用户id")
    @QueryCondition
    private Integer userId;


    @ApiModelProperty(value = "角色id")
    @QueryCondition
    private Integer roleId;


    @QueryCondition
    private Date createTime;

}
