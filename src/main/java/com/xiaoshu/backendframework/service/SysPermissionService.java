package com.xiaoshu.backendframework.service;

import com.xiaoshu.backendframework.model.SysPermission;

import java.util.List;

public interface SysPermissionService {

    List<SysPermission> selectByUserId(Long userId);

    void update(SysPermission permission);

    void delete(Long id);

    SysPermission getById(Long id);

    void save(SysPermission permission);

    List<SysPermission> selectByRoleId(Long roleId);

    List<SysPermission> selectParents();

    List<SysPermission> selecttAll();

}
