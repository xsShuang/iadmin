package xyz.iotcode.iadmin.demo.security.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author xieshuang
 * @date 2019-08-23 17:51
 */
@Data
@ApiModel("登录DTO")
public class LoginDTO {

    @NotBlank(message = "登录名不能为空")
    @ApiModelProperty(value = "登录名", required = true)
    private String username;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "保存登录，默认2小时，保存后7天")
    private Boolean saveLogin;
}
