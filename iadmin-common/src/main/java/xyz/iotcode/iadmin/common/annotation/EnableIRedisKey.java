package xyz.iotcode.iadmin.common.annotation;

import org.springframework.context.annotation.Import;
import xyz.iotcode.iadmin.common.redis.RedisKeyConfig;

import java.lang.annotation.*;

/**
 * @author MrBird
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RedisKeyConfig.class)
public @interface EnableIRedisKey {
}
