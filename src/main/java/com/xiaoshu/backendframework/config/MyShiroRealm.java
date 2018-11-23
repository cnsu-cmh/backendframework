package com.xiaoshu.backendframework.config;

import com.xiaoshu.backendframework.mapper.SysPermissionMapper;
import com.xiaoshu.backendframework.mapper.SysRoleMapper;
import com.xiaoshu.backendframework.model.SysPermission;
import com.xiaoshu.backendframework.model.SysRole;
import com.xiaoshu.backendframework.model.SysUser;
import com.xiaoshu.backendframework.service.SysUserService;
import com.xiaoshu.backendframework.util.SpringUtil;
import com.xiaoshu.backendframework.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MyShiroRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.debug("权限配置");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser user = UserUtil.getCurrentUser();
        List<SysRole> roles = SpringUtil.getBean(SysRoleMapper.class).listByUserId(user.getId());
        Set<String> roleNames = roles.stream().map(SysRole::getName).collect(Collectors.toSet());
        authorizationInfo.setRoles(roleNames);
        List<SysPermission> permissionList = SpringUtil.getBean(SysPermissionMapper.class).selectByUserId(user.getId());
        UserUtil.setPermissionSession(permissionList);
        Set<String> permissions = permissionList.stream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
                .map(SysPermission::getPermission).collect(Collectors.toSet());
        authorizationInfo.setStringPermissions(permissions);

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        String username = usernamePasswordToken.getUsername();
        SysUserService userService = SpringUtil.getBean(SysUserService.class);
        SysUser user = userService.getUser(username);
        if (user == null) {
            throw new UnknownAccountException("用户名不存在");
        }

        if (!user.getPassword().equals(userService.passwordEncoder(new String(usernamePasswordToken.getPassword()), user.getSalt()))) {
            throw new IncorrectCredentialsException("密码错误");
        }

        if (user.getStatus() != SysUser.Status.VALID) {
            throw new IncorrectCredentialsException("无效状态，请联系管理员");
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()), getName());

        UserUtil.setUserSession(user);

        return authenticationInfo;
    }
}
