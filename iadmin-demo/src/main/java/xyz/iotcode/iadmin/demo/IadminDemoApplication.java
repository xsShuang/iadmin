package xyz.iotcode.iadmin.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import xyz.iotcode.iadmin.common.annotation.EnableILettuceRedis;
import xyz.iotcode.iadmin.common.annotation.EnableIRedisKey;

/**
 * @author Administrator
 */
@EnableCaching
@EnableILettuceRedis
@EnableIRedisKey
@SpringBootApplication
public class IadminDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(IadminDemoApplication.class, args);
    }

}
