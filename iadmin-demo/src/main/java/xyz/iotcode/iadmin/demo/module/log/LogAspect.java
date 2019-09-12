package xyz.iotcode.iadmin.demo.module.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.iotcode.iadmin.common.util.IPUtil;
import xyz.iotcode.iadmin.common.util.ThrowableUtil;
import xyz.iotcode.iadmin.core.common.log.SaveLog;
import xyz.iotcode.iadmin.demo.module.log.entity.SysLog;
import xyz.iotcode.iadmin.demo.module.log.service.SysLogService;
import xyz.iotcode.iadmin.demo.security.util.UserUtils;

import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Autowired
    private SysLogService sysLogService;

    @Pointcut("@annotation(xyz.iotcode.iadmin.core.common.log.SaveLog)")
    public void logPointcut() {
    }

    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        long beginTime = System.currentTimeMillis();
        result = joinPoint.proceed();
        SysLog sysLog = new SysLog();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        sysLog.setLogType("info");
        sysLog.setTime(time);
        sysLog.setUsername(getUsername());
        sysLog.setRequestIp(IPUtil.getIpAddr());
        sysLogService.isave(joinPoint, sysLog);
        return result;
    }

    public String getUsername() {
        try {
            return UserUtils.getUser().getUsername();
        }catch (Exception e){
            return "";
        }
    }
}
