package com.xiaoshu.backendframework.controller;


import com.xiaoshu.backendframework.annotation.LogAnnotation;
import com.xiaoshu.backendframework.dto.RoleDto;
import com.xiaoshu.backendframework.model.SysRole;
import com.xiaoshu.backendframework.page.table.PageTableHandler;
import com.xiaoshu.backendframework.page.table.PageTableRequest;
import com.xiaoshu.backendframework.page.table.PageTableResponse;
import com.xiaoshu.backendframework.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色")
@RestController
@RequestMapping("/roles")
public class SysRoleController {

    @Autowired
    private SysRoleService roleService;

    @GetMapping
    @ApiOperation(value = "角色列表")
    @RequiresPermissions("sys:role:query")
    public PageTableResponse listRoles(PageTableRequest request) {
        PageTableHandler.CountHandler countHandler = (r) -> roleService.selectConditionCount(r.getParams());
        PageTableHandler.ListHandler listHandler = (r) -> {
            return roleService.selectConditionList(r.getParams());
        };

        return new PageTableHandler(countHandler,listHandler).handle(request);
    }

    @GetMapping("/all")
    @ApiOperation(value = "所有角色")
    @RequiresPermissions(value = { "sys:user:query", "sys:role:query" }, logical = Logical.OR)
    public List<SysRole> roles() {
        return roleService.selectAll();
    }

    @GetMapping(params = "userId")
    @ApiOperation(value = "根据用户id获取拥有的角色")
    @RequiresPermissions(value = { "sys:user:query", "sys:role:query" }, logical = Logical.OR)
    public List<SysRole> roles(Long userId) {
        return roleService.selectByUserId(userId);
    }

    @LogAnnotation
    @PostMapping
    @ApiOperation(value = "保存角色")
    @RequiresPermissions("sys:role:add")
    public void saveRole(@RequestBody RoleDto roleDto) {
        roleService.saveRole(roleDto);
    }

    @LogAnnotation
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除角色")
    @RequiresPermissions(value = { "sys:role:del" })
    public void delete(@PathVariable Long id) {
        roleService.deleteRole(id);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取角色")
    @RequiresPermissions("sys:role:query")
    public SysRole get(@PathVariable Long id) {
        return roleService.getById(id);
    }

}
