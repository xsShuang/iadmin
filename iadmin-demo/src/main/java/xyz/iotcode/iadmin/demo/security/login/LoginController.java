package xyz.iotcode.iadmin.demo.security.login;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.iotcode.iadmin.common.vo.IResult;
import xyz.iotcode.iadmin.demo.security.bean.LoginDTO;
import xyz.iotcode.iadmin.demo.security.bean.UserInfoVO;
import xyz.iotcode.iadmin.demo.security.provider.AuthenticationProvider;

import javax.validation.Valid;

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

    @PostMapping
    public IResult<String> login(@Valid @RequestBody LoginDTO dto){
        return IResult.ok("登录成功", authenticationProvider.login(dto));
    }

    @GetMapping("/userInfo")
    public IResult<UserInfoVO> getUserInfo(@RequestHeader("token") String token){
        return IResult.ok(authenticationProvider.getUserInfoByToken(token));
    }
}
