package xyz.iotcode.iadmin.common.mapping.annotation;

import java.lang.annotation.*;

/**
 * @author xieshuang
 */

@Documented
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Mappings {

    Mapping[] value();
}
