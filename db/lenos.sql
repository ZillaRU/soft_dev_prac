/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : lenos

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-02 16:55:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) NOT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('10', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-28 19:05:51');
INSERT INTO `sys_log` VALUES ('11', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-28 20:41:18');
INSERT INTO `sys_log` VALUES ('12', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-28 22:35:38');
INSERT INTO `sys_log` VALUES ('13', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-28 22:35:41');
INSERT INTO `sys_log` VALUES ('14', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 10:27:36');
INSERT INTO `sys_log` VALUES ('15', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 10:27:46');
INSERT INTO `sys_log` VALUES ('16', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 11:49:40');
INSERT INTO `sys_log` VALUES ('17', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 11:49:44');
INSERT INTO `sys_log` VALUES ('18', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 11:56:35');
INSERT INTO `sys_log` VALUES ('19', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 11:57:04');
INSERT INTO `sys_log` VALUES ('20', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 11:57:32');
INSERT INTO `sys_log` VALUES ('21', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单/ by zero', '2017-12-29 11:57:37');
INSERT INTO `sys_log` VALUES ('22', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 11:57:38');
INSERT INTO `sys_log` VALUES ('23', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单/ by zero', '2017-12-29 11:57:40');
INSERT INTO `sys_log` VALUES ('24', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 11:58:29');
INSERT INTO `sys_log` VALUES ('25', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 11:58:31');
INSERT INTO `sys_log` VALUES ('26', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 13:58:45');
INSERT INTO `sys_log` VALUES ('27', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 13:58:49');
INSERT INTO `sys_log` VALUES ('28', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:00:57');
INSERT INTO `sys_log` VALUES ('29', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:00:57');
INSERT INTO `sys_log` VALUES ('30', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:01:52');
INSERT INTO `sys_log` VALUES ('31', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:01:53');
INSERT INTO `sys_log` VALUES ('32', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:01:53');
INSERT INTO `sys_log` VALUES ('33', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:01:55');
INSERT INTO `sys_log` VALUES ('34', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:02:34');
INSERT INTO `sys_log` VALUES ('35', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:02:34');
INSERT INTO `sys_log` VALUES ('36', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:02:35');
INSERT INTO `sys_log` VALUES ('37', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:02:36');
INSERT INTO `sys_log` VALUES ('38', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:02:37');
INSERT INTO `sys_log` VALUES ('39', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:02:41');
INSERT INTO `sys_log` VALUES ('40', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:08:10');
INSERT INTO `sys_log` VALUES ('41', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:09:16');
INSERT INTO `sys_log` VALUES ('42', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 14:09:20');
INSERT INTO `sys_log` VALUES ('43', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 14:12:41');
INSERT INTO `sys_log` VALUES ('44', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 14:12:47');
INSERT INTO `sys_log` VALUES ('45', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 14:13:07');
INSERT INTO `sys_log` VALUES ('46', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:13:16');
INSERT INTO `sys_log` VALUES ('47', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:13:49');
INSERT INTO `sys_log` VALUES ('48', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 14:13:52');
INSERT INTO `sys_log` VALUES ('49', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 14:17:33');
INSERT INTO `sys_log` VALUES ('50', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 14:39:49');
INSERT INTO `sys_log` VALUES ('51', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 14:40:08');
INSERT INTO `sys_log` VALUES ('52', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 14:40:20');
INSERT INTO `sys_log` VALUES ('53', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 14:40:40');
INSERT INTO `sys_log` VALUES ('54', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:41:04');
INSERT INTO `sys_log` VALUES ('55', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:41:24');
INSERT INTO `sys_log` VALUES ('56', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:41:25');
INSERT INTO `sys_log` VALUES ('57', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:41:26');
INSERT INTO `sys_log` VALUES ('58', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:42:22');
INSERT INTO `sys_log` VALUES ('59', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:43:31');
INSERT INTO `sys_log` VALUES ('60', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:43:51');
INSERT INTO `sys_log` VALUES ('61', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:44:49');
INSERT INTO `sys_log` VALUES ('62', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:44:50');
INSERT INTO `sys_log` VALUES ('63', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:44:50');
INSERT INTO `sys_log` VALUES ('64', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:44:57');
INSERT INTO `sys_log` VALUES ('65', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 14:45:02');
INSERT INTO `sys_log` VALUES ('66', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 14:52:18');
INSERT INTO `sys_log` VALUES ('67', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 14:52:58');
INSERT INTO `sys_log` VALUES ('68', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 14:53:14');
INSERT INTO `sys_log` VALUES ('69', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 14:53:33');
INSERT INTO `sys_log` VALUES ('70', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 14:59:40');
INSERT INTO `sys_log` VALUES ('71', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 15:00:12');
INSERT INTO `sys_log` VALUES ('72', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 15:02:02');
INSERT INTO `sys_log` VALUES ('73', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 15:14:20');
INSERT INTO `sys_log` VALUES ('74', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 15:16:43');
INSERT INTO `sys_log` VALUES ('75', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 15:23:06');
INSERT INTO `sys_log` VALUES ('76', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 15:54:03');
INSERT INTO `sys_log` VALUES ('77', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 15:54:15');
INSERT INTO `sys_log` VALUES ('78', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 15:56:46');
INSERT INTO `sys_log` VALUES ('79', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 15:56:54');
INSERT INTO `sys_log` VALUES ('80', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 15:58:30');
INSERT INTO `sys_log` VALUES ('81', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 15:59:10');
INSERT INTO `sys_log` VALUES ('82', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 16:01:12');
INSERT INTO `sys_log` VALUES ('83', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 16:01:55');
INSERT INTO `sys_log` VALUES ('84', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 16:02:57');
INSERT INTO `sys_log` VALUES ('85', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 16:02:59');
INSERT INTO `sys_log` VALUES ('86', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 16:05:45');
INSERT INTO `sys_log` VALUES ('87', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 16:06:07');
INSERT INTO `sys_log` VALUES ('88', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 16:06:17');
INSERT INTO `sys_log` VALUES ('89', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 16:06:21');
INSERT INTO `sys_log` VALUES ('90', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 16:06:22');
INSERT INTO `sys_log` VALUES ('91', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 16:12:50');
INSERT INTO `sys_log` VALUES ('92', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 16:24:44');
INSERT INTO `sys_log` VALUES ('93', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 16:24:48');
INSERT INTO `sys_log` VALUES ('94', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 16:26:47');
INSERT INTO `sys_log` VALUES ('95', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 16:27:36');
INSERT INTO `sys_log` VALUES ('96', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 16:29:48');
INSERT INTO `sys_log` VALUES ('97', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 20:22:16');
INSERT INTO `sys_log` VALUES ('98', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 20:33:31');
INSERT INTO `sys_log` VALUES ('99', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 20:36:42');
INSERT INTO `sys_log` VALUES ('100', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-29 20:36:58');
INSERT INTO `sys_log` VALUES ('101', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 23:16:31');
INSERT INTO `sys_log` VALUES ('102', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 23:22:40');
INSERT INTO `sys_log` VALUES ('103', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 23:24:27');
INSERT INTO `sys_log` VALUES ('104', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '登录平台', '2017-12-29 23:32:24');
INSERT INTO `sys_log` VALUES ('105', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-30 01:02:58');
INSERT INTO `sys_log` VALUES ('106', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-30 01:04:25');
INSERT INTO `sys_log` VALUES ('107', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-30 18:19:40');
INSERT INTO `sys_log` VALUES ('108', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-30 18:37:48');
INSERT INTO `sys_log` VALUES ('109', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-30 20:05:05');
INSERT INTO `sys_log` VALUES ('110', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-30 20:23:56');
INSERT INTO `sys_log` VALUES ('111', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '更新用户', '2017-12-30 22:28:05');
INSERT INTO `sys_log` VALUES ('112', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '更新角色', '2017-12-30 22:28:13');
INSERT INTO `sys_log` VALUES ('113', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-31 00:54:32');
INSERT INTO `sys_log` VALUES ('114', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '更新角色', '2017-12-31 00:54:52');
INSERT INTO `sys_log` VALUES ('115', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-31 00:57:13');
INSERT INTO `sys_log` VALUES ('116', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '更新角色', '2017-12-31 00:57:21');
INSERT INTO `sys_log` VALUES ('117', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '更新用户', '2017-12-31 00:57:52');
INSERT INTO `sys_log` VALUES ('118', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '更新角色', '2017-12-31 00:58:05');
INSERT INTO `sys_log` VALUES ('119', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-31 00:58:14');
INSERT INTO `sys_log` VALUES ('120', 'zxm', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-31 01:00:09');
INSERT INTO `sys_log` VALUES ('121', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-31 10:42:28');
INSERT INTO `sys_log` VALUES ('122', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-31 12:28:01');
INSERT INTO `sys_log` VALUES ('123', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '更新角色', '2017-12-31 12:31:51');
INSERT INTO `sys_log` VALUES ('124', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '更新角色', '2017-12-31 12:32:40');
INSERT INTO `sys_log` VALUES ('125', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '更新用户', '2017-12-31 12:33:04');
INSERT INTO `sys_log` VALUES ('126', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-31 12:38:47');
INSERT INTO `sys_log` VALUES ('127', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-31 16:40:46');
INSERT INTO `sys_log` VALUES ('128', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '更新角色', '2017-12-31 16:42:15');
INSERT INTO `sys_log` VALUES ('129', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-31 16:55:51');
INSERT INTO `sys_log` VALUES ('130', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '更新角色', '2017-12-31 16:58:09');
INSERT INTO `sys_log` VALUES ('131', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-31 19:57:10');
INSERT INTO `sys_log` VALUES ('132', 'admin', '192.168.0.102', 'SELECT', '展示菜单', '2017-12-31 21:19:58');
INSERT INTO `sys_log` VALUES ('133', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-31 21:41:12');
INSERT INTO `sys_log` VALUES ('134', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2017-12-31 21:41:21');
INSERT INTO `sys_log` VALUES ('135', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '更新用户', '2018-01-02 16:11:28');
INSERT INTO `sys_log` VALUES ('136', 'admin', '0:0:0:0:0:0:0:1', 'ADD', '更新角色', '2018-01-02 16:12:05');
INSERT INTO `sys_log` VALUES ('137', 'admin', '0:0:0:0:0:0:0:1', 'SELECT', '展示菜单', '2018-01-02 16:12:46');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  `p_id` varchar(36) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `order_num` int(4) DEFAULT NULL COMMENT '排序字段',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `permission` varchar(255) DEFAULT NULL COMMENT '权限',
  `menu_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1栏目2菜单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('3873ccc2dfda11e7b555201a068c6482', '菜单管理', 'cfda8029dfb311e7b555201a068c6482', 'menu/showMenu', '1', '&#xe62a;', null, '2017-12-14 14:02:50', null, '2017-12-14 14:02:50', null, '0');
INSERT INTO `sys_menu` VALUES ('433089a6eb0111e782d5201a068c6482', '编辑', 'cfe54921dfb311e7b555201a068c6482', '', null, '1', 'acfc0e9232f54732a5d9ffe9071bf572', '2017-12-27 20:27:11', null, null, 'user:update', '1');
INSERT INTO `sys_menu` VALUES ('6dc13c6eec5f11e7a472201a068c6482', '系统日志', 'a1ca6642ec5e11e7a472201a068c6482', 'log/showLog', '1', '&#xe60a;', 'acfc0e9232f54732a5d9ffe9071bf572', '2017-12-29 14:43:46', null, '2017-12-29 14:43:46', null, '0');
INSERT INTO `sys_menu` VALUES ('7967e098ee0611e7a60d201a068c6482', '接口api', 'a1ca6642ec5e11e7a472201a068c6482', 'swagger-ui.html', '2', '&#xe64e;', 'acfc0e9232f54732a5d9ffe9071bf572', '2017-12-31 16:42:04', null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('a1ca6642ec5e11e7a472201a068c6482', '系统监控', null, null, '2', '&#xe62c;', 'acfc0e9232f54732a5d9ffe9071bf572', '2017-12-29 14:52:14', null, '2017-12-29 14:52:14', null, '0');
INSERT INTO `sys_menu` VALUES ('b441914cee0811e7a60d201a068c6482', '系统监控', 'a1ca6642ec5e11e7a472201a068c6482', 'druid/index.html', '3', '&#xe628;', 'acfc0e9232f54732a5d9ffe9071bf572', '2017-12-31 16:58:01', null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('cfda8029dfb311e7b555201a068c6482', '系统管理', null, null, '1', '&#xe614;', null, '2017-12-29 14:59:22', null, '2017-12-29 14:59:22', null, '0');
INSERT INTO `sys_menu` VALUES ('cfe54921dfb311e7b555201a068c6482', '用户管理', 'cfda8029dfb311e7b555201a068c6482', '/user/showUser', '2', '&#xe6af;', null, '2017-12-29 14:40:34', null, '2017-12-29 14:40:34', null, '0');
INSERT INTO `sys_menu` VALUES ('cfe54921dfb311e7b555201a068c6483', '增加', 'cfe54921dfb311e7b555201a068c6482', null, '1', null, null, null, null, null, 'user:select', '1');
INSERT INTO `sys_menu` VALUES ('cff61424dfb311e7b555201a068c6482', '角色管理', 'cfda8029dfb311e7b555201a068c6482', '/role/showRole', '3', '&#xe613;', null, '2017-12-29 14:40:36', null, '2017-12-29 14:40:36', null, '0');
INSERT INTO `sys_menu` VALUES ('e3b11497eb9e11e7928d201a068c6482', '删除', 'cfe54921dfb311e7b555201a068c6482', '', null, '', 'acfc0e9232f54732a5d9ffe9071bf572', '2017-12-28 15:17:07', null, '2017-12-28 15:17:07', 'user:del', '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(32) NOT NULL,
  `role_name` varchar(128) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('0ea934e5e55411e7b983201a068c6482', 'group1', '一组', 'acfc0e9232f54732a5d9ffe9071bf572', '2017-12-20 15:04:44', null, '2018-01-02 11:41:43');
INSERT INTO `sys_role` VALUES ('2619a672e53811e7b983201a068c6482', 'admin', '管理员', 'acfc0e9232f54732a5d9ffe9071bf572', '2017-12-20 11:44:57', null, '2018-01-02 11:38:37');
INSERT INTO `sys_role` VALUES ('576e0c81eb1311e782d5201a068c6482', 'group2', '二组', 'acfc0e9232f54732a5d9ffe9071bf572', '2017-12-27 22:36:36', null, null);
INSERT INTO `sys_role` VALUES ('a56219ffeb7d11e7928d201a068c6482', 'g10', '十组', 'acfc0e9232f54732a5d9ffe9071bf572', '2017-12-28 11:17:33', null, null);
INSERT INTO `sys_role` VALUES ('b9fb1173eb7611e7928d201a068c6482', 'group3', '三组', 'acfc0e9232f54732a5d9ffe9071bf572', '2017-12-28 10:28:01', null, null);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` varchar(32) NOT NULL,
  `menu_id` varchar(32) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('0ea934e5e55411e7b983201a068c6482', '6dc13c6eec5f11e7a472201a068c6482');
INSERT INTO `sys_role_menu` VALUES ('0ea934e5e55411e7b983201a068c6482', 'a1ca6642ec5e11e7a472201a068c6482');
INSERT INTO `sys_role_menu` VALUES ('0ea934e5e55411e7b983201a068c6482', 'cfda8029dfb311e7b555201a068c6482');
INSERT INTO `sys_role_menu` VALUES ('0ea934e5e55411e7b983201a068c6482', 'cfe54921dfb311e7b555201a068c6482');
INSERT INTO `sys_role_menu` VALUES ('0ea934e5e55411e7b983201a068c6482', 'e3b11497eb9e11e7928d201a068c6482');
INSERT INTO `sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', '3873ccc2dfda11e7b555201a068c6482');
INSERT INTO `sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', '433089a6eb0111e782d5201a068c6482');
INSERT INTO `sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', '6dc13c6eec5f11e7a472201a068c6482');
INSERT INTO `sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', '7967e098ee0611e7a60d201a068c6482');
INSERT INTO `sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', 'a1ca6642ec5e11e7a472201a068c6482');
INSERT INTO `sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', 'b441914cee0811e7a60d201a068c6482');
INSERT INTO `sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', 'cfda8029dfb311e7b555201a068c6482');
INSERT INTO `sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', 'cfe54921dfb311e7b555201a068c6482');
INSERT INTO `sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', 'cfe54921dfb311e7b555201a068c6483');
INSERT INTO `sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', 'cff61424dfb311e7b555201a068c6482');
INSERT INTO `sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', 'e3b11497eb9e11e7928d201a068c6482');
INSERT INTO `sys_role_menu` VALUES ('a56219ffeb7d11e7928d201a068c6482', '433089a6eb0111e782d5201a068c6482');
INSERT INTO `sys_role_menu` VALUES ('a56219ffeb7d11e7928d201a068c6482', 'cfda8029dfb311e7b555201a068c6482');
INSERT INTO `sys_role_menu` VALUES ('a56219ffeb7d11e7928d201a068c6482', 'cfe54921dfb311e7b555201a068c6482');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `user_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('2211fec3e17c11e795ed201a068c6482', '0ea934e5e55411e7b983201a068c6482');
INSERT INTO `sys_role_user` VALUES ('7dfe1191eaa911e79999201a068c6482', '0ea934e5e55411e7b983201a068c6482');
INSERT INTO `sys_role_user` VALUES ('acfc0e9232f54732a5d9ffe9071bf572', '0ea934e5e55411e7b983201a068c6482');
INSERT INTO `sys_role_user` VALUES ('acfc0e9232f54732a5d9ffe9071bf572', '2619a672e53811e7b983201a068c6482');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(36) NOT NULL,
  `username` varchar(64) NOT NULL,
  `password` varchar(128) NOT NULL,
  `age` int(4) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `real_name` varchar(18) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0可用1封禁',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('2211fec3e17c11e795ed201a068c6482', 'zxm', 'f8880ebbdbc37a936245657fa9084198', '24', '154040976@qq.com', '', '', null, null, '2017-12-15 17:41:32', '2017-12-20 16:33:29', '0');
INSERT INTO `sys_user` VALUES ('7dfe1191eaa911e79999201a068c6482', 'ceshi', 'e6e7d1d292662f8a30a3f2c57049af00', '24', '', '', '', null, null, '2017-12-27 09:58:54', null, '0');
INSERT INTO `sys_user` VALUES ('900ac744db0111e7b309201a068c6482', 'zhangsan', '9bad41710724cg6511abde2a52416881', '24', '154040976@qq.com', null, null, null, null, '2017-12-18 16:38:08', '2017-12-18 16:38:08', '1');
INSERT INTO `sys_user` VALUES ('91b1af44db1511e7b309201a068c6482', 'zhang18', 'b67bdb1e035545aeg7eg4ab85a8g5dd0', '24', null, null, null, null, null, '2017-12-18 16:38:20', '2017-12-18 16:38:20', '1');
INSERT INTO `sys_user` VALUES ('acfc0e9232f54732a5d9ffe9071bf510', 'ceshi5', '123456', '24', '154040976@qq.com', null, null, null, null, '2017-12-18 16:40:20', '2017-12-18 16:40:20', '1');
INSERT INTO `sys_user` VALUES ('acfc0e9232f54732a5d9ffe9071bf513', 'ceshi9', '123457', '24', '154040976@qq.com', null, null, null, null, null, null, '0');
INSERT INTO `sys_user` VALUES ('acfc0e9232f54732a5d9ffe9071bf514', 'ceshi10', '123457', '24', '154040976@qq.com', null, null, null, null, '2017-12-18 20:22:10', '2017-12-18 20:22:10', '1');
INSERT INTO `sys_user` VALUES ('acfc0e9232f54732a5d9ffe9071bf572', 'admin', 'e0b141de1c8091be350d3fc80de66528', '24', '', 'image/2018-01-02/04112710081355948a3f0cf5-464x700.jpg', '', null, null, '2017-12-20 16:34:06', '2017-12-20 16:34:06', '0');
INSERT INTO `sys_user` VALUES ('acfc0e9232f54732a5d9ffe9071bf573', 'ceshi1', '123456', '24', null, null, null, null, null, null, null, '0');
INSERT INTO `sys_user` VALUES ('acfc0e9232f54732a5d9ffe9071bf574', 'ceshi2', '123456', '24', '154040976@qq.com', null, null, null, null, null, null, '0');
INSERT INTO `sys_user` VALUES ('acfc0e9232f54732a5d9ffe9071bf577', 'ceshi3', '123456', '24', '154040976@qq.com', null, null, null, null, null, null, '0');
INSERT INTO `sys_user` VALUES ('b25094d9e39511e7b012201a068c6482', 'zhangsan1', '6bea0fa0327d690c26382a992342179c', '24', '154040976@qq.com', 'image/2017-12-18/094914111.jpg', '张三', null, null, '2017-12-18 09:49:33', null, '0');
INSERT INTO `sys_user` VALUES ('b4fe2048db1211e7b309201a068c6482', 'zhang2', '308ggbdd4g58d783bd5077cg1da31adb', '24', null, null, null, null, null, null, null, '0');
INSERT INTO `sys_user` VALUES ('beeef6eadb1411e7b309201a068c6482', 'zhang11', '4g5c9cae6dd3gb0a2046a8bgbb7ed98b', '24', null, null, null, null, null, null, null, '0');
INSERT INTO `sys_user` VALUES ('ccb3774ddb1211e7b309201a068c6482', 'zhang3', '320725ca60cg5101e7bd719e9b3a176g', '24', null, null, null, null, null, null, null, '0');
