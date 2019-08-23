package xyz.iotcode.iadmin.permissions.interceptor;

import cn.hutool.core.collection.CollectionUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xyz.iotcode.iadmin.common.exception.MyRuntimeException;
import xyz.iotcode.iadmin.permissions.annotation.IPermissions;
import xyz.iotcode.iadmin.permissions.bean.PermissionUser;
import xyz.iotcode.iadmin.permissions.bean.UrlPermission;
import xyz.iotcode.iadmin.permissions.provider.PermissionProvider;
import xyz.iotcode.iadmin.permissions.provider.UserProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xieshuang
 * @date 2019-08-22 20:41
 */
public class PermissionInterceptor implements HandlerInterceptor {

    private static String TOKEN = "token";

    public PermissionInterceptor(PermissionProvider permissionProvider, UserProvider userProvider){
        super();
        this.permissionProvider = permissionProvider;
        this.userProvider = userProvider;
    }

    private UserProvider userProvider;
    private PermissionProvider permissionProvider;

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
     * 是否有权限
     * 验证逻辑：
     * 1.获取注解，判断是否有注解或者注解上是否有值
     * 2.如果没有注解或者没有值根据url获取链接需要的权限信息，否则根据注解的权限编码获取
     * 3.判断链接是否需要校验（没有权限信息，或者为0即不需要校验）
     * 4.如果需要校验则通过request获取token信息，通过用户提供者（UserProvider）获取用户信息
     * @param handler
     * @return
     */
    private boolean hasPermission(HttpServletRequest request, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法上的注解
            IPermissions iPermissions = handlerMethod.getMethod().getAnnotation(IPermissions.class);
            // 如果标记了注解，则判断权限
            UrlPermission urlPermission;
            if (iPermissions != null && StringUtils.isNotBlank(iPermissions.value())) {
                urlPermission = permissionProvider.getByPermissionCode(iPermissions.value());
            }else {
                urlPermission = permissionProvider.getByUrlAndRequestWay(request.getRequestURI(), request.getMethod());
            }
            if (urlPermission==null){
                return true;
            }else {
                int state = urlPermission.getState();
                // 状态（0不需要验证，1需要登录，2需要验证）
                if (state==0){
                    return true;
                }else if (state==1){
                    PermissionUser permissionUser = getPermissionUser(request);
                    if (permissionUser==null){
                        throw new MyRuntimeException("未登录", 401);
                    }
                    if (permissionUser.getState()==0){
                        throw new MyRuntimeException("账号已被禁用", 403);
                    }
                }else {
                    PermissionUser permissionUser = getPermissionUser(request);
                    if (permissionUser==null){
                        throw new MyRuntimeException("未登录", 401);
                    }
                    if (CollectionUtil.isEmpty(permissionUser.getRoles())){
                        throw new MyRuntimeException("无权限", 403);
                    }
                    if (!urlPermission.getRoles().removeAll(permissionUser.getRoles())){
                        throw new MyRuntimeException("无权限", 403);
                    }
                }
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
