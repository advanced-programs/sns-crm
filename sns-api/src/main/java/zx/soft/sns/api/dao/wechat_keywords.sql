-- phpMyAdmin SQL Dump
-- version 4.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2015-07-02 03:53:29
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
-- 表的结构 `wechat_keywords`
--

CREATE TABLE IF NOT EXISTS `wechat_keywords` (
`id` int(11) NOT NULL COMMENT '自增ID',
  `kid` bigint(20) unsigned NOT NULL COMMENT '关键词CRC32编码',
  `keyword` char(50) NOT NULL COMMENT '关键词',
  `lasttime` datetime NOT NULL COMMENT '插入时间'
) ENGINE=MEMORY  DEFAULT CHARSET=utf8 COMMENT='关键词列表' AUTO_INCREMENT=8 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `wechat_keywords`
--
ALTER TABLE `wechat_keywords`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `kid` (`kid`), ADD KEY `lasttime` (`lasttime`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `wechat_keywords`
--
ALTER TABLE `wechat_keywords`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',AUTO_INCREMENT=8;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
