package zx.soft.sns.dao.qq;

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
public class SnsJDBC {

	private static Logger logger = LoggerFactory.getLogger(SnsJDBC.class);

	private BasicDataSource dataSource;

	private String db_url;
	private String db_username;
	private String db_password;

	public SnsJDBC() {
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
		}
	}

	@Override
	public String toString() {
		return "SentJDBC:[db_url=" + db_url + ",db_username=" + db_username + ",db_password=" + db_password + "]";
	}

	/**
	 * 创建QQ数据表
	 */
	public void createQQTable(String tablename) {

		String sql = "CREATE TABLE IF NOT EXISTS "
				+ tablename
				+ " (`id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',"
				+ "`qq` int(10) unsigned NOT NULL COMMENT 'QQ号',`name` varchar(50) NOT NULL COMMENT '群备注',"
				+ "`sex` tinyint(4) NOT NULL COMMENT '性别：0--男，1---女',`age` smallint(5) unsigned NOT NULL COMMENT '年龄',"
				+ "`qq_group` int(10) unsigned NOT NULL COMMENT '所属QQ群号',`lasttime` int(10) unsigned NOT NULL COMMENT '采集时间',"
				+ "PRIMARY KEY (`id`),UNIQUE KEY `qq_qq_group` (`qq`,`qq_group`),KEY `qq` (`qq`),KEY `qq_group` (`qq_group`)) "
				+ "ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='QQ数据表' AUTO_INCREMENT=1 ;";
		try (Connection conn = getConnection(); Statement pstmt = conn.createStatement();) {
			pstmt.execute(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
