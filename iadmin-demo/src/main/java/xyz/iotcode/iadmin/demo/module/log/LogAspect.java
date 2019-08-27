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

    private Date date;


    @Pointcut("@annotation(SaveLog)")
    public void logPointcut() {
    }

    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        Date beginTime = new Date();
        date = beginTime;
        result = joinPoint.proceed();
        Date endTime = new Date();
        SysLog sysLog = new SysLog();
        sysLog.setBeginTime(beginTime);
        sysLog.setEndTime(endTime);
        sysLog.setTime(endTime.getTime()-beginTime.getTime());
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = joinPoint.getTarget().getClass().getName()+"."+signature.getName()+"()";
        String params = "{";
        //参数值
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        if(argValues != null){
            for (int i = 0; i < argValues.length; i++) {
                params += " " + argNames[i] + ": " + argValues[i];
            }
        }
        SaveLog log = method.getAnnotation(SaveLog.class);
        sysLog.setDescription(log.value());
        sysLog.setLogType("info");
        sysLog.setLogLevel(log.level());
        sysLog.setUsername(getUsername());
        sysLog.setRequestIp(IPUtil.getIpAddr());
        sysLog.setMethod(methodName);
        sysLog.setParams(params+ " }");
        sysLogService.isave(sysLog);
        return result;
    }

    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        Date endTime = new Date();
        SysLog sysLog = new SysLog();
        sysLog.setBeginTime(date);
        sysLog.setEndTime(endTime);
        sysLog.setTime(endTime.getTime()-date.getTime());
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = joinPoint.getTarget().getClass().getName()+"."+signature.getName()+"()";
        String params = "{";
        //参数值
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        if(argValues != null){
            for (int i = 0; i < argValues.length; i++) {
                params += " " + argNames[i] + ": " + argValues[i];
            }
        }
        SaveLog log = method.getAnnotation(SaveLog.class);
        sysLog.setExceptionDetail(ThrowableUtil.getStackTrace(e));
        sysLog.setDescription(log.value());
        sysLog.setLogType("error");
        sysLog.setLogLevel(log.level());
        sysLog.setUsername(getUsername());
        sysLog.setRequestIp(IPUtil.getIpAddr());
        sysLog.setMethod(methodName);
        sysLog.setParams(params+ " }");
        sysLogService.isave(sysLog);
    }

    public String getUsername() {
        try {
            return UserUtils.getUser().getUsername();
        }catch (Exception e){
            return "";
        }
    }
}
