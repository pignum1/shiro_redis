package com.panghu.controller;
import com.google.common.collect.Lists;
import java.util.Date;

import com.panghu.dao.SysUserDao;
import com.panghu.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
 * @Date: 2020/5/5 "1"4:"1"0
 * @Version: "1".0
 */
@RestController
public class UserController {

    @Autowired
    private SysUserDao userDao;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;



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

    @PostMapping("/redisTest")
    public String redisTest(){
        SysUser user = new SysUser();
        user.setAccount("1");
        user.setPassword("1");
        user.setUserType("1");
        user.setUserName("1");
        user.setNamePinyin("1");
        user.setSex(0);
        user.setAvatar("1");
        user.setPhone("1");
        user.setEmail("1");
        user.setIdCard("1");
        user.setWeiXin("1");
        user.setWeiBo("1");
        user.setQq("1");
        user.setBirthDay(new Date());
        user.setDeptId(0L);
        user.setPosition("1");
        user.setAddress("1");
        user.setStaffNo("1");
        user.setOldPassword("1");
        user.setDeptName("1");
        user.setUserTypeText("1");
        user.setSalt("1");
        user.setRoles(Lists.newArrayList());
        user.setId("1");
        user.setVersion(0L);
        user.setCreateTime(new Date());
        user.setLastTime(new Date());
        user.setDeleteFlag(false);
        redisTemplate.opsForValue().set("123",user);
        redisTemplate.opsForList();
        return "67890";
    }

}
