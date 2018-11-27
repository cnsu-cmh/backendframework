package com.xiaoshu.backendframework.controller.api;

import com.xiaoshu.backendframework.model.Dict;
import com.xiaoshu.backendframework.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "接口")
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private DictService dictService;

    @GetMapping(value = "/dict", params = "type" )
    @ResponseBody
    @ApiOperation(value = "根据type获取字典")
    public List<Dict> listByType(String type) {
        return dictService.selectByType(type);
    }

}
