package com.xiaoshu.backendframework.service;

import com.xiaoshu.backendframework.model.SysPermission;

import java.util.List;

public interface SysPermissionService {

    List<SysPermission> selectByUserId(Long userId);
}
