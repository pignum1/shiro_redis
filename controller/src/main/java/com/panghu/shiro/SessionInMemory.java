package com.panghu.shiro;

import org.apache.shiro.session.Session;

import java.util.Date;

/**
 * @ProjectName: nacos_base4j
 * @Package: com.panghu.shiro
 * @ClassName: SessionInMemory
 * @Author: wxy
 * @Description: Use ThreadLocal as a temporary storage of Session, so that shiro wouldn't keep read redis several times while a request coming.
 * @Date: 2020/5/19 10:45
 * @Version: 1.0
 */
public class SessionInMemory {

    private Session session;
    private Date createTime;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
