package xyz.iotcode.iadmin.permissions.bean;

import java.util.Collection;

/**
 * @author xieshuang
 * @date 2019-08-22 20:51
 * url的权限信息
 */
public class UrlPermission {

    /**
     * 链接
     */
    private String url;
    /**
     * 状态（0不需要验证，1需要登录，2需要验证）
     */
    private int state;

    /**
     * 权限编码
     */
    private String permissionCode;

    /**
     * 访问该链接需要的角色集合
     */
    private Collection<String> roles;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public Collection<String> getRoles() {
        return roles;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }
}
