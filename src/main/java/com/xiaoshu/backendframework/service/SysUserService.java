package com.xiaoshu.backendframework.service;

import com.xiaoshu.backendframework.dto.UserDto;
import com.xiaoshu.backendframework.model.SysUser;

import java.util.List;
import java.util.Map;

public interface SysUserService {


    String passwordEncoder(String credentials, String salt);

    SysUser getUser(String username);

    Integer selectConditionCount(Map<String,Object> params);

    List<SysUser> selectConditionList(Map<String,Object> params);

    SysUser updateUser(UserDto userDto);

    SysUser saveUser(UserDto userDto);

    void changePassword(String username, String oldPassword, String newPassword);

    SysUser getById(Long id);
}
