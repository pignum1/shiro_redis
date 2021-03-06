package com.panghu.shiro;

import com.panghu.dao.SysUserDao;
import com.panghu.entity.SysUser;
import com.panghu.reids.RedisManager;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ProjectName: nacos_base4j
 * @Package: com.panghu.shiro
 * @ClassName: RetryLimitHashedCredentialsMatcher
 * @Author: wxy
 * @Description: 登录失败次数限制
 * @Date: 2020/5/19 16:29
 * @Version: 1.0
 */
public class RetryLimitHashedCredentialsMatcher extends SimpleCredentialsMatcher {
    private static final Logger logger = LoggerFactory.getLogger(RetryLimitHashedCredentialsMatcher.class);

    public static final String DEFAULT_RETRYLIMIT_CACHE_KEY_PREFIX = "shiro:cache:retrylimit:";
    private String keyPrefix = DEFAULT_RETRYLIMIT_CACHE_KEY_PREFIX;

    @Autowired
    private SysUserDao userDao;
    private RedisManager redisManager;

    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
    }

    private String getRedisKickoutKey(String username) {
        return this.keyPrefix + username;
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //获取用户名
        String username = (String) token.getPrincipal();
        //获取用户登录次数
        AtomicInteger retryCount = new AtomicInteger(redisManager.get(getRedisKickoutKey(username)) == null ? 0 : (Integer) redisManager.get(getRedisKickoutKey(username)));
        if (retryCount == null) {
            //如果用户没有登陆过,登陆次数加1 并放入缓存
            retryCount = new AtomicInteger(0);
        }
        if (retryCount.incrementAndGet() > 5) {
            //如果用户登陆失败次数大于5次 抛出锁定用户异常  并修改数据库字段
            SysUser user = userDao.findByUserName(username);
            if (user != null && "0".equals(user.getState())) {
                //数据库字段 默认为 0  就是正常状态 所以 要改为1
                //修改数据库的状态字段为锁定
                user.setState("1");
                userDao.save(user);
                logger.info("锁定用户" + user.getUserName());
                //抛出用户锁定异常
                throw new LockedAccountException();
            }
        }
        //判断用户账号和密码是否正确
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            //如果正确,从缓存中将用户登录计数 清除
            redisManager.del(getRedisKickoutKey(username));
        }
        return matches;
    }

    /**
     * 根据用户名 解锁用户
     *
     * @param username
     * @return
     */
    public void unlockAccount(String username) {
        SysUser user = userDao.findByUserName(username);
        if (user != null) {
            //修改数据库的状态字段为锁定
            user.setState("0");
            userDao.save(user);
            redisManager.del(getRedisKickoutKey(username));
        }
    }

}
