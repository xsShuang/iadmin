package xyz.iotcode.iadmin.demo.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.iotcode.iadmin.demo.security.provider.Iprovider;
import xyz.iotcode.iadmin.permissions.interceptor.PermissionInterceptor;
import xyz.iotcode.iadmin.permissions.properties.ISecurityProperties;

/**
 * @author xieshuang
 * @date 2019-08-23 16:03
 */
@Configuration
public class PermissionWebConfig implements WebMvcConfigurer {

    @Autowired
    private Iprovider iprovider;
    @Autowired
    private ISecurityProperties iSecurityProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new PermissionInterceptor(iprovider, iSecurityProperties));
        interceptorRegistration.addPathPatterns("/**");
        interceptorRegistration.excludePathPatterns("/error");
    }

}
