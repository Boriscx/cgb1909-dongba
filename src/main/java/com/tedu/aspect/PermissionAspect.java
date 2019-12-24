package com.tedu.aspect;


import com.tedu.aspect.annotation.ControllerPermission;
import com.tedu.dao.SysUserDao;
import com.tedu.pojo.JsonResult;
import com.tedu.util.ShiroUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@Component
public class PermissionAspect {

    private SysUserDao sysUserDao;

    private HttpSession session;

    public PermissionAspect(SysUserDao sysUserDao, HttpSession session) {
        this.session = session;
        this.sysUserDao = sysUserDao;
    }

    @Pointcut("@annotation(com.tedu.aspect.annotation.ControllerPermission)")
    public void asp() {
    }

    @Around("asp()")
    public Object Before(ProceedingJoinPoint pjp) throws Throwable {

        // 从session中获取权限数据
        List<String> userPermission = (List<String>) session.getAttribute("userPermission");

        // 如果session中没有则从数据库中获取权限数据
        if (userPermission == null || userPermission.size() == 0) {
            // 从持久层获取权限数据
            userPermission = sysUserDao.findPermissionsByUserId(ShiroUtil.getUserId());
            Stream<String> stringStream = userPermission.stream().filter((per) -> per != null && per.length() > 1);
            userPermission = stringStream.collect(Collectors.toList());
            // 将权限数据保存进session中
            session.setAttribute("userPermission", userPermission);
        }
        // 获取切入点调用的方法
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        // 获取方法的注解
        ControllerPermission annotation = signature.getMethod().getAnnotation(ControllerPermission.class);
        // 获取注解上的值
        String value = annotation.value();
        // 检查用户权限列表中是否有此方法的访问权限,没有则返回没有操作权限
        if (!userPermission.contains(value)) return JsonResult.error("没有操作权限");
        return pjp.proceed();
    }

}
