package xyz.iotcode.iadmin.demo.security.provider;

import cn.hutool.core.collection.CollectionUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.iotcode.iadmin.demo.module.system.entity.SysPermission;
import xyz.iotcode.iadmin.demo.module.system.entity.SysRole;
import xyz.iotcode.iadmin.demo.module.system.service.SysPermissionService;
import xyz.iotcode.iadmin.demo.module.system.service.SysRoleService;
import xyz.iotcode.iadmin.permissions.bean.PermissionUser;
import xyz.iotcode.iadmin.permissions.bean.UrlPermission;
import xyz.iotcode.iadmin.permissions.provider.PermissionProvider;
import xyz.iotcode.iadmin.permissions.provider.UserProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author xieshuang
 * @date 2019-08-23 16:18
 */
@Component
public class Iprovider implements UserProvider, PermissionProvider {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Override
    public UrlPermission getByUrlAndRequestWay(String url, String requestWay){
        SysPermission permission = sysPermissionService.getByUrl(url, requestWay);
        if (permission==null){
            return null;
        }
        UrlPermission urlPermission = new UrlPermission();
        BeanUtils.copyProperties(permission, urlPermission);
        List<SysRole> byPermissionId = sysRoleService.getByPermissionId(permission.getId());
        if (CollectionUtil.isEmpty(byPermissionId)){
            urlPermission.setRoles(Collections.emptyList());
        }else {
            List<String> stringList = new ArrayList<>(byPermissionId.size());
            for (SysRole sysRole : byPermissionId) {
                stringList.add(sysRole.getLabel());
            }
            urlPermission.setRoles(stringList);
        }
        return urlPermission;
    }

    @Override
    public UrlPermission getByPermissionCode(String code) {
        SysPermission byCode = sysPermissionService.getByCode(code);
        if (byCode==null){
            return null;
        }
        UrlPermission urlPermission = new UrlPermission();
        BeanUtils.copyProperties(byCode, urlPermission);
        List<SysRole> byPermissionId = sysRoleService.getByPermissionId(byCode.getId());
        if (CollectionUtil.isEmpty(byPermissionId)){
            urlPermission.setRoles(Collections.emptyList());
        }else {
            List<String> stringList = new ArrayList<>(byPermissionId.size());
            for (SysRole sysRole : byPermissionId) {
                stringList.add(sysRole.getLabel());
            }
            urlPermission.setRoles(stringList);
        }
        return urlPermission;
    }

    @Override
    public PermissionUser getUserByToken(String token) {
        return authenticationProvider.getUserByToken(token);
    }
}
