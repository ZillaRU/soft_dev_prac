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
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0可用1封禁',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;