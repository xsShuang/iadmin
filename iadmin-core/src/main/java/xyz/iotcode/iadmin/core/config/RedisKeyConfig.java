package xyz.iotcode.iadmin.core.config;

import com.alibaba.fastjson.JSON;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisKeyConfig extends CachingConfigurerSupport {
 
	/**
	 * redis key生成策略
	 * target: 类
	 * method: 方法
	 * params: 参数
	 * @return KeyGenerator
	 * 
	 * 注意: 该方法只是声明了key的生成策略,还未被使用,需在@Cacheable注解中指定keyGenerator
	 *      如: @Cacheable(value = "key", keyGenerator = "cacheKeyGenerator")
	 */
    @Bean
    public KeyGenerator cacheKeyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(method.getName());
            for (Object obj : params) {
            	// 由于参数可能不同, hashCode肯定不一样, 缓存的key也需要不一样
                sb.append(JSON.toJSONString(obj));
            }
            return sb.toString();
        };
    }
}