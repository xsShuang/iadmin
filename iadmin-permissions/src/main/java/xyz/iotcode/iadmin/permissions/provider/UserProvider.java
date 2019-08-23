package xyz.iotcode.iadmin.permissions.provider;

import xyz.iotcode.iadmin.permissions.bean.PermissionUser;

/**
 * @author xieshuang
 * @date 2019-08-22 21:24
 * 用户提供者
 */
public interface UserProvider {

    PermissionUser getUserByToken(String token);
}
