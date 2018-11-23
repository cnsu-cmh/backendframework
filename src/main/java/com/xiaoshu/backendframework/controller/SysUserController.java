package com.xiaoshu.backendframework.controller;


import com.xiaoshu.backendframework.model.SysUser;
import com.xiaoshu.backendframework.page.table.PageTableHandler;
import com.xiaoshu.backendframework.page.table.PageTableRequest;
import com.xiaoshu.backendframework.page.table.PageTableResponse;
import com.xiaoshu.backendframework.service.SysUserService;
import com.xiaoshu.backendframework.util.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "用户")
@RestController
@RequestMapping("/users")
public class SysUserController {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private SysUserService userService;

    @ApiOperation(value = "当前登录用户")
    @GetMapping("/current")
    public SysUser currentUser() {
        return UserUtil.getCurrentUser();
    }

    @GetMapping
    @ApiOperation(value = "用户列表")
    @RequiresPermissions("sys:user:query")
    public PageTableResponse listUsers(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return userService.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {

            @Override
            public List<SysUser> list(PageTableRequest request) {
                List<SysUser> list = userService.selectList(request.getParams(), request.getOffset(), request.getLimit());
                return list;
            }
        }).handle(request);
    }
}
