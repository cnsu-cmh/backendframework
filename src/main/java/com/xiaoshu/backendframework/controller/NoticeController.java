package com.xiaoshu.backendframework.controller;

import java.util.List;

import com.xiaoshu.backendframework.model.Notice;
import com.xiaoshu.backendframework.model.SysUser;
import com.xiaoshu.backendframework.service.NoticeService;
import com.xiaoshu.backendframework.util.UserUtil;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "公告")
@RestController
@RequestMapping("/notices")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@ApiOperation(value = "未读公告数")
	@GetMapping("/countUnread")
	public Integer countUnread() {
		SysUser user = UserUtil.getCurrentUser();
		return noticeService.countUnread(user.getId());
	}

}
