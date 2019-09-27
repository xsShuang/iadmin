package xyz.iotcode.iadmin.demo.security.provider;

import xyz.iotcode.iadmin.demo.security.bean.LoginDTO;
import xyz.iotcode.iadmin.demo.security.bean.UserInfoVO;
import xyz.iotcode.iadmin.permissions.bean.PermissionUser;

/**
 * @author Administrator
 */
public interface AuthenticationProvider {

    /**
     * 登录方法，返回token字符串
     * @param dto
     * @return
     */
    String login(LoginDTO dto);

    /**
     * 根据token获取用户
     * @param token
     * @return
     */
    PermissionUser getUserByToken(String token);

    UserInfoVO getUserInfoByToken(String token);
}
