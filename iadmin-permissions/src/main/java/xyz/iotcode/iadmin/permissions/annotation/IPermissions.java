package xyz.iotcode.iadmin.permissions.annotation;
import java.lang.annotation.*;

/**
 * @author xieshuang
 * @date 2019-08-22 20:37
 * 权限验证注解
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface IPermissions {
    String value();
}
