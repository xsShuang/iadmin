package xyz.iotcode.iadmin.demo.module.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Administrator
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SaveLog {

	/**
	 * 说明
	 * @return
	 */
	String value() default "";

	/**
	 * 日志级别（1一般，2敏感，3危险）
	 * @return
	 */
	int level() default 1;
}