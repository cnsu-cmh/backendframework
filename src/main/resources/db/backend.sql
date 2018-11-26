/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50137
Source Host           : localhost:3306
Source Database       : backend

Target Server Type    : MYSQL
Target Server Version : 50137
File Encoding         : 65001

Date: 2018-11-26 21:31:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(16) NOT NULL,
  `k` varchar(16) NOT NULL,
  `val` varchar(64) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`,`k`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dict
-- ----------------------------
INSERT INTO `dict` VALUES ('1', 'sex', '0', '女', '2017-11-17 09:58:24', '2017-11-18 14:21:05');
INSERT INTO `dict` VALUES ('2', 'sex', '1', '男', '2017-11-17 10:03:46', '2017-11-17 10:03:46');
INSERT INTO `dict` VALUES ('3', 'userStatus', '0', '无效', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
INSERT INTO `dict` VALUES ('4', 'userStatus', '1', '正常', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
INSERT INTO `dict` VALUES ('5', 'userStatus', '2', '锁定', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
INSERT INTO `dict` VALUES ('6', 'noticeStatus', '0', '草稿', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
INSERT INTO `dict` VALUES ('7', 'noticeStatus', '1', '发布', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
INSERT INTO `dict` VALUES ('8', 'isRead', '0', '未读', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
INSERT INTO `dict` VALUES ('9', 'isRead', '1', '已读', '2017-11-17 16:26:06', '2017-11-17 16:26:09');

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `md5` varchar(32) NOT NULL COMMENT '文件md5',
  `content_type` varchar(128) NOT NULL,
  `size` int(11) NOT NULL,
  `path` varchar(255) NOT NULL COMMENT '物理路径',
  `url` varchar(1024) NOT NULL,
  `type` int(1) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file_info
-- ----------------------------
INSERT INTO `file_info` VALUES ('2', '40623f731b28cec4cf3fcbf74135738d', 'image/jpeg', '73379', 'd:/files/2018/11/26/40623f731b28cec4cf3fcbf74135738d.jpg', '/2018/11/26/40623f731b28cec4cf3fcbf74135738d.jpg', '1', '2018-11-26 13:27:44', '2018-11-26 13:27:44');
INSERT INTO `file_info` VALUES ('3', '87ec56bb57601be3ec03ec754794f20d', 'image/jpeg', '22828', 'd:/files/2018/11/26/87ec56bb57601be3ec03ec754794f20d.jpg', '/2018/11/26/87ec56bb57601be3ec03ec754794f20d.jpg', '1', '2018-11-26 13:30:26', '2018-11-26 13:30:26');
INSERT INTO `file_info` VALUES ('5', 'daa920cdd49c964a031c21ed5416bf55', 'image/png', '66183', 'd:/files/2018/11/26/daa920cdd49c964a031c21ed5416bf55.png', '/2018/11/26/daa920cdd49c964a031c21ed5416bf55.png', '1', '2018-11-26 17:46:44', '2018-11-26 18:29:13');
INSERT INTO `file_info` VALUES ('6', 'b938e5910560500565d42f9928510c1c', 'image/jpeg', '51813', 'd:/files/2018/11/26/b938e5910560500565d42f9928510c1c.jpg', '/2018/11/26/b938e5910560500565d42f9928510c1c.jpg', '1', '2018-11-26 18:18:58', '2018-11-26 18:18:58');
INSERT INTO `file_info` VALUES ('7', 'eccc79f0dc56ed930efa28589957b8cc', 'image/jpeg', '12797', 'd:/files/2018/11/26/eccc79f0dc56ed930efa28589957b8cc.jpg', '/2018/11/26/eccc79f0dc56ed930efa28589957b8cc.jpg', '1', '2018-11-26 20:49:36', '2018-11-26 20:49:36');

-- ----------------------------
-- Table structure for mail
-- ----------------------------
DROP TABLE IF EXISTS `mail`;
CREATE TABLE `mail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '发送人',
  `subject` varchar(255) NOT NULL COMMENT '标题',
  `content` longtext NOT NULL COMMENT '正文',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mail
