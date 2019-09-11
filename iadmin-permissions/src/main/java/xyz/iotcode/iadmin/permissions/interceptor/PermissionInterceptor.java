package xyz.iotcode.iadmin.permissions.interceptor;

import cn.hutool.core.collection.CollectionUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xyz.iotcode.iadmin.common.exception.MyRuntimeException;
import xyz.iotcode.iadmin.permissions.annotation.IPermissions;
import xyz.iotcode.iadmin.permissions.bean.PermissionUser;
import xyz.iotcode.iadmin.permissions.properties.ISecurityProperties;
import xyz.iotcode.iadmin.permissions.provider.UserProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xieshuang
 * @date 2019-08-22 20:41
 */
public class PermissionInterceptor implements HandlerInterceptor {

    public static String TOKEN = "token";

    public PermissionInterceptor(UserProvider userProvider, ISecurityProperties properties){
        super();
        this.properties = properties;
        this.userProvider = userProvider;
    }

    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();
    private UserProvider userProvider;
    private ISecurityProperties properties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return hasPermission(request, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }


    /**
     * 验证是否有权限
     * @version: 1.0
     * @date: 2019/9/11 16:06
     * @author: xieshuang
     * @param request
     * @param handler
     * @return boolean
     */
    private boolean hasPermission(HttpServletRequest request, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法上的注解
            IPermissions iPermissions = handlerMethod.getMethod().getAnnotation(IPermissions.class);
            String[] noCheckUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getNoCheckUrl(), ",");
            if (noCheckUrls!=null&&noCheckUrls.length>0){
                for (int i = 0; i < noCheckUrls.length; i++) {
                    if (ANT_PATH_MATCHER.match(noCheckUrls[i], request.getRequestURI())){
                        return true;
                    }
                }
            }
            if (iPermissions == null) {
                return true;
            }
            PermissionUser permissionUser = getPermissionUser(request);
            if (permissionUser==null){
                throw new MyRuntimeException("未登录", 401);
            }
            if (StringUtils.isBlank(iPermissions.value())){
                return true;
            }
            if (CollectionUtil.isEmpty(permissionUser.getPermissions())){
                throw new MyRuntimeException("无权限", 403);
            }
            if (permissionUser.getPermissions().contains(iPermissions.value())){
                return true;
            }
        }
        return true;
    }

    private PermissionUser getPermissionUser(HttpServletRequest request){
        String header = request.getHeader(TOKEN);
        if(StringUtils.isBlank(header)){
            header = request.getParameter(TOKEN);
        }
        if (StringUtils.isBlank(header)) {
            return null;
        }
        return userProvider.getUserByToken(header);
    }
}
