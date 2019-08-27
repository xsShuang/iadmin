package xyz.iotcode.iadmin.demo.security.login;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.iotcode.iadmin.common.vo.IResult;
import xyz.iotcode.iadmin.demo.security.bean.LoginDTO;
import xyz.iotcode.iadmin.demo.security.provider.AuthenticationProvider;

/**
 * @author xieshuang
 * @date 2019-08-23 17:49
 */
@Api(value="login接口", tags="登录接口")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @RequestMapping
    public IResult login(LoginDTO dto){
        return IResult.ok(authenticationProvider.login(dto));
    }
}
