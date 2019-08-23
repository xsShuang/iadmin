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
 * 用户
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysUser查询对象", description="用户查询对象")
public class SysUserQuery extends PageDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户id")
    @QueryCondition
    private Integer userId;


    @ApiModelProperty(value = "账号")
    @QueryCondition
    private String username;


    @ApiModelProperty(value = "密码")
    @QueryCondition
    private String password;


    @ApiModelProperty(value = "昵称")
    @QueryCondition
    private String nickName;


    @ApiModelProperty(value = "头像")
    @QueryCondition
    private String avatar;


    @ApiModelProperty(value = "性别")
    @QueryCondition
    private String sex;


    @ApiModelProperty(value = "手机号")
    @QueryCondition
    private String phone;


    @ApiModelProperty(value = "邮箱")
    @QueryCondition
    private String email;


    @ApiModelProperty(value = "邮箱是否验证，0未验证，1已验证")
    @QueryCondition
    private Integer emailVerified;


    @ApiModelProperty(value = "真实姓名")
    @QueryCondition
    private String trueName;


    @ApiModelProperty(value = "身份证号")
    @QueryCondition
    private String idCard;


    @ApiModelProperty(value = "出生日期")
    @QueryCondition
    private Date birthday;


    @ApiModelProperty(value = "部门id")
    @QueryCondition
    private Integer departmentId;


    @ApiModelProperty(value = "状态，1正常，0禁用")
    @QueryCondition
    private Integer state;


    @ApiModelProperty(value = "注册时间")
    @QueryCondition
    private Date createTime;


    @ApiModelProperty(value = "修改时间")
    @QueryCondition
    private Date updateTime;

}
