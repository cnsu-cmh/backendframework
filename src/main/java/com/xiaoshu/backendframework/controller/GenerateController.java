package com.xiaoshu.backendframework.controller;


import com.xiaoshu.backendframework.annotation.LogAnnotation;
import com.xiaoshu.backendframework.dto.BeanField;
import com.xiaoshu.backendframework.dto.GenerateDetail;
import com.xiaoshu.backendframework.dto.GenerateInput;
import com.xiaoshu.backendframework.service.GenerateService;
import com.xiaoshu.backendframework.util.ConfigUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "代码生成")
@RestController
@RequestMapping("/generate")
public class GenerateController {

    @Autowired
    private GenerateService generateService;

    @ApiOperation("根据表名显示表信息")
    @GetMapping(params = { "tableName" })
    @RequiresPermissions("generate:edit")
    public GenerateDetail generateByTableName(String tableName) {
        ConfigUtil configUtil = ConfigUtil.getInstance("generator/bgm.properties");
        String explorerDir = (String)configUtil.getValue("explorerDir");
        GenerateDetail detail = new GenerateDetail(explorerDir);
        detail.setBeanName(generateService.upperFirstChar(tableName));
        List<BeanField> fields = generateService.selectBeanField(tableName);
        detail.setFields(fields);
        return detail;
    }

    @LogAnnotation
    @ApiOperation("生成代码")
    @PostMapping
    @RequiresPermissions("generate:edit")
    public void save(@RequestBody GenerateInput input) {
        generateService.saveCode(input);
    }
}
