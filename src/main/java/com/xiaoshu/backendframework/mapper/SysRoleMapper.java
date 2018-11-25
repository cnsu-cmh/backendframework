package com.xiaoshu.backendframework.mapper;

import com.xiaoshu.backendframework.model.SysRole;
import com.xiaoshu.backendframework.util.MyMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysRoleMapper extends MyMapper<SysRole> {

    @Select("select * from sys_role r inner join sys_role_user ru on r.id = ru.role_id where ru.user_id = #{userId}")
    List<SysRole> selectByUserId(@Param("userId") Long userId);

    @Delete("delete from sys_role_permission where role_id = #{roleId}")
    Integer deleteRolePermission(Long roleId);

    Integer saveRolePermission(@Param("roleId") Long roleId, @Param("permissionIds") List<Long> permissionIds);

    @Delete("delete from sys_role_user where role_id = #{roleId}")
    Integer deleteRoleUser(Long roleId);
}
