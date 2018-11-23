package com.xiaoshu.backendframework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages")
public class PagesController {


    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard";
    }

    @GetMapping("/generate")
    public String generate(){
        return "generate/generate";
    }

    @GetMapping("/user/userList")
    public String user(){
        return "user/userList";
    }



}
