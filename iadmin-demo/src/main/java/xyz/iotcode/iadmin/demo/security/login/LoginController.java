package xyz.iotcode.iadmin.demo.security.login;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.iotcode.iadmin.common.vo.IResult;
import xyz.iotcode.iadmin.demo.security.bean.LoginDTO;
import xyz.iotcode.iadmin.demo.system.entity.SysUser;

/**
 * @author xieshuang
 * @date 2019-08-23 17:49
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping
    public IResult login(LoginDTO dto){
        SysUser sysUser = new SysUser().selectOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, dto.getUsername())
                .eq(SysUser::getPassword, dto.getPassword()));
        if (sysUser==null){
            return IResult.fail("用户名或者密码错误");
        }
        if (sysUser.getState()==0){
            return IResult.fail("账户已被禁用");
        }
        return IResult.ok();
    }
}
