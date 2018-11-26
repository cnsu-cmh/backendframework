package com.xiaoshu.backendframework.service.impl;

import com.xiaoshu.backendframework.mapper.SysLogsMapper;
import com.xiaoshu.backendframework.model.SysLogs;
import com.xiaoshu.backendframework.model.SysUser;
import com.xiaoshu.backendframework.service.SysLogsService;
import com.xiaoshu.backendframework.util.ObjectUtils;
import com.xiaoshu.backendframework.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysLogsServiceImpl implements SysLogsService {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    SysLogsMapper sysLogsMapper;

    @Override
    public Integer selectConditionCount(Map<String, Object> params) {
        return sysLogsMapper.conditionCount(params);
    }

    @Override
    public List<SysLogs> selectConditionList(Map<String, Object> params,Integer offset,Integer limit) {
        return sysLogsMapper.conditionList(params,offset,limit);
    }

    @Override
    public void save(SysLogs sysLogs) {
        SysUser user = UserUtil.getCurrentUser();
        if (user == null || user.getId() == null) {
            return;
        }

        sysLogs.setUser(user);
        sysLogsMapper.save(sysLogs);
    }

    @Async
    @Override
    public void save(Long userId, String module, Boolean flag, String remark) {
        SysLogs sysLogs = new SysLogs();
        sysLogs.setFlag(flag);
        sysLogs.setModule(module);
        sysLogs.setRemark(remark);

        SysUser user = new SysUser();
        user.setId(userId);
        sysLogs.setUser(user);

        sysLogsMapper.save(sysLogs);

    }

}
