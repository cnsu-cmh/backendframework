package com.xiaoshu.backendframework.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.xiaoshu.backendframework.annotation.LogAnnotation;
import com.xiaoshu.backendframework.constants.UserConstants;
import com.xiaoshu.backendframework.token.TokenManager;
import com.xiaoshu.backendframework.util.ResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;

@RestController
public class LonginController {

    private final static Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    @Qualifier("captchaProducer")
    DefaultKaptcha captchaProducer;

    @Autowired
    private ServerProperties serverProperties;

    @GetMapping("/getCaptcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置页面不缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String verifyCode = captchaProducer.createText();
        //将验证码放到HttpSession里面
        request.getSession().setAttribute(UserConstants.VALIDATE_CODE, verifyCode);
        log.info("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");
        //设置输出的内容的类型为JPEG图像
        response.setContentType("image/jpeg");
        BufferedImage bufferedImage = captchaProducer.createImage(verifyCode);
        //写给浏览器
        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
    }

    @LogAnnotation(module = "web端登陆")
    @PostMapping("admin/login")
    @ResponseBody
    public ResponseEntity adminLogin(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return ResponseEntity.failure("用户名或者密码不能为空");
        }else if(StringUtils.isBlank(code)){
            return ResponseEntity.failure("验证码不能为空");
        }
        HttpSession session = request.getSession();
        if(session == null){
            return ResponseEntity.failure("session超时");
        }
        String trueCode = (String)session.getAttribute(UserConstants.VALIDATE_CODE);
        if(StringUtils.isBlank(trueCode)){
            return ResponseEntity.failure("验证码超时");
        }
        if(StringUtils.isBlank(code) || !trueCode.toLowerCase().equals(code.toLowerCase())){
            return ResponseEntity.failure("验证码错误");
        }else {
            /*当前用户*/
            String errorMsg = null;
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            try {
                SecurityUtils.getSubject().login(token);
                log.debug(username+"用户"+LocalDate.now().toString()+":======》登陆系统!");
                // 设置shiro的session过期时间
                SecurityUtils.getSubject().getSession().setTimeout(serverProperties.getServlet().getSession().getTimeout().toMillis());
            }catch (IncorrectCredentialsException e) {
                errorMsg = "用户名密码错误!";
            }catch (UnknownAccountException e) {
                errorMsg = "账户不存在!";
            }catch (LockedAccountException e) {
                errorMsg = "账户已被锁定!";
            }

            if(StringUtils.isBlank(errorMsg)) {
                ResponseEntity responseEntity = ResponseEntity.success();
                responseEntity.setAny("url","index");
                return responseEntity;
            }else {
                return ResponseEntity.failure(errorMsg);
            }
        }
    }

}
