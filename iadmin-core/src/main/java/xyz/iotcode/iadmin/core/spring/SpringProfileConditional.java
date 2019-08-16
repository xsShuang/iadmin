package xyz.iotcode.iadmin.core.spring;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author xieshuang
 */
public class SpringProfileConditional implements Condition {
    /**
     * 环境装配条件
     * @param context 条件上下文
     * @param metadata 注释类型的元数据
     * @return true 装配bean，否则不装配
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 取出环境变量
        Environment env = context.getEnvironment();
        return "dev".equals(env.getProperty("spring.profiles.active"));
    }
    
}