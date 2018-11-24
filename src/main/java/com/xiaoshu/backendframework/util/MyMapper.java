package com.xiaoshu.backendframework.util;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
import java.util.Map;

public interface MyMapper<T> extends Mapper<T> , MySqlMapper<T> {

    Integer count(@Param("params") Map<String,Object> params);

    List<T> selectList(@Param("params") Map<String,Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
