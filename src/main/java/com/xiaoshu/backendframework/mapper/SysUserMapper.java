package com.xiaoshu.backendframework.mapper;

import com.xiaoshu.backendframework.model.SysUser;
import com.xiaoshu.backendframework.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysUserMapper extends MyMapper<SysUser> {

    Integer count(@Param("params") Map<String,Object> params);

    List<SysUser> selectList(@Param("params") Map<String,Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
