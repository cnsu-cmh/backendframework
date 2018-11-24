package com.xiaoshu.backendframework.controller;


import com.xiaoshu.backendframework.service.SysRoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "角色")
@RestController
@RequestMapping("/roles")
public class SysRoleController {

    @Autowired
    private SysRoleService roleService;
}
