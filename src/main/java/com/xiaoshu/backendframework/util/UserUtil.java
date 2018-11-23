package com.xiaoshu.backendframework.util;

import com.xiaoshu.backendframework.constants.UserConstants;
import com.xiaoshu.backendframework.model.SysPermission;
import com.xiaoshu.backendframework.model.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.List;

public class UserUtil {

    public static SysUser getCurrentUser() {
        SysUser user = (SysUser) getSession().getAttribute(UserConstants.LOGIN_USER);

        return user;
    }

    public static void setUserSession(SysUser user) {
        getSession().setAttribute(UserConstants.LOGIN_USER, user);
    }

    public static List<SysPermission> getCurrentPermissions() {
        List<SysPermission> list = (List<SysPermission>) getSession().getAttribute(UserConstants.USER_PERMISSIONS);

        return list;
    }

    public static void setPermissionSession(List<SysPermission> list) {
        getSession().setAttribute(UserConstants.USER_PERMISSIONS, list);
    }

    public static Session getSession() {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();

        return session;
    }
}
