package com.xiaoshu.backendframework.controller.api;

import com.xiaoshu.backendframework.annotation.LogAnnotation;
import com.xiaoshu.backendframework.dto.Token;
import com.xiaoshu.backendframework.model.SysUser;
import com.xiaoshu.backendframework.token.TokenManager;
import com.xiaoshu.backendframework.util.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "登陆接口")
@RestController
public class ApiLoginController {

    @Autowired
    private TokenManager tokenManager;

    @LogAnnotation
    @ApiOperation(value = "Restful方式登陆,前后端分离时登录接口")
    @PostMapping("/admin/login/restful")
    public Token restfulLogin(String username, String password) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        SecurityUtils.getSubject().login(usernamePasswordToken);

        return tokenManager.saveToken(usernamePasswordToken);
    }

    @ApiOperation(value = "当前登录用户")
    @GetMapping("/admin/loginInfo")
    public SysUser getLoginInfo() {
        return UserUtil.getCurrentUser();
    }
}
