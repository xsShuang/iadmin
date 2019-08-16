package xyz.iotcode.iadmin.core.wrapper;

import java.lang.annotation.*;

/**
 * @author xieshuang
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QueryCondition {

    /**
     * 查询条件枚举类
     * DEFAULT，不进行查询
     */
    enum Condition{
        DEFAULT, EQ, IN, LIKE, GE, LE
    }

    /**
     * Sort为排序字段，
     * DEFAULT,不进行排序
     * DESC，倒叙
     * ASC，顺序
     * AUTO，根据值进行排序，当值为数值型时，0代表顺序，其余都为倒叙
     * 当值为string类型时，asc和ASC为正序，其他都为逆序
     * 其余情况均为逆序
     */
    enum Sort{
        DEFAULT, DESC, ASC, AUTO
    }

    /**
     * 查询条件
     * @return
     */
    Condition condition() default Condition.EQ;

    /**
     * 数据库字段，默认为空，自动根据驼峰转下划线
     * @return
     */
    String field() default "";

    /**
     * 排序说明
     * @return
     */
    Sort sort() default Sort.DEFAULT;
}