-- ----------------------------
INSERT INTO `mail` VALUES ('1', '1', 'test', '<p><b>sdfsfg </b><i>dsfbgdfgb</i> <u>dfgdfgdfgd</u> <strike>fgdfgdfg<img src=\"http://127.0.0.1:8888/static/layui/images/face/25.gif\" alt=\"[抱抱]\"></strike></p><p><strike><img src=\"http://127.0.0.1:8888/files/2018/11/26/daa920cdd49c964a031c21ed5416bf55.png\" alt=\"20140917201659_mRkAC.thumb.700_0.png\"><br></strike></p><strike></strike>', '2018-11-26 17:53:41', '2018-11-26 17:53:41');
INSERT INTO `mail` VALUES ('2', '1', 'rtert', '<p style=\"text-align: left;\"><b>xdfvgxfdgd</b> <u>zscdsff</u> <i>SDgfdsegf</i> <strike>sefesfedf</strike></p><p><img src=\"http://127.0.0.1:8888/files/2018/11/26/daa920cdd49c964a031c21ed5416bf55.png\" alt=\"20140917201659_mRkAC.thumb.700_0.png\"></p>', '2018-11-26 18:00:57', '2018-11-26 18:00:57');
INSERT INTO `mail` VALUES ('3', '1', 'sdf', '<b>sdfge</b> erfgtd ergfer wefre&nbsp;<img src=\"http://127.0.0.1:8888/static/layui/images/face/13.gif\" alt=\"[偷笑]\">', '2018-11-26 18:13:51', '2018-11-26 18:13:51');
INSERT INTO `mail` VALUES ('4', '1', 'test', '<p><b>sdfsd </b><i>eorgf e</i>rgtw&nbsp; <u>er&nbsp; sdgd</u>&nbsp;fhf<strike>fghfghfghfghfgh<img src=\"http://127.0.0.1:8888/static/layui/images/face/12.gif\" alt=\"[泪]\"></strike></p><p><strike><img src=\"http://127.0.0.1:8888/files/2018/11/26/b938e5910560500565d42f9928510c1c.jpg\" alt=\"u.jpg\"><br></strike></p><strike></strike>', '2018-11-26 18:19:04', '2018-11-26 18:19:04');
INSERT INTO `mail` VALUES ('5', '1', 'wrwe', 'dfgdfgdfg', '2018-11-26 18:21:49', '2018-11-26 18:21:49');
INSERT INTO `mail` VALUES ('6', '1', 'sfsdf', 'sdfgsdfsdf', '2018-11-26 18:25:26', '2018-11-26 18:25:26');
INSERT INTO `mail` VALUES ('7', '1', 'sefrserse', 'sdfsdf', '2018-11-26 18:28:26', '2018-11-26 18:28:26');
INSERT INTO `mail` VALUES ('8', '1', 'qwrwerwer', '<p><b>sdfsdfssfd</b>sdfs w<i>sefsdfs</i> s<u>dfsdfsd</u> <strike>sdfsdfs</strike>&nbsp;<img src=\"http://127.0.0.1:8888/static/layui/images/face/1.gif\" alt=\"[嘻嘻]\"></p><p><img src=\"http://127.0.0.1:8888/files/2018/11/26/daa920cdd49c964a031c21ed5416bf55.png\" alt=\"20140917201659_mRkAC.thumb.700_0.png\"><br></p>', '2018-11-26 18:29:29', '2018-11-26 18:29:29');

