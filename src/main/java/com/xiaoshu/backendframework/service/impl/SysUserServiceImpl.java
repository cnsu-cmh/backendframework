package com.xiaoshu.backendframework.service.impl;

import com.xiaoshu.backendframework.constants.UserConstants;
import com.xiaoshu.backendframework.dto.UserDto;
import com.xiaoshu.backendframework.mapper.SysUserMapper;
import com.xiaoshu.backendframework.model.SysUser;
import com.xiaoshu.backendframework.service.SysUserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public String passwordEncoder(String credentials, String salt) {
        Object object = new SimpleHash(UserConstants.HASH_ALGORITHM, credentials, salt, UserConstants.HASH_ITERATIONS);
        return object.toString();
    }

    @Override
    public SysUser getUser(String username) {
        return userMapper.selectOne(new SysUser(username));
    }

    @Override
    public Integer count(Map<String, Object> params) {
        return userMapper.count(params);
    }

    @Override
    public List<SysUser> selectList(Map<String, Object> params, Integer offset, Integer limit) {
        return userMapper.selectList(params, offset, limit);
    }
}
