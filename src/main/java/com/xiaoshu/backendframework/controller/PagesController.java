package com.xiaoshu.backendframework.controller;

import com.xiaoshu.backendframework.constants.UserConstants;
import com.xiaoshu.backendframework.model.SysUser;
import com.xiaoshu.backendframework.util.UserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/pages")
public class PagesController {

    /***----------------------------dashboard-------------------------------*/

    @RequestMapping("/dashboard")
    public String dashboard(ModelMap map){
        SysUser user = UserUtil.getCurrentUser();
        String passwordEncoder = UserUtil.passwordEncoder(UserConstants.DEFAULT_PASSWORD, user.getSalt());
        map.put("isDefaultPassword",user.getPassword().equals(passwordEncoder));
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
    public String addUser(ModelMap map){
        map.put("defaultPassword",UserConstants.DEFAULT_PASSWORD);
        return "user/addUser";
    }
    
    @RequestMapping("/user/updateUser")
    public String updateUser(){
        return "user/updateUser";
    }

    @RequestMapping("/user/updateMyself")
    public String updateMyself(){
        return "user/updateMyself";
    }

    @RequestMapping("/user/updateHeadImg")
    public String updateHeadImg(){
        return "user/updateHeadImg";
    }




   /***------------------------------role----------------*/
   @RequestMapping("/role/roleList")
   public String roleList(){
        return "role/roleList";
    }

    @RequestMapping("/role/addRole")
    public String addRole(){
        return "role/addRole";
    }




    /***-----------------------------menu----------------*/
    @RequestMapping("/menu/menuList")
    public String menuList(){
        return "menu/menuList";
    }

    @RequestMapping("/menu/addMenu")
    public String addMenu(){
        return "menu/addMenu";
    }

    @RequestMapping("/menu/icon")
    public String menuIcon(){
        return "menu/icon";
    }

    @RequestMapping("/menu/updateMenu")
    public String updateMenu(){
        return "menu/updateMenu";
    }




    /***-----------------fileInfo-------------------------*/

    @RequestMapping("/fileInfo/fileInfoList")
    public String fileInfoList(){
        return "file/fileList";
    }




    /***-----------------log-------------------------*/
    @RequestMapping("/log/logList")
    public String logList(){
        return "log/logList";
    }




    /***-----------------dict-------------------------*/
    @RequestMapping("/dict/dictList")
    public String dictList(){
        return "dict/dictList";
    }

    @RequestMapping("/dict/addDict")
    public String addDict(){
        return "dict/addDict";
    }

    @RequestMapping("/dict/updateDict")
    public String updateDict(){
        return "dict/updateDict";
    }


    /***-----------------excel-------------------------*/
    @RequestMapping("/excel/sql")
    public String excelSql(){
        return "excel/sql";
    }

    /***-----------------mail-------------------------*/
    @RequestMapping("/mail/mailList")
    public String mailList(){
        return "mail/mailList";
    }

    @RequestMapping("/mail/addMail")
    public String addMail(){
        return "mail/addMail";
    }

    @RequestMapping("/mail/mailDetail")
    public String mailDetail(){
        return "mail/mailDetail";
    }

}
