package com.xiaoshu.backendframework.service.impl;

import com.xiaoshu.backendframework.dto.RoleDto;
import com.xiaoshu.backendframework.mapper.SysRoleMapper;
import com.xiaoshu.backendframework.model.SysRole;
import com.xiaoshu.backendframework.service.SysRoleService;
import com.xiaoshu.backendframework.util.ObjectUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public List<SysRole> selectAll() {
        return roleMapper.selectAll();
    }

    @Override
    public List<SysRole> selectByUserId(Long userId) {
        return roleMapper.selectByUserId(userId);
    }

    @Override
    public Integer selectConditionCount(Map<String, Object> params) {
        SysRole role = (SysRole)ObjectUtils.mapToObject(params, SysRole.class);
        return roleMapper.selectConditionCount(role);
    }

    @Override
    public List<SysRole> selectConditionList(Map<String, Object> params) {
        SysRole role = (SysRole)ObjectUtils.mapToObject(params, SysRole.class);
        return  roleMapper.selectConditionList(role);
    }

    @Override
    @Transactional
    public void saveRole(RoleDto roleDto) {
        RoleDto role = roleDto;
        SysRole re = new SysRole(role.getName());
        SysRole r = roleMapper.selectOne(re);
        if (r != null && r.getId() != role.getId()) {
            throw new IllegalArgumentException(role.getName() + "已存在");
        }
        if (role.getId() != null) {// 修改
            roleMapper.updateByPrimaryKeySelective(role);
            log.debug("修改角色{}", role.getName());
        } else {// 新增
            roleMapper.insert(role);
            log.debug("新增角色{}", role.getName());
        }
        saveRolePermission(role.getId(), roleDto.getPermissionIds());
    }

    public void saveRolePermission(Long roleId, List<Long> permissionIds) {
        roleMapper.deleteRolePermission(roleId);
        permissionIds.remove(0L);
        if (!CollectionUtils.isEmpty(permissionIds)) {
            roleMapper.saveRolePermission(roleId, permissionIds);
        }
    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        roleMapper.deleteRolePermission(id);
        roleMapper.deleteRoleUser(id);
        roleMapper.deleteByPrimaryKey(id);
        log.debug("删除角色id:{}", id);
    }

    @Override
    public SysRole getById(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }
}
