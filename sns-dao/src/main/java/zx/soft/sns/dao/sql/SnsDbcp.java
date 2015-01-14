package zx.soft.sns.dao.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sns.dao.common.ConfigUtil;

/**
 * 舆情数据JDBC
 * @author wanggang
 *
 */
public class SnsDbcp {

	private static Logger logger = LoggerFactory.getLogger(SnsDbcp.class);

	private BasicDataSource dataSource;

	private String db_url;
	private String db_username;
	private String db_password;

	public SnsDbcp() {
		dbInit();
		dbConnection();
	}

	/**
	 * 初始化数据库相关参数
	 */
	private void dbInit() {
		Properties props = ConfigUtil.getProps("data_db.properties");
		db_url = props.getProperty("sns.db.url");
		db_username = props.getProperty("sns.db.username");
		db_password = props.getProperty("sns.db.password");
	}

	/**
	 * 链接数据库
	 */
	private void dbConnection() {
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(db_url);
		dataSource.setUsername(db_username);
		dataSource.setPassword(db_password);
		dataSource.setTestOnBorrow(true);
		dataSource.setValidationQuery("select 1");
	}

	/**
	 * 获取链接
	 */
	private Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			logger.error("SQLException:{}", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 创建QQ信息数据表
	 */
	public void createQQAccountTable(String tablename) {
		String sql = "CREATE TABLE IF NOT EXISTS "
				+ tablename
				+ " (`id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',"
				+ "`qq` int(10) unsigned NOT NULL COMMENT 'QQ号',`name` char(50) NOT NULL COMMENT '用户名',"
				+ "`gender` char(2) NOT NULL COMMENT '性别',`age` tinyint(3) unsigned NOT NULL COMMENT '年龄',"
				+ "`qq_group` int(10) unsigned NOT NULL COMMENT 'QQ群号',"
				+ "`lasttime` datetime NOT NULL COMMENT '记录时间',"
				+ "PRIMARY KEY (`id`),KEY `qq` (`qq`),KEY `qq_group` (`qq_group`),UNIQUE KEY `qq_qqgroup` (`qq`,`qq_group`)) "
				+ "ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='QQ帐号数据表' AUTO_INCREMENT=1 ;";
		createTable(sql);
	}

	/**
	 * 创建微信公共帐号数据表
	 */
	public void createWeChatPublicAccountTable(String tablename) {
		String sql = "CREATE TABLE IF NOT EXISTS "
				+ tablename
				+ " (`id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',"
				+ "`wid` varchar(50) NOT NULL COMMENT '微信号',`name` char(50) NOT NULL COMMENT '用户名',"
				+ "`open_id` varchar(30) NOT NULL COMMENT 'openid',`head_url` varchar(500) NOT NULL COMMENT '头像地址',"
				+ "`description` varchar(500) NOT NULL COMMENT '功能介绍',`verify_info` varchar(500) NOT NULL COMMENT '认证信息',"
				+ "`last_article_url` varchar(500) NOT NULL COMMENT '最近文章地址',`lasttime` datetime NOT NULL COMMENT '记录时间',"
				+ "PRIMARY KEY (`id`),UNIQUE KEY `wid` (`wid`),KEY `name` (`name`),UNIQUE KEY `open_id` (`open_id`)) "
				+ "ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='微信公共帐号数据表' AUTO_INCREMENT=1 ;";
		createTable(sql);
	}

	/**
	 * 创建微信文章数据表
	 */
	public void createWeChatArticleTable(String tablename) {
		String sql = "CREATE TABLE IF NOT EXISTS "
				+ tablename
				+ " (`id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',"
				+ "`aid` varchar(50) NOT NULL COMMENT '文章唯一ID，由url经MD5加密得到',"
				+ "`title` varchar(50) NOT NULL COMMENT '文章标题',`url` varchar(300) NOT NULL COMMENT '文章地址',"
				+ "`pic_url` varchar(300) NOT NULL COMMENT '文章中图片地址',`timestamp` datetime NOT NULL COMMENT '文章发布时间',"
				+ "`wechat_name` varchar(50) NOT NULL COMMENT '公共号名称',`wechat_open_id` varchar(30) NOT NULL COMMENT '公共号OpenID',"
				+ "`content` mediumtext NOT NULL COMMENT '文章内容'," + "`lasttime` datetime NOT NULL COMMENT '记录时间',"
				+ "PRIMARY KEY (`id`),UNIQUE KEY `aid` (`aid`),KEY `wechat_name` (`wechat_name`),"
				+ "KEY `wechat_open_id` (`wechat_open_id`)) "
				+ "ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='微信文章数据表' AUTO_INCREMENT=1 ;";
		createTable(sql);
	}

	private void createTable(String sql) {
		try (Connection conn = getConnection(); Statement pstmt = conn.createStatement();) {
			pstmt.execute(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 关闭数据库
	 */
	public void close() {
		try {
			dataSource.close();
		} catch (SQLException e) {
			logger.info("Db close error.");
			throw new RuntimeException(e);
		}
	}

	@Override
	public String toString() {
		return "SnsDbcp:[db_url=" + db_url + ",db_username=" + db_username + ",db_password=" + db_password + "]";
	}

}
