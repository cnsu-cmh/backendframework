package com.xiaoshu.backendframework.controller;

import java.util.List;

import com.xiaoshu.backendframework.model.Dict;
import com.xiaoshu.backendframework.service.DictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/dicts")
public class DictController {

	@Autowired
	private DictService dictService;

	@GetMapping(params = "type")
	public List<Dict> listByType(String type) {
		return dictService.selectByType(type);
	}
}
