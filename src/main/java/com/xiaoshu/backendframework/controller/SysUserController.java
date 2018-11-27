package com.xiaoshu.backendframework.controller;


import com.xiaoshu.backendframework.annotation.LogAnnotation;
import com.xiaoshu.backendframework.dto.UserDto;
import com.xiaoshu.backendframework.model.SysUser;
import com.xiaoshu.backendframework.page.table.PageTableHandler;
import com.xiaoshu.backendframework.page.table.PageTableRequest;
import com.xiaoshu.backendframework.page.table.PageTableResponse;
import com.xiaoshu.backendframework.service.SysUserService;
import com.xiaoshu.backendframework.util.UserUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class SysUserController {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private SysUserService userService;

    /**
     * 当前登录用户
     * @return
     */
    @GetMapping("/current")
    public SysUser currentUser() {
        return UserUtil.getCurrentUser();
    }

    @GetMapping
    @RequiresPermissions("sys:user:query")
    public PageTableResponse listUsers(PageTableRequest request) {

        PageTableHandler.CountHandler countHandler = (r) -> userService.selectConditionCount(r.getParams());
        PageTableHandler.ListHandler listHandler = (r) -> {
            return userService.selectConditionList(r.getParams());
        };

        return new PageTableHandler(countHandler,listHandler).handle(request);
    }

    @PostMapping
    @LogAnnotation(module = "保存用户")
    @RequiresPermissions("sys:user:add")
    public SysUser saveUser(@RequestBody UserDto userDto) {
        SysUser user = userService.getUser(userDto.getUsername());
        if (user != null) {
            throw new IllegalArgumentException(userDto.getUsername() + "已存在");
        }

        return userService.saveUser(userDto);
    }

    @PutMapping
    @LogAnnotation(module = "修改用户")
    @RequiresPermissions("sys:user:add")
    public SysUser updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @PutMapping("/{username}")
    @LogAnnotation(module = "修改密码")
    @RequiresPermissions("sys:user:password")
    public void changePassword(String oldPassword, String newPassword, @PathVariable String username) {
        userService.changePassword(username, oldPassword, newPassword);
    }

    @GetMapping("/{id}")
    public SysUser get(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PutMapping(params = "headImgUrl")
    @LogAnnotation(module = "修改头像")
    public void updateHeadImgUrl(String headImgUrl) {
        SysUser user = UserUtil.getCurrentUser();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        userDto.setHeadImgUrl(headImgUrl);

        userService.updateUser(userDto);
        log.debug("{}修改了头像", user.getUsername());
    }
}