-- ----------------------------
-- Table structure for mail_to
-- ----------------------------
DROP TABLE IF EXISTS `mail_to`;
CREATE TABLE `mail_to` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mail_id` int(11) NOT NULL,
  `to_user` varchar(128) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1成功，0失败',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mail_to
-- ----------------------------
INSERT INTO `mail_to` VALUES ('1', '1', '292644410@qq.com', '0');
INSERT INTO `mail_to` VALUES ('2', '2', '292644410@qq.com', '0');
INSERT INTO `mail_to` VALUES ('3', '3', '18206764943@163.com', '0');
INSERT INTO `mail_to` VALUES ('4', '4', '292644410@qq.com', '0');
INSERT INTO `mail_to` VALUES ('5', '5', '292644410@qq.com', '0');
INSERT INTO `mail_to` VALUES ('6', '6', '18206764943', '0');
INSERT INTO `mail_to` VALUES ('7', '7', '18206764943@163.com', '1');
INSERT INTO `mail_to` VALUES ('8', '8', '18206764943@163.com', '1');

-- ----------------------------
-- Table structure for sys_logs
-- ----------------------------
DROP TABLE IF EXISTS `sys_logs`;
CREATE TABLE `sys_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) NOT NULL,
  `module` varchar(50) DEFAULT NULL COMMENT '模块名',
  `flag` tinyint(4) NOT NULL DEFAULT '1' COMMENT '成功失败',
  `remark` text COMMENT '备注',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `create_time` (`create_time`),
  KEY `user_id` (`user`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_logs
-- ----------------------------
INSERT INTO `sys_logs` VALUES ('1', '1', 'web端登陆', '1', null, '2018-11-26 13:59:16');
INSERT INTO `sys_logs` VALUES ('2', '1', '退出', '1', null, '2018-11-26 13:59:31');
INSERT INTO `sys_logs` VALUES ('3', '1', 'web端登陆', '1', null, '2018-11-26 13:59:49');
INSERT INTO `sys_logs` VALUES ('4', '1', 'web端登陆', '1', null, '2018-11-26 14:05:14');
INSERT INTO `sys_logs` VALUES ('5', '1', 'web端登陆', '1', null, '2018-11-26 14:46:32');
INSERT INTO `sys_logs` VALUES ('6', '1', '退出', '1', null, '2018-11-26 14:46:56');
INSERT INTO `sys_logs` VALUES ('7', '1', 'web端登陆', '1', null, '2018-11-26 14:47:04');
INSERT INTO `sys_logs` VALUES ('8', '1', 'web端登陆', '1', null, '2018-11-26 15:11:59');
INSERT INTO `sys_logs` VALUES ('9', '1', 'web端登陆', '1', null, '2018-11-26 15:59:57');
INSERT INTO `sys_logs` VALUES ('10', '1', 'web端登陆', '1', null, '2018-11-26 16:03:33');
INSERT INTO `sys_logs` VALUES ('11', '1', 'web端登陆', '1', null, '2018-11-26 16:21:18');
INSERT INTO `sys_logs` VALUES ('12', '1', '根据sql在页面显示结果', '1', null, '2018-11-26 16:21:27');
INSERT INTO `sys_logs` VALUES ('13', '1', '根据sql在页面显示结果', '1', null, '2018-11-26 16:23:22');
INSERT INTO `sys_logs` VALUES ('14', '1', '根据sql在页面显示结果', '1', null, '2018-11-26 16:23:37');
INSERT INTO `sys_logs` VALUES ('15', '1', '根据sql在页面显示结果', '1', null, '2018-11-26 16:23:40');
INSERT INTO `sys_logs` VALUES ('16', '1', 'web端登陆', '1', null, '2018-11-26 16:32:31');
INSERT INTO `sys_logs` VALUES ('17', '1', '根据sql在页面显示结果', '1', null, '2018-11-26 16:32:37');
INSERT INTO `sys_logs` VALUES ('18', '1', 'web端登陆', '1', null, '2018-11-26 16:35:55');
INSERT INTO `sys_logs` VALUES ('19', '1', '根据sql在页面显示结果', '1', null, '2018-11-26 16:35:59');
INSERT INTO `sys_logs` VALUES ('20', '1', '根据sql导出excel', '1', null, '2018-11-26 16:36:04');
INSERT INTO `sys_logs` VALUES ('21', '1', '退出', '1', null, '2018-11-26 16:44:42');
INSERT INTO `sys_logs` VALUES ('22', '1', 'web端登陆', '1', null, '2018-11-26 16:44:46');
INSERT INTO `sys_logs` VALUES ('23', '1', 'web端登陆', '1', null, '2018-11-26 17:37:47');
INSERT INTO `sys_logs` VALUES ('24', '1', '退出', '1', null, '2018-11-26 17:42:24');
INSERT INTO `sys_logs` VALUES ('25', '1', 'web端登陆', '1', null, '2018-11-26 17:42:31');
INSERT INTO `sys_logs` VALUES ('26', '1', '退出', '1', null, '2018-11-26 17:43:09');
INSERT INTO `sys_logs` VALUES ('27', '1', 'web端登陆', '1', null, '2018-11-26 17:43:14');
INSERT INTO `sys_logs` VALUES ('28', '1', 'layui富文本文件自定义上传', '1', null, '2018-11-26 17:46:44');
INSERT INTO `sys_logs` VALUES ('29', '1', '保存邮件', '0', '\r\n### Error updating database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'to_users\' in \'field list\'\r\n### The error may involve com.xiaoshu.backendframework.mapper.MailMapper.insertUseGeneratedKeys-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO mail  ( create_time,update_time,user_id,to_users,subject,content ) VALUES ( ?,?,?,?,?,? )\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'to_users\' in \'field list\'\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'to_users\' in \'field list\'', '2018-11-26 17:47:21');
INSERT INTO `sys_logs` VALUES ('30', '1', 'web端登陆', '1', null, '2018-11-26 17:51:35');
INSERT INTO `sys_logs` VALUES ('31', '1', 'layui富文本文件自定义上传', '1', null, '2018-11-26 17:52:56');
INSERT INTO `sys_logs` VALUES ('32', '1', '保存邮件', '1', null, '2018-11-26 17:53:50');
INSERT INTO `sys_logs` VALUES ('33', '1', 'layui富文本文件自定义上传', '1', null, '2018-11-26 18:00:28');
INSERT INTO `sys_logs` VALUES ('34', '1', '保存邮件', '1', null, '2018-11-26 18:02:03');
INSERT INTO `sys_logs` VALUES ('35', '1', '保存邮件', '1', null, '2018-11-26 18:14:17');
INSERT INTO `sys_logs` VALUES ('36', '1', 'web端登陆', '1', null, '2018-11-26 18:18:13');
INSERT INTO `sys_logs` VALUES ('37', '1', 'layui富文本文件自定义上传', '1', null, '2018-11-26 18:18:58');
INSERT INTO `sys_logs` VALUES ('38', '1', '保存邮件', '1', null, '2018-11-26 18:19:16');
INSERT INTO `sys_logs` VALUES ('39', '1', '保存邮件', '1', null, '2018-11-26 18:22:15');
INSERT INTO `sys_logs` VALUES ('40', '1', '保存邮件', '1', null, '2018-11-26 18:25:29');
INSERT INTO `sys_logs` VALUES ('41', '1', '保存邮件', '1', null, '2018-11-26 18:28:31');
INSERT INTO `sys_logs` VALUES ('42', '1', 'layui富文本文件自定义上传', '1', null, '2018-11-26 18:29:13');
INSERT INTO `sys_logs` VALUES ('43', '1', '保存邮件', '1', null, '2018-11-26 18:29:31');
INSERT INTO `sys_logs` VALUES ('44', '1', '根据sql在页面显示结果', '1', null, '2018-11-26 18:35:15');
INSERT INTO `sys_logs` VALUES ('45', '1', '根据sql在页面显示结果', '1', null, '2018-11-26 18:35:27');
INSERT INTO `sys_logs` VALUES ('46', '1', '根据sql导出excel', '1', null, '2018-11-26 18:35:33');
INSERT INTO `sys_logs` VALUES ('47', '1', '根据sql在页面显示结果', '1', null, '2018-11-26 18:36:12');
INSERT INTO `sys_logs` VALUES ('48', '1', 'web端登陆', '1', null, '2018-11-26 20:17:07');
INSERT INTO `sys_logs` VALUES ('49', '1', '退出', '1', null, '2018-11-26 20:19:41');
INSERT INTO `sys_logs` VALUES ('50', '1', 'web端登陆', '1', null, '2018-11-26 20:19:47');
INSERT INTO `sys_logs` VALUES ('51', '1', 'web端登陆', '1', null, '2018-11-26 20:27:05');
INSERT INTO `sys_logs` VALUES ('52', '1', '文件上传', '1', null, '2018-11-26 20:49:36');
INSERT INTO `sys_logs` VALUES ('53', '1', '修改头像', '1', null, '2018-11-26 20:49:36');
INSERT INTO `sys_logs` VALUES ('54', '1', 'web端登陆', '1', null, '2018-11-26 21:25:30');
INSERT INTO `sys_logs` VALUES ('55', '1', '根据sql在页面显示结果', '1', null, '2018-11-26 21:26:04');
INSERT INTO `sys_logs` VALUES ('56', '1', '退出', '1', null, '2018-11-26 21:26:09');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `css` varchar(30) DEFAULT NULL,
  `href` varchar(1000) DEFAULT NULL,
  `type` tinyint(1) NOT NULL,
  `permission` varchar(50) DEFAULT NULL,
  `sort` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '0', '用户管理', 'fa-users', '', '1', '', '1');
