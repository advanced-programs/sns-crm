-- --------------------------------------------------------
-- 主机:                           192.168.1.30
-- 服务器版本:                        5.5.20-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  8.1.0.4545
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 kefu_dev 的数据库结构
CREATE DATABASE IF NOT EXISTS `kefu_dev` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `kefu_dev`;


-- 导出  表 kefu_dev.kefu_config 结构
CREATE TABLE IF NOT EXISTS `kefu_config` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '皮皮精灵用户ID',
  `max_items` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '每客服同时处理工单数的峰值',
  `start_time` int(10) DEFAULT '0' COMMENT '客服工作开始时间',
  `end_time` int(10) DEFAULT '0' COMMENT '客服工作结束时间',
  `human_wait_num` tinyint(3) DEFAULT '10' COMMENT '排队提示人数',
  `human_pre` text COMMENT '人工客服请求等待回复',
  `human_no_service` text COMMENT '无在线客服回复',
  `human_pre_wait` text COMMENT '人工客服请求排队回复',
  `human_pre_invalidtime` text COMMENT '人工客服请求不在服务时间回复',
  `human_welcome` text COMMENT '人工客服请求接入自动回复',
  `human_end` text COMMENT '人工客服结束感谢及提示评价语',
  `human_bad` text COMMENT '评价为差评时提示原因选择引导语',
  `human_tks` text COMMENT '评价后回复语',
  `human_quit_key` text COMMENT '用户主动退出对话关键字',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 kefu_dev.kefu_qas 结构
CREATE TABLE IF NOT EXISTS `kefu_qas` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '皮皮精灵用户ID',
  `suid` int(10) unsigned DEFAULT NULL COMMENT '客服ID',
  `type_id` int(10) DEFAULT NULL COMMENT '分类ID',
  `question` char(200) DEFAULT NULL COMMENT '问题',
  `answer` text COMMENT '答案',
  `sort` smallint(6) DEFAULT NULL COMMENT '排序',
  `insert_type` tinyint(3) DEFAULT NULL COMMENT '插入类型1系统问题（导入+后台）2个人问题（前台）',
  `addtime` int(10) DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 kefu_dev.kefu_qas_type 结构
CREATE TABLE IF NOT EXISTS `kefu_qas_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '皮皮精灵用户ID',
  `type_name` char(20) DEFAULT NULL COMMENT '问题分类名字',
  `type_sort` tinyint(3) DEFAULT NULL COMMENT '问题分类排序',
  `parent_id` int(10) DEFAULT NULL COMMENT '问题分类父ID',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 kefu_dev.kefu_user 结构
CREATE TABLE IF NOT EXISTS `kefu_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '客服ID',
  `uid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '皮皮精灵用户ID',
  `sno` char(6) NOT NULL COMMENT '客服工号',
  `susername` char(16) NOT NULL COMMENT '客服登录账号',
  `spassword` char(32) NOT NULL COMMENT '客服登录密码',
  `realname` char(16) DEFAULT NULL COMMENT '姓名',
  `salt` char(6) NOT NULL,
  `onlinestatus` tinyint(1) NOT NULL DEFAULT '0' COMMENT '在线状态(2:在线,1:离开,0:离线)',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '账号状态(0:锁定,1:启用)',
  `last_login_time` int(10) DEFAULT NULL COMMENT '最后登录时间',
  `access_token` char(32) DEFAULT NULL COMMENT '最后登录会话令牌 session_id',
  `last_activity` int(10) DEFAULT NULL COMMENT '最后活动时间',
  `last_deal_item` int(10) DEFAULT NULL COMMENT '最后一次处理中的工单ID',
  `quick_reply` text COMMENT '工单接入快捷回复',
  `max_deal_items` smallint(6) DEFAULT NULL COMMENT '同时处理工单数的最大值',
  `dealing_items_num` smallint(6) NOT NULL DEFAULT '0' COMMENT '正在处理的工单数',
  `work_start_time` int(10) DEFAULT '0' COMMENT '客服每日开始工作时间',
  `work_end_time` int(10) DEFAULT '0' COMMENT '客服每日结束工作时间',
  `addtime` int(10) NOT NULL COMMENT '账号添加时间',
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 kefu_dev.member 结构
CREATE TABLE IF NOT EXISTS `member` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '粉丝记录ID',
  `uid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '皮皮精灵用户ID',
  `openid` char(32) NOT NULL DEFAULT '' COMMENT '微信公开ID',
  `senderid` int(64) unsigned NOT NULL DEFAULT '0' COMMENT '新浪微博私信发送者ID',
  `request_node` char(20) DEFAULT NULL COMMENT '当前请求节点',
  `next_request_area` tinyint(1) DEFAULT NULL,
  `rel_data` text COMMENT '当前请求关联数据, 供下个请求使用',
  `last_request_time` int(10) NOT NULL DEFAULT '0' COMMENT '最后请求时间, 上下文时判断状态是否超时',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '1：待处理 0：已处理',
  `platform` char(15) NOT NULL DEFAULT '' COMMENT 'wechat:微信 sina:新浪微博',
  `addtime` int(10) DEFAULT '0' COMMENT '粉丝添加时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 kefu_dev.member_profile 结构
