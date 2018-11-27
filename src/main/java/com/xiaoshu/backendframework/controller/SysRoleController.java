package com.xiaoshu.backendframework.controller;


import com.xiaoshu.backendframework.annotation.LogAnnotation;
import com.xiaoshu.backendframework.dto.RoleDto;
import com.xiaoshu.backendframework.model.SysRole;
import com.xiaoshu.backendframework.page.table.PageTableHandler;
import com.xiaoshu.backendframework.page.table.PageTableRequest;
import com.xiaoshu.backendframework.page.table.PageTableResponse;
import com.xiaoshu.backendframework.service.SysRoleService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class SysRoleController {

    @Autowired
    private SysRoleService roleService;

    @GetMapping
    @RequiresPermissions("sys:role:query")
    public PageTableResponse listRoles(PageTableRequest request) {
        PageTableHandler.CountHandler countHandler = (r) -> roleService.selectConditionCount(r.getParams());
        PageTableHandler.ListHandler listHandler = (r) -> {
            return roleService.selectConditionList(r.getParams());
        };

        return new PageTableHandler(countHandler,listHandler).handle(request);
    }

    @GetMapping("/all")
    @RequiresPermissions(value = { "sys:user:query", "sys:role:query" }, logical = Logical.OR)
    public List<SysRole> roles() {
        return roleService.selectAll();
    }

    /**
     * 根据用户id获取拥有的角色
     * @param userId
     * @return
     */
    @GetMapping(params = "userId")
    @RequiresPermissions(value = { "sys:user:query", "sys:role:query" }, logical = Logical.OR)
    public List<SysRole> roles(Long userId) {
        return roleService.selectByUserId(userId);
    }

    @LogAnnotation(module = "保存角色")
    @PostMapping
    @RequiresPermissions("sys:role:add")
    public void saveRole(@RequestBody RoleDto roleDto) {
        roleService.saveRole(roleDto);
    }

    @LogAnnotation(module = "删除角色")
    @DeleteMapping("/{id}")
    @RequiresPermissions(value = { "sys:role:del" })
    public void delete(@PathVariable Long id) {
        roleService.deleteRole(id);
    }

    /**
     * 根据id获取角色
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @RequiresPermissions("sys:role:query")
    public SysRole get(@PathVariable Long id) {
        return roleService.getById(id);
    }

}
