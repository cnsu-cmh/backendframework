package com.xiaoshu.backendframework.util;

import com.xiaoshu.backendframework.model.SysUser;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
import java.util.Map;

public interface MyMapper<T> extends Mapper<T> , MySqlMapper<T> , MySelectConditionMapper<T> {

}