CREATE TABLE IF NOT EXISTS `member_profile` (
  `mid` int(10) unsigned NOT NULL,
  `uid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '皮皮精灵用户ID',
  `nickname` varchar(50) DEFAULT NULL,
  `headimgurl` varchar(150) DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `language` varchar(50) DEFAULT NULL,
  `city` char(30) DEFAULT NULL,
  `province` char(30) DEFAULT NULL,
  `country` char(30) DEFAULT NULL,
  `subscribe_time` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 kefu_dev.response_base 结构
CREATE TABLE IF NOT EXISTS `response_base` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '回复参数ID',
  `uid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '皮皮精灵用户ID',
  `productlist_face` char(30) DEFAULT NULL COMMENT '微信产品中心查看更多配图',
  `welcome` text COMMENT '关注回复',
  `main_key` varchar(50) DEFAULT NULL COMMENT '主菜单关键字',
  `main` text COMMENT '?对应的主菜单回复',
  `backtomain` varchar(100) DEFAULT NULL COMMENT '返回主菜单引导语',
  `prod_key` varchar(50) DEFAULT NULL COMMENT '产品中心关键字',
  `prod_parts` text COMMENT '配件中心回复',
  `prod_parts_key` varchar(50) DEFAULT NULL COMMENT '配件中心关键字',
  `productlist_parts_face` char(30) DEFAULT NULL COMMENT '微信配件列表查看更多配图',
  `qas_key` varchar(50) DEFAULT NULL COMMENT '知识库关键字',
  `qas` text COMMENT '问题咨询自动回复',
  `noquestion` text COMMENT '没有检索到相关问题时的回复',
  `qas_pre` text COMMENT '检索成功时, 问题列表前置语',
  `qas_end` text COMMENT '检索成功时, 问题列表后置语',
  `qas_choose_wrong` text COMMENT '序号选择错误回复',
  `qas_back_key` varchar(50) DEFAULT NULL COMMENT '返回上一层关键字',
  `qas_back` text COMMENT '返回上一层提示语',
  `human_lead` text COMMENT '人工客服引导语',
  `human_key` varchar(50) DEFAULT NULL COMMENT '人工客服关键字',
  `human` text COMMENT '人工客服自动回复',
  `user_keepstatus_time` smallint(6) DEFAULT '900' COMMENT '微信用户保存状态跟踪时间(单位:秒)',
  `code_key` varchar(50) DEFAULT NULL COMMENT '获取优惠码关键字',
  `code_url` varchar(200) DEFAULT '' COMMENT '获取优惠码接口地址',
  `code_url_version` smallint(6) DEFAULT '0' COMMENT '获取优惠码接口版本号',
  `code_key_desc` varchar(100) DEFAULT NULL COMMENT '获取优惠码前置语',
  `code_key_desc_end` text COMMENT '获取优惠码后置语',
  `ymt_key` varchar(50) DEFAULT NULL COMMENT '印美图关键字',
  `ymt_key_desc` varchar(200) DEFAULT NULL COMMENT '印美图回复',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 kefu_dev.system_configs 结构
CREATE TABLE IF NOT EXISTS `system_configs` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '皮皮精灵用户ID',
  `appid` char(18) NOT NULL DEFAULT '',
  `appsecret` char(32) NOT NULL DEFAULT '',
  `token` varchar(50) DEFAULT NULL,
  `wxmenu` text,
  `access_token` varchar(255) NOT NULL DEFAULT '',
  `expires_time` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 kefu_dev.system_role 结构
CREATE TABLE IF NOT EXISTS `system_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '管理角色ID',
  `uid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '皮皮精灵用户ID',
  `role_name` char(20) NOT NULL COMMENT '管理角色名称',
  `role_shell` varchar(255) NOT NULL DEFAULT '' COMMENT '管理角色权限',
  `role_rank` tinyint(1) NOT NULL DEFAULT '1' COMMENT '角色属性, 0:系统内置, 1:用户添加',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 kefu_dev.system_user 结构
CREATE TABLE IF NOT EXISTS `system_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '皮皮精灵用户ID',
  `name` char(16) NOT NULL COMMENT '登录账号',
  `role_id` smallint(6) NOT NULL COMMENT '角色ID',
  `user_shell` text COMMENT '角色权限',
  `last_login_time` int(10) DEFAULT NULL COMMENT '最后登录时间',
  `add_by_user` char(16) DEFAULT NULL COMMENT '由谁创建的账号',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态(0:禁用, 1:启用)',
  `addtime` int(10) DEFAULT NULL COMMENT '账号添加时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 kefu_dev.work_items 结构
CREATE TABLE IF NOT EXISTS `work_items` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '客服工单ID',
  `uid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '皮皮精灵用户ID',
  `mid` int(10) NOT NULL COMMENT '用户ID',
  `suid` int(10) DEFAULT NULL COMMENT '客服ID',
  `last_suid` int(10) DEFAULT NULL COMMENT '转接前处理客服ID',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '工单状态(-1:用户放弃,0:已完成,1:待处理,2:处理中)',
  `create_time` int(10) NOT NULL COMMENT '工单生成时间',
  `last_request_time` int(10) DEFAULT NULL COMMENT '用户最后请求时间',
  `last_get_time` int(10) DEFAULT NULL COMMENT '客服最后一次接入时间',
  `last_read_time` int(10) NOT NULL DEFAULT '0' COMMENT '客服最后阅读工单时间',
  `deal_time` int(10) DEFAULT NULL COMMENT '客服受理工单时间',
  `done_time` int(10) DEFAULT NULL COMMENT '工单完成时间',
  `memo` text COMMENT '工单描述',
  `evaluate_time` int(10) DEFAULT NULL COMMENT '评价时间',
  `evaluate_id` tinyint(4) DEFAULT NULL COMMENT '用户评价',
  `evaluate_bad_reason` text COMMENT '差评原因',
  `platform` char(15) NOT NULL DEFAULT '' COMMENT 'wechat && sina',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 kefu_dev.work_item_evaluate_bad_options 结构
CREATE TABLE IF NOT EXISTS `work_item_evaluate_bad_options` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '客服评价选项记录ID',
  `uid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '皮皮精灵用户ID',
  `content` varchar(20) NOT NULL COMMENT '选项内容',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '选项序号',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 kefu_dev.work_item_evaluate_options 结构
CREATE TABLE IF NOT EXISTS `work_item_evaluate_options` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '客服评价选项记录ID',
  `uid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '皮皮精灵用户ID',
  `content` varchar(20) NOT NULL COMMENT '选项内容',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '选项序号',
  `isbad` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否差评(1:是, 0:否)',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
