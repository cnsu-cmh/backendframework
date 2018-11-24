package com.xiaoshu.backendframework.controller;

import com.xiaoshu.backendframework.util.ConfigUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/pages")
public class PagesController {

    /***----------------------------dashboard-------------------------------*/

    @RequestMapping("/dashboard")
    public String dashboard(){
        return "dashboard";
    }


    /***----------------------------generate-------------------------------*/

    @RequestMapping("/generate")
    public String generate(){
        return "generate/generate";
    }



    /***----------------------------user-------------------------------*/

    @RequestMapping("/user/userList")
    public String user(){
        return "user/userList";
    }
    @RequestMapping("/user/changePassword")
    public String changePassword(){
        return "user/changePassword";
    }

    @RequestMapping("/user/addUser")
    public String addUser(){
        return "user/addUser";
    }
    
    @RequestMapping("/user/updateUser")
    public String updateUser(){
        return "user/updateUser";
    }

}
