package com.xiaoshu.backendframework.service;

import com.xiaoshu.backendframework.dto.UserDto;
import com.xiaoshu.backendframework.model.SysUser;

import java.util.List;
import java.util.Map;

public interface SysUserService {


    String passwordEncoder(String credentials, String salt);

    SysUser getUser(String username);

    Integer count(Map<String,Object> params);

    List<SysUser> selectList(Map<String,Object> params, Integer offset, Integer limit);
}
