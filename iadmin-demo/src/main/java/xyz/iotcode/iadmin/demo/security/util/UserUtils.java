package xyz.iotcode.iadmin.demo.security.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.iotcode.iadmin.demo.security.provider.AuthenticationProvider;
import xyz.iotcode.iadmin.permissions.bean.PermissionUser;
import xyz.iotcode.iadmin.permissions.interceptor.PermissionInterceptor;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 */
@Component
public class UserUtils {

    @Autowired
    private AuthenticationProvider provider;

    private static AuthenticationProvider authenticationProvider;

    @PostConstruct
    public void init(){
        authenticationProvider = provider;
    }

    public static PermissionUser getUser(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String header = request.getHeader(PermissionInterceptor.TOKEN);
        if(StringUtils.isBlank(header)){
            header = request.getParameter(PermissionInterceptor.TOKEN);
        }
        if (StringUtils.isBlank(header)) {
            return null;
        }
        return authenticationProvider.getUserByToken(header);
    }
}
