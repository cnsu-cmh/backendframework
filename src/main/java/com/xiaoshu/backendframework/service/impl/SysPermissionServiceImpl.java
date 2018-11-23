package com.xiaoshu.backendframework.service.impl;

import com.xiaoshu.backendframework.mapper.SysPermissionMapper;
import com.xiaoshu.backendframework.model.SysPermission;
import com.xiaoshu.backendframework.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    SysPermissionMapper permissionMapper;

    @Override
    public List<SysPermission> selectByUserId(Long userId) {
        return permissionMapper.selectByUserId(userId);
    }
}
