package xyz.iotcode.iadmin.permissions.bean;

import java.util.Collection;

/**
 * @author xieshuang
 * @date 2019-08-22 21:22
 */
public class PermissionUser {

    private Object userId;
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色
     */
    private Collection<String> roles;

    /**
     * 权限
     */
    private Collection<String> permissions;

    /**
     * 状态（1启用，0禁用）
     */
    private int state;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<String> getRoles() {
        return roles;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public Collection<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Collection<String> permissions) {
        this.permissions = permissions;
    }
}
