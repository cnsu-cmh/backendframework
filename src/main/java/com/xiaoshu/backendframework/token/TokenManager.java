package com.xiaoshu.backendframework.token;

import com.xiaoshu.backendframework.dto.Token;
import org.apache.shiro.authc.UsernamePasswordToken;

public interface TokenManager {

    /**
     * 保存Token
     *
     * @param token
     * @return
     */
    Token saveToken(UsernamePasswordToken token);

    /**
     * 根据token获取凭证
     * @param key
     * @return
     */
    UsernamePasswordToken getToken(String key);

    /**
     * 删除token
     * @param key
     */
    boolean deleteToken(String key);
}
