package xyz.iotcode.iadmin.demo.security.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.iotcode.iadmin.demo.module.system.entity.SysPermission;
import xyz.iotcode.iadmin.demo.module.system.entity.SysRole;
import xyz.iotcode.iadmin.demo.module.system.entity.SysUser;

import java.util.Collection;
import java.util.List;

/**
 * @author xieshuang
 * @date 2019-09-11 18:07
 */
@Data
@ApiModel("登录成功返回的VO")
public class LoginSuccessVO {

    @ApiModelProperty(value = "用户信息")
    private SysUser userInfo;
    @ApiModelProperty(value = "角色信息")
    private Collection<SysRole> roles;
    @ApiModelProperty(value = "权限信息")
    private Collection<SysPermission> permissions;
    @ApiModelProperty(value = "权限编码信息")
    private Collection<String> permissionCodes;
    private String token;
}
