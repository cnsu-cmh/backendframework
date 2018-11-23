package com.xiaoshu.backendframework.controller;

import com.xiaoshu.backendframework.model.SysPermission;
import com.xiaoshu.backendframework.model.SysUser;
import com.xiaoshu.backendframework.service.SysPermissionService;
import com.xiaoshu.backendframework.util.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "权限")
@RestController
@RequestMapping("/permissions")
public class SysPermissionController {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private SysPermissionService permissionService;


    @ApiOperation(value = "当前登录用户拥有的权限")
    @GetMapping("/current")
    public List<SysPermission> permissionsCurrent() {
        List<SysPermission> list = UserUtil.getCurrentPermissions();
        if (list == null) {
            SysUser user = UserUtil.getCurrentUser();
            list = permissionService.selectByUserId(user.getId());
            UserUtil.setPermissionSession(list);
        }
        final List<SysPermission> permissions = list.stream().filter(l -> l.getType().equals(1))
                .collect(Collectors.toList());

        List<SysPermission> firstLevel = permissions.stream().filter(p -> p.getParentId().equals(0L)).collect(Collectors.toList());
        firstLevel.parallelStream().forEach(p -> {
            setChild(p, permissions);
        });

        return firstLevel;
    }

    private void setChild(SysPermission p, List<SysPermission> permissions) {
        List<SysPermission> child = permissions.parallelStream().filter(a -> a.getParentId().equals(p.getId())).collect(Collectors.toList());
        p.setChild(child);
        if (!CollectionUtils.isEmpty(child)) {
            child.parallelStream().forEach(c -> {
                //递归设置子元素，多级菜单支持
                setChild(c, permissions);
            });
        }
    }

}