INSERT INTO `sys_permission` VALUES ('2', '1', '用户查询', 'fa-user', 'pages/user/userList', '1', '', '2');
INSERT INTO `sys_permission` VALUES ('3', '2', '查询', '', '', '2', 'sys:user:query', '100');
INSERT INTO `sys_permission` VALUES ('4', '2', '新增', '', '', '2', 'sys:user:add', '100');
INSERT INTO `sys_permission` VALUES ('6', '0', '修改密码', 'fa-pencil-square-o', 'pages/user/changePassword', '1', 'sys:user:password', '4');
INSERT INTO `sys_permission` VALUES ('7', '0', '系统设置', 'fa-gears', '', '1', '', '5');
INSERT INTO `sys_permission` VALUES ('8', '7', '菜单', 'fa-cog', 'pages/menu/menuList', '1', '', '6');
INSERT INTO `sys_permission` VALUES ('9', '8', '查询', '', '', '2', 'sys:menu:query', '100');
INSERT INTO `sys_permission` VALUES ('10', '8', '新增', '', '', '2', 'sys:menu:add', '100');
INSERT INTO `sys_permission` VALUES ('11', '8', '删除', '', '', '2', 'sys:menu:del', '100');
INSERT INTO `sys_permission` VALUES ('12', '7', '角色', 'fa-user-secret', 'pages/role/roleList', '1', '', '7');
INSERT INTO `sys_permission` VALUES ('13', '12', '查询', '', '', '2', 'sys:role:query', '100');
INSERT INTO `sys_permission` VALUES ('14', '12', '新增', '', '', '2', 'sys:role:add', '100');
INSERT INTO `sys_permission` VALUES ('15', '12', '删除', '', '', '2', 'sys:role:del', '100');
INSERT INTO `sys_permission` VALUES ('16', '0', '文件管理', 'fa-folder-open', 'pages/fileInfo/fileInfoList', '1', '', '8');
INSERT INTO `sys_permission` VALUES ('17', '16', '查询', '', '', '2', 'sys:file:query', '100');
INSERT INTO `sys_permission` VALUES ('18', '16', '删除', '', '', '2', 'sys:file:del', '100');
INSERT INTO `sys_permission` VALUES ('19', '0', '数据源监控', 'fa-eye', 'druid/index.html', '1', '', '9');
INSERT INTO `sys_permission` VALUES ('20', '0', '接口swagger', 'fa-file-pdf-o', 'swagger-ui.html', '1', '', '10');
INSERT INTO `sys_permission` VALUES ('21', '0', '代码生成', 'fa-wrench', 'pages/generate', '1', 'generate:edit', '11');
INSERT INTO `sys_permission` VALUES ('26', '0', '日志查询', 'fa-reorder', 'pages/log/logList', '1', 'sys:log:query', '13');
INSERT INTO `sys_permission` VALUES ('27', '0', '邮件管理', 'fa-envelope', 'pages/mail/mailList', '1', '', '14');
INSERT INTO `sys_permission` VALUES ('28', '27', '发送邮件', '', '', '2', 'mail:send', '100');
INSERT INTO `sys_permission` VALUES ('29', '27', '查询', '', '', '2', 'mail:all:query', '100');
INSERT INTO `sys_permission` VALUES ('34', '0', 'excel导出', 'fa-arrow-circle-down', 'pages/excel/sql', '1', '', '16');
INSERT INTO `sys_permission` VALUES ('35', '34', '导出', '', '', '2', 'excel:down', '100');
INSERT INTO `sys_permission` VALUES ('36', '34', '页面显示数据', '', '', '2', 'excel:show:datas', '100');
INSERT INTO `sys_permission` VALUES ('37', '0', '字典管理', 'fa-reddit', 'pages/dict/dictList', '1', '', '17');
INSERT INTO `sys_permission` VALUES ('38', '37', '查询', '', '', '2', 'dict:query', '100');
INSERT INTO `sys_permission` VALUES ('39', '37', '新增', '', '', '2', 'dict:add', '100');
INSERT INTO `sys_permission` VALUES ('40', '37', '删除', '', '', '2', 'dict:del', '100');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ADMIN', '管理员', '2017-05-01 13:25:39', '2017-10-05 21:59:18');
INSERT INTO `sys_role` VALUES ('2', 'USER', '用户', '2017-08-01 21:47:31', '2017-10-05 21:59:26');
INSERT INTO `sys_role` VALUES ('3', 'TEST', 'cesgi', '2018-11-25 22:14:05', '2018-11-25 22:20:02');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('1', '2');
INSERT INTO `sys_role_permission` VALUES ('1', '3');
INSERT INTO `sys_role_permission` VALUES ('1', '4');
INSERT INTO `sys_role_permission` VALUES ('1', '6');
INSERT INTO `sys_role_permission` VALUES ('1', '7');
INSERT INTO `sys_role_permission` VALUES ('1', '8');
INSERT INTO `sys_role_permission` VALUES ('1', '9');
INSERT INTO `sys_role_permission` VALUES ('1', '10');
INSERT INTO `sys_role_permission` VALUES ('1', '11');
INSERT INTO `sys_role_permission` VALUES ('1', '12');
INSERT INTO `sys_role_permission` VALUES ('1', '13');
INSERT INTO `sys_role_permission` VALUES ('1', '14');
INSERT INTO `sys_role_permission` VALUES ('1', '15');
INSERT INTO `sys_role_permission` VALUES ('1', '16');
INSERT INTO `sys_role_permission` VALUES ('1', '17');
INSERT INTO `sys_role_permission` VALUES ('1', '18');
INSERT INTO `sys_role_permission` VALUES ('1', '19');
INSERT INTO `sys_role_permission` VALUES ('1', '20');
INSERT INTO `sys_role_permission` VALUES ('1', '21');
INSERT INTO `sys_role_permission` VALUES ('1', '22');
INSERT INTO `sys_role_permission` VALUES ('1', '23');
INSERT INTO `sys_role_permission` VALUES ('1', '24');
INSERT INTO `sys_role_permission` VALUES ('1', '25');
INSERT INTO `sys_role_permission` VALUES ('1', '26');
INSERT INTO `sys_role_permission` VALUES ('1', '27');
INSERT INTO `sys_role_permission` VALUES ('1', '28');
INSERT INTO `sys_role_permission` VALUES ('1', '29');
INSERT INTO `sys_role_permission` VALUES ('1', '30');
INSERT INTO `sys_role_permission` VALUES ('1', '31');
INSERT INTO `sys_role_permission` VALUES ('1', '32');
INSERT INTO `sys_role_permission` VALUES ('1', '33');
INSERT INTO `sys_role_permission` VALUES ('1', '34');
INSERT INTO `sys_role_permission` VALUES ('1', '35');
INSERT INTO `sys_role_permission` VALUES ('1', '36');
INSERT INTO `sys_role_permission` VALUES ('1', '37');
INSERT INTO `sys_role_permission` VALUES ('1', '38');
INSERT INTO `sys_role_permission` VALUES ('1', '39');
INSERT INTO `sys_role_permission` VALUES ('1', '40');
INSERT INTO `sys_role_permission` VALUES ('2', '1');
INSERT INTO `sys_role_permission` VALUES ('2', '2');
INSERT INTO `sys_role_permission` VALUES ('2', '3');
INSERT INTO `sys_role_permission` VALUES ('2', '4');
INSERT INTO `sys_role_permission` VALUES ('2', '6');
INSERT INTO `sys_role_permission` VALUES ('2', '7');
INSERT INTO `sys_role_permission` VALUES ('2', '8');
INSERT INTO `sys_role_permission` VALUES ('2', '9');
INSERT INTO `sys_role_permission` VALUES ('2', '10');
INSERT INTO `sys_role_permission` VALUES ('2', '11');
INSERT INTO `sys_role_permission` VALUES ('2', '12');
INSERT INTO `sys_role_permission` VALUES ('2', '13');
INSERT INTO `sys_role_permission` VALUES ('2', '14');
INSERT INTO `sys_role_permission` VALUES ('2', '15');
INSERT INTO `sys_role_permission` VALUES ('2', '16');
INSERT INTO `sys_role_permission` VALUES ('2', '17');
INSERT INTO `sys_role_permission` VALUES ('2', '18');
INSERT INTO `sys_role_permission` VALUES ('2', '19');
INSERT INTO `sys_role_permission` VALUES ('2', '20');
INSERT INTO `sys_role_permission` VALUES ('2', '21');
INSERT INTO `sys_role_permission` VALUES ('2', '22');
INSERT INTO `sys_role_permission` VALUES ('2', '23');
INSERT INTO `sys_role_permission` VALUES ('2', '24');
INSERT INTO `sys_role_permission` VALUES ('2', '25');
INSERT INTO `sys_role_permission` VALUES ('2', '30');
INSERT INTO `sys_role_permission` VALUES ('2', '31');
INSERT INTO `sys_role_permission` VALUES ('2', '34');
INSERT INTO `sys_role_permission` VALUES ('2', '36');
INSERT INTO `sys_role_permission` VALUES ('3', '1');
INSERT INTO `sys_role_permission` VALUES ('3', '2');
INSERT INTO `sys_role_permission` VALUES ('3', '3');
INSERT INTO `sys_role_permission` VALUES ('3', '4');
INSERT INTO `sys_role_permission` VALUES ('3', '6');
INSERT INTO `sys_role_permission` VALUES ('3', '7');
INSERT INTO `sys_role_permission` VALUES ('3', '8');
INSERT INTO `sys_role_permission` VALUES ('3', '9');
INSERT INTO `sys_role_permission` VALUES ('3', '10');
INSERT INTO `sys_role_permission` VALUES ('3', '11');
INSERT INTO `sys_role_permission` VALUES ('3', '12');
INSERT INTO `sys_role_permission` VALUES ('3', '13');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('1', '1');
INSERT INTO `sys_role_user` VALUES ('2', '2');
INSERT INTO `sys_role_user` VALUES ('3', '3');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(32) NOT NULL,
  `salt` varchar(32) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `head_img_url` varchar(255) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `telephone` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'f511e47d720ebb7e9973124b82de7e04', 'f17858e64a6b445bbb07c4d912cac08d', '管理员', '/2018/11/26/eccc79f0dc56ed930efa28589957b8cc.jpg', '18206764943', '0871-1234567', '292644410@qq.com', '1998-06-30', '1', '1', '2017-04-10 15:21:38', '2018-11-26 20:49:36');
INSERT INTO `sys_user` VALUES ('2', 'user', '72c2e62dba72e5f178542313803f33d1', '143292269df8c63e2da1a9ba2aeff43c', '用户', null, '18206764943', '', 'cmh19940105@163.com', null, '1', '1', '2017-08-01 21:47:18', '2018-11-25 18:15:43');
INSERT INTO `sys_user` VALUES ('3', 'test', '9fa415df97a052d3ea2813d321953155', 'bc103ae12bad59068b837ba61c22d4f4', '测试用户', null, '18206764943', null, null, '2018-11-27', '0', '1', '2018-11-25 18:16:11', '2018-11-25 22:19:20');
INSERT INTO `sys_user` VALUES ('4', 'u', 'b0abc7e97ee9a09bc1deb7d8810cad74', '9790116330fd2172e160dd7f5c1aa4ab', null, null, null, null, null, null, '0', '1', '2018-11-25 20:35:20', '2018-11-25 20:35:20');
