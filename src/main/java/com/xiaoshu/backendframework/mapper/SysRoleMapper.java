package com.xiaoshu.backendframework.mapper;

import com.xiaoshu.backendframework.model.SysRole;
import com.xiaoshu.backendframework.util.MyMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysRoleMapper extends MyMapper<SysRole> {

    @Select("select * from sys_role r inner join sys_role_user ru on r.id = ru.role_id where ru.user_id = #{userId}")
    List<SysRole> listByUserId(Long userId);
}
