package com.xiaoshu.backendframework.service.impl;

import com.xiaoshu.backendframework.constants.UserConstants;
import com.xiaoshu.backendframework.dto.UserDto;
import com.xiaoshu.backendframework.mapper.SysUserMapper;
import com.xiaoshu.backendframework.model.SysUser;
import com.xiaoshu.backendframework.service.SysUserService;
import com.xiaoshu.backendframework.util.UserUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class SysUserServiceImpl implements SysUserService {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

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

    @Override
    @Transactional
    public SysUser saveUser(UserDto userDto) {
        SysUser user = userDto;
        user.setSalt(DigestUtils.md5Hex(UUID.randomUUID().toString() + System.currentTimeMillis() + UUID.randomUUID().toString()));
        user.setPassword(passwordEncoder(user.getPassword(), user.getSalt()));
        user.setStatus(SysUser.Status.VALID);
        userMapper.insertSelective(user);
        saveUserRoles(user.getId(), userDto.getRoleIds());

        log.debug("新增用户{}", user.getUsername());
        return user;
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) {
        SysUser user = userMapper.selectOne(new SysUser(username));
        if (user == null) {
            throw new IllegalArgumentException("用户不存在");
        }

        if (!user.getPassword().equals(passwordEncoder(oldPassword, user.getSalt()))) {
            throw new IllegalArgumentException("密码错误");
        }

        userMapper.changePassword(user.getId(), passwordEncoder(newPassword,user.getSalt()));

        log.debug("修改{}的密码", username);
    }

    private void saveUserRoles(Long userId, List<Long> roleIds) {
        if (roleIds != null) {
            userMapper.deleteUserRole(userId);
            if (!CollectionUtils.isEmpty(roleIds)) {
                userMapper.saveUserRoles(userId, roleIds);
            }
        }
    }

    @Override
    @Transactional
    public SysUser updateUser(UserDto userDto) {
        userMapper.updateByPrimaryKeySelective(userDto);
        saveUserRoles(userDto.getId(), userDto.getRoleIds());
        updateUserSession(userDto.getId());

        return userDto;
    }

    private void updateUserSession(Long id) {
        SysUser current = UserUtil.getCurrentUser();
        if (current.getId().equals(id)) {
            SysUser user = userMapper.selectByPrimaryKey(id);
            UserUtil.setUserSession(user);
        }
    }


}
