package com.panghu.global;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

/**
 * @ProjectName: nacos_base4j
 * @Package: com.panghu.global
 * @ClassName: GlobalExceptionHandler
 * @Author: wxy
 * @Description: 全局异常处理类
 * @Date: 2020/5/6 15:32
 * @Version: 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NullPointerException.class)
    public String UnknownAccountHandle(HttpServletRequest req, Exception e){
        return "账号不存在";
    }


    @ExceptionHandler(value = IncorrectCredentialsException.class)
    public String IncorrectCredentialsHandle(HttpServletRequest req, Exception e){
        return "密码不正确";
    }

//    InvocationTargetException

    @ExceptionHandler(value = InvocationTargetException.class)
    public String InvocationTargetHandle(HttpServletRequest req, Exception e){
        return "密码不正确";
    }
}
