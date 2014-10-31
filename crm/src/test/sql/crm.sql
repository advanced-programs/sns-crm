-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.16 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 crm_dev 的数据库结构
CREATE DATABASE IF NOT EXISTS `crm_dev` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `crm_dev`;


-- 导出  表 crm_dev.contact_info 结构
CREATE TABLE IF NOT EXISTS `contact_info` (
  `contact_id` smallint(6) unsigned NOT NULL AUTO_INCREMENT COMMENT '联系方式id',
  `contact_name` varchar(50) NOT NULL COMMENT '联系方式描述',
  PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='联系方式字典信息表';

-- 正在导出表  crm_dev.contact_info 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `contact_info` DISABLE KEYS */;
INSERT INTO `contact_info` (`contact_id`, `contact_name`) VALUES
	(1, '手机'),
	(2, 'QQ'),
	(3, '电子邮箱'),
	(4, '支付宝');
/*!40000 ALTER TABLE `contact_info` ENABLE KEYS */;


-- 导出  表 crm_dev.member 结构
CREATE TABLE IF NOT EXISTS `member` (
  `mid` bigint(20) unsigned NOT NULL,
  `uid` bigint(20) unsigned NOT NULL,
  `address` varchar(200) NOT NULL COMMENT '会员地址信息，用于会员的地域划分，和收获地址不同',
  `birthday` date DEFAULT NULL COMMENT '出生年月',
  `member_point` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '会员积分',
  `experience` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '会员经验',
  `id_card_no` char(18) NOT NULL DEFAULT '' COMMENT '身份证号',
  `member_card_no` varchar(30) NOT NULL DEFAULT '' COMMENT '会员卡号',
  `member_level_id` int(10) unsigned DEFAULT NULL COMMENT '会员等级Id，如果有值，则以该字段为准',
  `update_time` datetime NOT NULL COMMENT '加入时间',
  `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '状态：-1=删除；0=正常',
  PRIMARY KEY (`mid`),
  KEY `Index 2` (`uid`,`mid`),
  KEY `FK_member_member_level_info` (`member_level_id`),
  CONSTRAINT `FK_member_member_level_info` FOREIGN KEY (`member_level_id`) REFERENCES `member_level_info` (`member_level_id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员信息表';

-- 正在导出表  crm_dev.member 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` (`mid`, `uid`, `address`, `birthday`, `member_point`, `experience`, `id_card_no`, `member_card_no`, `member_level_id`, `update_time`, `status`) VALUES
	(101, 1, '安徽,合肥,政务区,平安大厦8楼', '2010-03-21', 100, 999, '111111111111111111', '88888888', 1, '2014-03-21 11:12:32', 0),
	(102, 1, '浙江,杭州,西湖区,福地创业园', '1999-09-09', 10, 89, '0', '0', 2, '2014-03-26 16:02:12', 0),
	(103, 2, '北京,北京,北京,xxx', NULL, 0, 0, '0', '0', NULL, '2014-03-26 16:06:20', 0);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;


-- 导出  表 crm_dev.member_address 结构
CREATE TABLE IF NOT EXISTS `member_address` (
  `address_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mid` bigint(20) unsigned NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '收件人姓名',
  `province` varchar(20) NOT NULL,
  `city` varchar(20) NOT NULL,
  `county` varchar(20) NOT NULL,
  `address` varchar(300) NOT NULL,
  `phone` varchar(24) NOT NULL,
  PRIMARY KEY (`address_id`),
  KEY `Index 2` (`mid`),
  CONSTRAINT `FK_member_address_member` FOREIGN KEY (`mid`) REFERENCES `member` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='会员地址信息表';

-- 正在导出表  crm_dev.member_address 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `member_address` DISABLE KEYS */;
INSERT INTO `member_address` (`address_id`, `mid`, `name`, `province`, `city`, `county`, `address`,`phone`) VALUES
	(1, 101, '李四老婆', '安徽', '合肥', '政务区', '汇林阁小区','13888888888'),
	(2, 101, '李四', '安徽', '合肥', '蜀山区', '办公室','13888888888');
/*!40000 ALTER TABLE `member_address` ENABLE KEYS */;


-- 导出  表 crm_dev.member_exp_record 结构
CREATE TABLE IF NOT EXISTS `member_exp_record` (
  `uid` bigint(20) unsigned NOT NULL,
  `mid` bigint(20) unsigned NOT NULL,
  `reason` varchar(200) NOT NULL COMMENT '经验变动原因',
  `exp_change` int(11) NOT NULL COMMENT '经验变动值',
  `create_time` datetime NOT NULL COMMENT '记录创建时间'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='会员经验变动记录表';

-- 正在导出表  crm_dev.member_exp_record 的数据：2 rows
/*!40000 ALTER TABLE `member_exp_record` DISABLE KEYS */;
INSERT INTO `member_exp_record` (`uid`, `mid`, `reason`, `exp_change`, `create_time`) VALUES
	(1, 101, '砸金蛋', 100, '2014-03-23 15:22:29'),
	(1, 101, '刮刮卡', 150, '2014-03-28 15:23:03');
/*!40000 ALTER TABLE `member_exp_record` ENABLE KEYS */;


-- 导出  表 crm_dev.member_level_info 结构
CREATE TABLE IF NOT EXISTS `member_level_info` (
  `member_level_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '会员等级id',
  `uid` bigint(20) unsigned NOT NULL,
  `level_name` varchar(50) NOT NULL COMMENT '等级名称',
  `level_description` varchar(300) NOT NULL COMMENT '等级描述',
  `exp_start` int(11) NOT NULL COMMENT '等级经验起始值',
  `exp_end` int(11) NOT NULL COMMENT '等级经验结束值',
  `discount` tinyint(3) unsigned NOT NULL COMMENT '折扣百分比值，如果是50%折扣即为50',
  `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`member_level_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='会员等级信息表';

-- 正在导出表  crm_dev.member_level_info 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `member_level_info` DISABLE KEYS */;
INSERT INTO `member_level_info` (`member_level_id`, `uid`, `level_name`, `level_description`, `exp_start`, `exp_end`, `discount`, `status`) VALUES
	(1, 1, 'vip1', '我是vip1的描述', 100, 999, 90, 0),
	(2, 1, 'vip2', '更nb的vip', 1000, 9999, 80, 0);
/*!40000 ALTER TABLE `member_level_info` ENABLE KEYS */;


-- 导出  表 crm_dev.member_point_record 结构
CREATE TABLE IF NOT EXISTS `member_point_record` (
  `uid` bigint(20) unsigned NOT NULL,
  `mid` bigint(20) unsigned NOT NULL,
  `reason` varchar(200) NOT NULL COMMENT '积分变动原因',
  `point_change` int(11) NOT NULL COMMENT '积分变动，如果是积分减少则为负数',
  `create_time` datetime NOT NULL COMMENT '记录创建时间'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='会员积分变动记录表';

-- 正在导出表  crm_dev.member_point_record 的数据：2 rows
/*!40000 ALTER TABLE `member_point_record` DISABLE KEYS */;
INSERT INTO `member_point_record` (`uid`, `mid`, `reason`, `point_change`, `create_time`) VALUES
	(1, 101, '刮刮卡', 10, '2014-03-23 10:52:27'),
	(1, 101, '砸金蛋', 5, '2014-03-24 11:53:15');
/*!40000 ALTER TABLE `member_point_record` ENABLE KEYS */;


-- 导出  表 crm_dev.platform 结构
CREATE TABLE IF NOT EXISTS `platform` (
  `uid` bigint(20) unsigned NOT NULL,
  `mid` bigint(20) unsigned NOT NULL,
  `platform` tinyint(3) unsigned NOT NULL COMMENT '平台编号；1=新浪微博；2=腾讯微博；3=微信',
  `platform_user_id` varchar(50) NOT NULL COMMENT '用户在平台的id',
  `create_time` datetime NOT NULL COMMENT '加入时间',
  KEY `FK_platform_user` (`uid`,`mid`),
  CONSTRAINT `FK_platform_user` FOREIGN KEY (`uid`, `mid`) REFERENCES `user` (`uid`, `mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台信息表';

-- 正在导出表  crm_dev.platform 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `platform` DISABLE KEYS */;
INSERT INTO `platform` (`uid`, `mid`, `platform`, `platform_user_id`, `create_time`) VALUES
	(1, 101, 1, '2222222222', '2014-03-21 11:15:01'),
	(1, 101, 2, 'qwerasdf', '2014-03-26 16:49:29');
/*!40000 ALTER TABLE `platform` ENABLE KEYS */;


-- 导出  表 crm_dev.tag_info 结构
CREATE TABLE IF NOT EXISTS `tag_info` (
  `tag_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) unsigned DEFAULT NULL,
  `tag_name` varchar(50) NOT NULL COMMENT '标签名称',
  PRIMARY KEY (`tag_id`),
  KEY `Index 2` (`uid`,`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='标签字典信息表';

-- 正在导出表  crm_dev.tag_info 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `tag_info` DISABLE KEYS */;
INSERT INTO `tag_info` (`tag_id`, `uid`, `tag_name`) VALUES
	(1, 1, '土豪'),
	(2, 1, '屌丝'),
	(3, 1, '高富帅');
/*!40000 ALTER TABLE `tag_info` ENABLE KEYS */;


-- 导出  表 crm_dev.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `mid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `uid` bigint(20) unsigned NOT NULL COMMENT '客户id',
  `is_member` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否是会员：1=是；0=不是',
  `identify` char(15) NOT NULL DEFAULT '' COMMENT '标识（手机号）',
  `name` char(20) NOT NULL DEFAULT '' COMMENT '姓名',
  `nick` char(20) NOT NULL DEFAULT '' COMMENT '昵称',
  `gender` tinyint(4) NOT NULL DEFAULT '0' COMMENT '性别：1=男性；2=女性；0=未知',
  `update_time` datetime NOT NULL COMMENT '加入时间',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态：-1=删除；0=正常',
  PRIMARY KEY (`mid`),
  KEY `Index 2` (`uid`,`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8 COMMENT='用户基本信息表';

-- 正在导出表  crm_dev.user 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`mid`, `uid`, `is_member`, `identify`, `name`, `nick`, `gender`, `update_time`, `status`) VALUES
	(100, 1, 0, '15000000000', '张三', '张三昵称', 0, '2014-03-21 15:40:40', 0),
	(101, 1, 1, '15888888888', '李四', '李四昵称', 1, '2014-03-21 09:32:33', 0),
	(102, 1, 1, '15900000000', '王五', '王五昵称', 0, '2014-03-21 09:33:27', -1),
	(103, 2, 1, '15000000000', '张三马甲', 'Orz', 0, '2014-03-21 09:34:26', 0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


-- 导出  表 crm_dev.user_contact 结构
CREATE TABLE IF NOT EXISTS `user_contact` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) unsigned NOT NULL,
  `mid` bigint(20) unsigned NOT NULL,
  `contact_id` smallint(6) unsigned NOT NULL COMMENT '联系方式类型',
  `value` varchar(50) NOT NULL COMMENT '联系方式值',
  PRIMARY KEY (`id`),
  KEY `FK_user_contact_contact_info` (`contact_id`),
  KEY `FK_user_contact_user` (`uid`,`mid`),
  CONSTRAINT `FK_user_contact_contact_info` FOREIGN KEY (`contact_id`) REFERENCES `contact_info` (`contact_id`),
  CONSTRAINT `FK_user_contact_user` FOREIGN KEY (`uid`, `mid`) REFERENCES `user` (`uid`, `mid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户联系信息表';

-- 正在导出表  crm_dev.user_contact 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `user_contact` DISABLE KEYS */;
INSERT INTO `user_contact` (`id`, `uid`, `mid`, `contact_id`, `value`) VALUES
	(1, 1, 101, 2, '999999'),
	(2, 1, 100, 1, '15888888888'),
	(3, 1, 100, 3, 'abc@pp.cc');
/*!40000 ALTER TABLE `user_contact` ENABLE KEYS */;


-- 导出  表 crm_dev.user_ext 结构
CREATE TABLE IF NOT EXISTS `user_ext` (
  `uid` bigint(20) unsigned DEFAULT NULL,
  `mid` bigint(20) unsigned DEFAULT NULL,
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像图片地址',
  `country` varchar(200) DEFAULT NULL COMMENT '国家',
  `province` varchar(20) DEFAULT NULL COMMENT '省',
  `city` varchar(20) DEFAULT NULL COMMENT '市',
  `county` varchar(20) DEFAULT NULL COMMENT '区',
  `ip` int(10) unsigned DEFAULT NULL COMMENT 'ip信息',
  KEY `FK_user_ext_user` (`uid`,`mid`),
  CONSTRAINT `FK_user_ext_user` FOREIGN KEY (`uid`, `mid`) REFERENCES `user` (`uid`, `mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户扩展表';

-- 正在导出表  crm_dev.user_ext 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `user_ext` DISABLE KEYS */;
INSERT INTO `user_ext` (`uid`, `mid`, `avatar`, `country`, `province`, `city`, `county`, `ip`) VALUES
	(1, 101, 'http://www.pp.cc/static/common/home_img/newindex/logo-index.png', '中国', '安徽', '合肥', '政务区', 2085194263);
/*!40000 ALTER TABLE `user_ext` ENABLE KEYS */;


-- 导出  表 crm_dev.user_last_active 结构
CREATE TABLE IF NOT EXISTS `user_last_active` (
  `uid` bigint(20) unsigned NOT NULL,
  `mid` bigint(20) unsigned NOT NULL,
  `last_active_time` datetime NOT NULL COMMENT '最后一次交互时间',
  `active_count` mediumint(8) unsigned NOT NULL COMMENT '交互次数',
  KEY `FK_user_last_active_user` (`uid`,`mid`),
  CONSTRAINT `FK_user_last_active_user` FOREIGN KEY (`uid`, `mid`) REFERENCES `user` (`uid`, `mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户最后交互信息表';

-- 正在导出表  crm_dev.user_last_active 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `user_last_active` DISABLE KEYS */;
INSERT INTO `user_last_active` (`uid`, `mid`, `last_active_time`, `active_count`) VALUES
	(1, 101, '2014-03-21 10:10:39', 2);
/*!40000 ALTER TABLE `user_last_active` ENABLE KEYS */;


-- 导出  表 crm_dev.user_tag 结构
CREATE TABLE IF NOT EXISTS `user_tag` (
  `uid` bigint(20) unsigned NOT NULL,
  `mid` bigint(20) unsigned NOT NULL,
  `tag_id` int(11) unsigned NOT NULL COMMENT '标签id',
  UNIQUE KEY `Index 3` (`uid`,`mid`,`tag_id`),
  KEY `FK_user_tag_user` (`uid`,`mid`),
  KEY `FK_user_tag_tag_info` (`tag_id`),
  CONSTRAINT `FK_user_tag_tag_info` FOREIGN KEY (`tag_id`) REFERENCES `tag_info` (`tag_id`) ON DELETE CASCADE,
  CONSTRAINT `FK_user_tag_user` FOREIGN KEY (`uid`, `mid`) REFERENCES `user` (`uid`, `mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户标签表';

-- 正在导出表  crm_dev.user_tag 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `user_tag` DISABLE KEYS */;
INSERT INTO `user_tag` (`uid`, `mid`, `tag_id`) VALUES
	(1, 101, 1),
	(1, 101, 3);
/*!40000 ALTER TABLE `user_tag` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
