package com.xiaoshu.backendframework.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.xiaoshu.backendframework.dto.ResponseInfo;
import com.xiaoshu.backendframework.token.TokenManager;
import com.xiaoshu.backendframework.model.SysUser;
import com.xiaoshu.backendframework.service.SysLogService;
import com.xiaoshu.backendframework.util.SpringUtil;
import com.xiaoshu.backendframework.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");


    private static String SUCCESS_INFO = JSONObject.toJSONString(new ResponseInfo(HttpStatus.OK.value() + "", "退出成功"));

    private static String ERR_INFO = JSONObject.toJSONString(new ResponseInfo(HttpStatus.BAD_REQUEST.value() + "", "退出失败,token不存在"));

    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        String loginToken = RestfulFilter.getToken(request);
        SysUser user = UserUtil.getCurrentUser();
        if (StringUtils.isBlank(loginToken)) {// 非Restful方式
            boolean flag = super.preHandle(request, response);
            log.debug("{}退出成功", user.getUsername());
            SpringUtil.getBean(SysLogService.class).save(user.getId(), "退出", true, null);

            return flag;
        } else {
            TokenManager tokenManager = SpringUtil.getBean(TokenManager.class);
            boolean flag = tokenManager.deleteToken(loginToken);
            if (flag) {
                RestfulFilter.writeResponse(WebUtils.toHttp(response), HttpStatus.OK.value(), SUCCESS_INFO);
                log.debug("{}退出成功", user.getUsername());
            } else {
                RestfulFilter.writeResponse(WebUtils.toHttp(response), HttpStatus.BAD_REQUEST.value(), ERR_INFO);
            }

            SpringUtil.getBean(SysLogService.class).save(user.getId(), "token方式退出", flag, null);

            return false;
        }
    }

}
