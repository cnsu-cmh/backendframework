package com.xiaoshu.backendframework.dto;

import java.io.Serializable;
import java.util.Date;

public class Token implements Serializable {

    private static final long serialVersionUID = 2819309757126873820L;

    private String token;

    /**
     * 到期时间
     */
    private Date expireTime;

    public Token(String token, Date expireTime) {
        this.token = token;
        this.expireTime = expireTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
}
