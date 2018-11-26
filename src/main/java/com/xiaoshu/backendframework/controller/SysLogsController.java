package com.xiaoshu.backendframework.controller;


import com.xiaoshu.backendframework.model.SysLogs;
import com.xiaoshu.backendframework.page.table.PageTableHandler;
import com.xiaoshu.backendframework.page.table.PageTableRequest;
import com.xiaoshu.backendframework.page.table.PageTableResponse;
import com.xiaoshu.backendframework.service.SysLogsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "日志")
@RestController
@RequestMapping("/logs")
public class SysLogsController {

    @Autowired
    private SysLogsService sysLogsService;

    @GetMapping
    @RequiresPermissions(value = "sys:log:query")
    @ApiOperation(value = "日志列表")
    public PageTableResponse list(PageTableRequest request) {

        PageTableHandler.CountHandler countHandler = (r) -> sysLogsService.selectConditionCount(r.getParams());
        PageTableHandler.ListHandler listHandler = (r) -> {
            return sysLogsService.selectConditionList(r.getParams(),r.getOffset(), r.getLimit());
        };

        return new PageTableHandler(countHandler,listHandler).handle(request);

    }

}
