package com.xiaoshu.backendframework.service.impl;

import com.xiaoshu.backendframework.model.SysLogs;
import com.xiaoshu.backendframework.service.SysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl implements SysLogService {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Override
    public void save(SysLogs sysLogs) {

    }

    @Override
    public void save(Long userId, String module, Boolean flag, String remark) {

    }

    @Override
    public void deleteLogs() {

    }
}
