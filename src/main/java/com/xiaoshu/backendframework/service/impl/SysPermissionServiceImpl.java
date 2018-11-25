package com.xiaoshu.backendframework.service.impl;

import com.xiaoshu.backendframework.mapper.SysPermissionMapper;
import com.xiaoshu.backendframework.model.SysPermission;
import com.xiaoshu.backendframework.service.SysPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    SysPermissionMapper permissionMapper;

    @Override
    public List<SysPermission> selectByUserId(Long userId) {
        return permissionMapper.selectByUserId(userId);
    }

    @Override
    public void update(SysPermission permission) {
        permissionMapper.updateByPrimaryKeySelective(permission);
        log.debug("修改菜单{}", permission.getName());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        //删除与角色的关系
        permissionMapper.deleteRolePermission(id);
        //删除自己
        permissionMapper.deleteByPrimaryKey(id);
        SysPermission sysPermission = new SysPermission();
        sysPermission.setParentId(id);
        //删除子权限
        permissionMapper.delete(sysPermission);
        log.debug("删除菜单id:{}", id);
    }

    @Override
    public SysPermission getById(Long id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(SysPermission permission) {
        permissionMapper.insertSelective(permission);
        log.debug("新增菜单{}", permission.getName());
    }

    @Override
    public List<SysPermission> selectByRoleId(Long roleId) {
        return permissionMapper.selectByRoleId(roleId);
    }

    @Override
    public List<SysPermission> selectParents() {
        Example example = new Example(SysPermission.class);
        example.createCriteria().andEqualTo("type",1);
        example.orderBy("sort").desc();
        return permissionMapper.selectByExample(example);
    }

    @Override
    public List<SysPermission> selecttAll() {
        Example example = new Example(SysPermission.class);
        example.orderBy("sort").desc();
        return permissionMapper.selectByExample(example);
    }
}
