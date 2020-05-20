package com.panghu.shiro;

import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.mgt.SimpleSession;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * @ProjectName: nacos_base4j
 * @Package: com.panghu.shiro
 * @ClassName: ShiroSession
 * @Author: wxy
 * @Description: session 由于SimpleSession lastAccessTime更改后也会调用SessionDao update方法，
 * 增加标识位，如果只是更新lastAccessTime SessionDao update方法直接返回
 * @Date: 2020/5/19 10:39
 * @Version: 1.0
 */
public class ShiroSession extends SimpleSession implements Serializable {
    // 除lastAccessTime以外其他字段发生改变时为true
    private boolean isChanged = false;

//    private String id = "init";

    public ShiroSession() {
        super();
        this.setChanged(true);
    }

    public ShiroSession(String host) {
        super(host);
        this.setChanged(true);
    }


    @Override
    public void setId(Serializable id) {
        super.setId(id);
        this.setChanged(true);
    }

    @Override
    public void setStopTimestamp(Date stopTimestamp) {
        super.setStopTimestamp(stopTimestamp);
        this.setChanged(true);
    }

    @Override
    public void setExpired(boolean expired) {
        super.setExpired(expired);
        this.setChanged(true);
    }

    @Override
    public void setTimeout(long timeout) {
        super.setTimeout(timeout);
        this.setChanged(true);
    }

    @Override
    public void setHost(String host) {
        super.setHost(host);
        this.setChanged(true);
    }

    @Override
    public void setAttributes(Map<Object, Object> attributes) {
        super.setAttributes(attributes);
        this.setChanged(true);
    }

    @Override
    public void setAttribute(Object key, Object value) {
        super.setAttribute(key, value);
        this.setChanged(true);
    }

    @Override
    public Object removeAttribute(Object key) {
        this.setChanged(true);
        return super.removeAttribute(key);
    }

    /**
     * 停止
     */
    @Override
    public void stop() {
        super.stop();
        this.setChanged(true);
    }

    /**
     * 设置过期
     */
    @Override
    protected void expire() {
        this.stop();
        this.setExpired(true);
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged(boolean isChanged) {
        this.isChanged = isChanged;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected boolean onEquals(SimpleSession ss) {
        return super.onEquals(ss);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Serializable getId() {
        return super.getId();
    }

    @Override
    public Date getStartTimestamp() {
        return super.getStartTimestamp();
    }

    @Override
    public void setStartTimestamp(Date startTimestamp) {
        super.setStartTimestamp(startTimestamp);
    }

    @Override
    public Date getStopTimestamp() {
        return super.getStopTimestamp();
    }

    @Override
    public Date getLastAccessTime() {
        return super.getLastAccessTime();
    }

    @Override
    public void setLastAccessTime(Date lastAccessTime) {
        super.setLastAccessTime(lastAccessTime);
    }

    @Override
    public boolean isExpired() {
        return super.isExpired();
    }

    @Override
    public long getTimeout() {
        return super.getTimeout();
    }

    @Override
    public String getHost() {
        return super.getHost();
    }

    @Override
    public Map<Object, Object> getAttributes() {
        return super.getAttributes();
    }

    @Override
    public void touch() {
        super.touch();
    }

    @Override
    protected boolean isStopped() {
        return super.isStopped();
    }

    @Override
    public boolean isValid() {
        return super.isValid();
    }

    @Override
    protected boolean isTimedOut() {
        return super.isTimedOut();
    }

    @Override
    public void validate() throws InvalidSessionException {
        super.validate();
    }

    @Override
    public Collection<Object> getAttributeKeys() throws InvalidSessionException {
        return super.getAttributeKeys();
    }

    @Override
    public Object getAttribute(Object key) {
        return super.getAttribute(key);
    }
}
