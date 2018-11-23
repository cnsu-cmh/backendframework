package com.xiaoshu.backendframework.constants;

public interface UserConstants {

    /**
     * shiro采用加密算法
     */
    String HASH_ALGORITHM = "MD5";

    /** 加密次数 */
    int HASH_ITERATIONS = 3;

    String LOGIN_USER = "login_user";

    String USER_PERMISSIONS = "user_permissions";

    /** 登陆token(nginx中默认header无视下划线) */
    String LOGIN_TOKEN = "login-token";

    /**
     * 验证码
     */
    String VALIDATE_CODE = "validateCode";

    /**
     *系统用户默认密码
     */
    String DEFAULT_PASSWORD = "123456";
}