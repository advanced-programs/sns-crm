-- phpMyAdmin SQL Dump
-- version 4.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2015-07-01 10:46:45
-- 服务器版本： 5.5.37-MariaDB-log
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `weichat_public`
--

-- --------------------------------------------------------

--
-- 表的结构 `wechat_public_accounts`
--

CREATE TABLE IF NOT EXISTS `wechat_public_accounts` (
`id` int(10) unsigned NOT NULL COMMENT '自增id',
  `wid` varchar(50) NOT NULL COMMENT '微信号',
  `name` char(50) NOT NULL COMMENT '用户名',
  `open_id` varchar(30) NOT NULL COMMENT 'openid',
  `head_url` varchar(500) NOT NULL COMMENT '头像地址',
  `description` varchar(500) NOT NULL COMMENT '功能介绍',
  `verify_info` varchar(500) NOT NULL COMMENT '认证信息',
  `last_article_url` varchar(500) NOT NULL COMMENT '最近文章地址',
  `lasttime` datetime NOT NULL COMMENT '记录时间'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='微信公共帐号数据表' AUTO_INCREMENT=1 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `wechat_public_accounts`
--
ALTER TABLE `wechat_public_accounts`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `wid` (`wid`), ADD UNIQUE KEY `open_id` (`open_id`), ADD KEY `name` (`name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `wechat_public_accounts`
--
ALTER TABLE `wechat_public_accounts`
MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id';
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
