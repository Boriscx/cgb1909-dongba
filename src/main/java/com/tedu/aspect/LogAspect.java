package com.tedu.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tedu.aspect.annotation.RequestLog;
import com.tedu.entity.SysLog;
import com.tedu.service.SysLogService;
import com.tedu.util.ShiroUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

@Aspect
@Component
public class LogAspect {

    private SysLogService sysLogService;

    private HttpServletRequest request;

    public LogAspect(SysLogService sysLogService, HttpServletRequest request) {
        this.sysLogService = sysLogService;
        this.request = request;
    }

    @Pointcut("@annotation(com.tedu.aspect.annotation.RequestLog)")
    public void doss() {
    }

    @Around("doss()")
    public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
        Long start = System.currentTimeMillis();
        Object obj = joinPoint.proceed();
        Long end = System.currentTimeMillis();
        saveLog(joinPoint, end - start);
        return obj;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long totalTime) throws Throwable {
        // 调用方法所在类
        Object target = joinPoint.getTarget();
        Class<?> aClass = target.getClass();
        String className = aClass.getName();
        // 调用方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getName();
        // 调用方法参数名称和值
        Map<String, Object> paramMap = new HashMap<>();
        Iterator<Object> iterator = Stream.of(joinPoint.getArgs()).iterator();
        Stream.of(signature.getParameterNames()).forEach(param -> {
            paramMap.put(param, iterator.next());
        });
        String s = new ObjectMapper().writeValueAsString(paramMap);
        // 所进行的操作
        RequestLog annotation = signature.getMethod().getAnnotation(RequestLog.class);
        String operation = annotation.value();
        // 时间 totalTime
        // 用户 shiroUtil.getUsername()
        String username = ShiroUtil.getUsername();
        // ip地址
        String remoteAddr = request.getRemoteAddr();
        // IPUtil.getIp();

        SysLog sysLog = new SysLog(username, operation, className + "." + methodName, s, totalTime, remoteAddr);
        System.out.println(sysLog);
        sysLogService.saveObject(sysLog);
    }

}
