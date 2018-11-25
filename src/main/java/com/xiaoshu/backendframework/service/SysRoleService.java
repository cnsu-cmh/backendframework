package com.xiaoshu.backendframework.service;

import com.xiaoshu.backendframework.dto.RoleDto;
import com.xiaoshu.backendframework.model.SysRole;

import java.util.List;
import java.util.Map;

public interface SysRoleService {

    List<SysRole> selectAll();

    List<SysRole> selectByUserId(Long userId);

    Integer selectConditionCount(Map<String,Object> params);

    List<SysRole> selectConditionList(Map<String,Object> params);

    void saveRole(RoleDto roleDto);

    void deleteRole(Long id);

    SysRole getById(Long id);
}
