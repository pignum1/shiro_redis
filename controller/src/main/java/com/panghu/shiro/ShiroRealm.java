package com.panghu.shiro;

import com.panghu.dao.SysUserDao;
import com.panghu.entity.SysRole;
import com.panghu.entity.SysUser;
import com.panghu.global.MyByteSource;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * @ProjectName: nacos_base4j
 * @Package: com.panghu
 * @ClassName: MyShiroRealm
 * @Author: wxy
 * @Description: 拦截实现
 * @Date: 2020/5/5 14:23
 * @Version: 1.0
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserDao userDao;


    /**
     * @Description: 用户权限配置
     * @Param: [principals]
     * @return: org.apache.shiro.authz.AuthorizationInfo
     * @Author: Mr.Wei
     * @Date: 2020/5/19
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限调用方法开始");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser userInfo = (SysUser) principals.getPrimaryPrincipal();
        for (SysRole role : userInfo.getRoles()) {
            authorizationInfo.addRoles(new ArrayList<>());

            authorizationInfo.addStringPermission("321");
        }
        return authorizationInfo;
    }


    /**
     * @Description: 身份验证
     * @Param: [token]
     * @return: org.apache.shiro.authc.AuthenticationInfo
     * @Author: Mr.Wei
     * @Date: 2020/5/5
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");

        // 获取用户的输入的账号.
        String name = (String) token.getPrincipal();
        System.out.println(token.getCredentials());

        // 通过name和password从数据库中查找 User对象，
        SysUser userInfo = userDao.findByAccount(name);
        if (userInfo == null) {
            return null;
        }

        String uid = userInfo.getId();
//        List<Role> roles = userService.getRoles(uid);
//        int roleId = roles.get(0).getId();
//        List<Permission> permissions = userService.getPermissions(roleId);
//        roles.get(0).setPermissions(permissions);
//        userInfo.setRoleList(roles);
        // 加密方式;
        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        String password = userInfo.getPassword();
        // 秘钥
        ByteSource salt = ByteSource.Util.bytes(userInfo.getAccount());
        // 当前域的名称（MyShiroRealm）
        String realmName = getName();
        // 认证信息
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userInfo.getAccount(), password, salt, realmName);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(), new MyByteSource(userInfo.getUserName()), getName());

        return info;
    }
}
