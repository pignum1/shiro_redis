package com.panghu.controller;

import com.panghu.dao.SysUserDao;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ProjectName: nacos_base4j
 * @Package: com.panghu.controller
 * @ClassName: UserController
 * @Author: wxy
 * @Description: 用户登录controller
 * @Date: 2020/5/5 14:10
 * @Version: 1.0
 */
@RestController
public class UserController {

    @Autowired
    private SysUserDao userDao;

    @PostMapping("/login")
    public String login(String username, String password) {
        try {
            System.out.println("login start");
//        String exception = (String) request.getAttribute("shiroLoginFailure");
            //登陆验证
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
            return (String) subject.getSession().getId();

//            return "登陆成功";
        } catch (IncorrectCredentialsException e) {
            return "密码错误";
        } catch (LockedAccountException e) {
            return "账户已被冻结";
        } catch (AuthenticationException e) {
            return "用户不存在";
        } catch (Exception e) {
            return "登录接口异常";
        }
    }

    @PostMapping("/test")
    public String test(HttpServletRequest request) {
        System.out.println("login start");
        HttpSession session   =   request.getSession();
        return "测试方法";
    }

}
