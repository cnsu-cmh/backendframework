package com.xiaoshu.backendframework.service;

import com.xiaoshu.backendframework.model.SysLogs;

import java.util.List;
import java.util.Map;

public interface SysLogsService {

    Integer selectConditionCount(Map<String,Object> params);

    List<SysLogs> selectConditionList(Map<String,Object> params,Integer offset,Integer limit);

    void save(Long id, String module, Boolean flag, String o);

    void save(SysLogs sysLogs);
}
