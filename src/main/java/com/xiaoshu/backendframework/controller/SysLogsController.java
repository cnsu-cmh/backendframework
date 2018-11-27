package com.xiaoshu.backendframework.controller;


import com.xiaoshu.backendframework.page.table.PageTableHandler;
import com.xiaoshu.backendframework.page.table.PageTableRequest;
import com.xiaoshu.backendframework.page.table.PageTableResponse;
import com.xiaoshu.backendframework.service.SysLogsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/logs")
public class SysLogsController {

    @Autowired
    private SysLogsService sysLogsService;

    @GetMapping
    @RequiresPermissions(value = "sys:log:query")
    public PageTableResponse list(PageTableRequest request) {

        PageTableHandler.CountHandler countHandler = (r) -> sysLogsService.selectConditionCount(r.getParams());
        PageTableHandler.ListHandler listHandler = (r) -> {
            return sysLogsService.selectConditionList(r.getParams(),r.getOffset(), r.getLimit());
        };

        return new PageTableHandler(countHandler,listHandler).handle(request);

    }

}
