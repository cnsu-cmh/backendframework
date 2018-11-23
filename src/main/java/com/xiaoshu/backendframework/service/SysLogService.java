package com.xiaoshu.backendframework.service;

import com.xiaoshu.backendframework.model.SysLogs;

public interface SysLogService {

    void save(SysLogs sysLogs);

    void save(Long userId, String module, Boolean flag, String remark);

    void deleteLogs();
}
