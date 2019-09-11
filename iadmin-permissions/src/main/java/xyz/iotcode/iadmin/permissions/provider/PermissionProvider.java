package xyz.iotcode.iadmin.permissions.provider;

import xyz.iotcode.iadmin.permissions.bean.UrlPermission;

/**
 * @author xieshuang
 * @date 2019-08-22 20:48
 * 权限提供者
 */
public interface PermissionProvider {

    /**
     * 根据url获取链接的权限信息
     * @version: 1.0
     * @author: xieshuang
     * @param url
     * @param requestWay
     * @return xyz.iotcode.iadmin.permissions.bean.UrlPermission
     */
    UrlPermission getByUrlAndRequestWay(String url, String requestWay);

    /**
     * 根据权限编码获取链接的权限信息
     * @param code
     * @return
     */
    UrlPermission getByPermissionCode(String code);
}
