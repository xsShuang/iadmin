package xyz.iotcode.iadmin.common.mapping.annotation;

import java.lang.annotation.*;

/**
 * @author xieshuang
 */

@Documented
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Mapping {

    /**
     * 参数数组
     * 1.$$，表示参数是字段本身
     * 2.$a，表示参数是bean中的字段，a为字段名称
     * 3.int#xxx，表示参数是int类型的，值为xxx
     * @return
     */
    String[] params() default "$$";

    /**
     * 映射类型
     * @return
     */
    MappingType mappingType();

    /**
     * 结果转换数组
     * 规则
     * 1.$$，表示结果名称为自己，如 id，映射后还是 id
     * 2.$a，表示结果由自己转化成b，$userName
     * 3.a>b，表示结果名称由a转化成b，如userId>userName
     * @return
     */
    String[] resultTrans() default "$$";

    /**
     * 服务提供者
     * @return
     */
    Class provider();

    /**
     * 方法
     * @return
     */
    String method();

    enum MappingType{
        /**
         * 映射为bean
         */
        BEAN,
        /**
         * 映射为字段
         */
        FIELD,
        /**
         * 映射到voMap
         */
        VO_MAP
    }
}
