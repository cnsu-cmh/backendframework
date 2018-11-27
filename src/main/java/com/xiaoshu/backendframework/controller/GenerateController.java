package com.xiaoshu.backendframework.controller;


import com.xiaoshu.backendframework.dto.BeanField;
import com.xiaoshu.backendframework.dto.GenerateDetail;
import com.xiaoshu.backendframework.dto.GenerateInput;
import com.xiaoshu.backendframework.service.GenerateService;
import com.xiaoshu.backendframework.util.ConfigUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generate")
public class GenerateController {

    @Autowired
    private GenerateService generateService;

    /**
     * 代码生成,根据表名显示表信息
     * @param tableName
     * @return
     */
    @GetMapping(params = { "tableName" })
    @RequiresPermissions("generate:edit")
    public GenerateDetail generateByTableName(String tableName) {
        if(StringUtils.isBlank(tableName)){
            throw new IllegalArgumentException("tableName表名称不能为空");
        }
        ConfigUtil configUtil = ConfigUtil.getInstance("generator/bgm.properties");
        String explorerDir = (String)configUtil.getValue("explorerDir");
        GenerateDetail detail = new GenerateDetail(explorerDir);
        detail.setBeanName(generateService.upperFirstChar(tableName));
        List<BeanField> fields = generateService.selectBeanField(tableName);
        detail.setFields(fields);
        return detail;
    }

    /**
     * 生成代码
     * @param input
     */
    @PostMapping
    @RequiresPermissions("generate:edit")
    public void save(@RequestBody GenerateInput input) {
        generateService.saveCode(input);
    }
}
