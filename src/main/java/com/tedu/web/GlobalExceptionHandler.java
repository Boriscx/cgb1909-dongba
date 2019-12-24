package com.tedu.web;

import com.tedu.pojo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.transaction.TransactionException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


/**
 * 被{@link RestControllerAdvice @RestControllerAdvice}注解了, 用于处理Controller抛出的异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 用户认证异常
     * @param e shiro验证时异常
     * @return JsonResult
     */
    @ExceptionHandler(ShiroException.class)
    public JsonResult handleShiroException(ShiroException e) {
        e.printStackTrace();
        String message = "系统维护中";
        log.debug("controller抛出异常: ", e);
        if (e instanceof UnknownAccountException) {
            message = "账户不存在";
        } else if (e instanceof LockedAccountException) {
            message = "账户已被禁用";
        } else if (e instanceof IncorrectCredentialsException) {
            message = "密码不正确";
        } else if (e instanceof AuthorizationException) {
            message = "没有此操作权限";
        }
        return JsonResult.error(message);
    }

    /**
     * 运行时异常
     * @param e 异常
     * @return 返回异常信息
     */
    @ExceptionHandler(RuntimeException.class)
    public JsonResult handleRuntimeException(RuntimeException e) {
        e.printStackTrace();
        return JsonResult.error(e.getMessage());
    }

    /**
     * validated 验证信息异常处理
     * @param e 验证异常
     * @return json
     */
    @ExceptionHandler(BindException.class)
    public JsonResult handleBindException(BindException e) {
        List<ObjectError> allErrors = e.getAllErrors();
        StringBuilder message = new StringBuilder();
        for (ObjectError error:allErrors){
            message.append(error.getDefaultMessage()).append("\n");
        }
        return JsonResult.error(message.toString());
    }

    @ExceptionHandler(NullPointerException.class)
    public JsonResult handleNullPointerException(NullPointerException e) {
        e.printStackTrace();
        return JsonResult.error("您输入了空信息,请检查核对信息");
    }
    /**
     * 其他异常
     * @param e 异常
     * @return json
     */
    @ExceptionHandler(Exception.class)
    public JsonResult handleOtherException(Exception e) {
        e.printStackTrace();
        return JsonResult.error(e.getMessage());
    }

//    @TransactionalEventListener
//    TransactionException
    @ExceptionHandler(TransactionException.class)
    public JsonResult handleTransactionException(TransactionException e){
        e.printStackTrace();
        return JsonResult.error("事务异常:"+e.getLocalizedMessage());
    }

}
