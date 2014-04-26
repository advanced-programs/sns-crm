package zx.soft.sns.dao.qq;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sns.dao.common.MybatisConfig;
import zx.soft.sns.dao.domain.QQRecordInfo;
import zx.soft.sns.dao.domain.QQRecordInsert;
import zx.soft.sns.dao.domain.QQRecordsSelect;

public class QQInfo {

	private static Logger logger = LoggerFactory.getLogger(QQInfo.class);

	private static SqlSessionFactory sqlSessionFactory;

	public QQInfo(MybatisConfig.ServerEnum server) {
		try {
			sqlSessionFactory = MybatisConfig.getSqlSessionFactory(server);
		} catch (RuntimeException e) {
			logger.error("QQInfo RuntimeException: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	/**
	 * 插入QQ记录数据
	 */
	public void insertQQRecord(QQRecordInsert qqRecordInsert) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			QQInfoDao qqInfoDao = sqlSession.getMapper(QQInfoDao.class);
			qqInfoDao.insertQQRecord(qqRecordInsert);
		}
	}

	/**
	 * 查询QQ记录数据，根据qq号查询所有相关记录
	 */
	public List<QQRecordInfo> selectQQRecordsByQQ(String tablename, long qq) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			QQInfoDao qqInfoDao = sqlSession.getMapper(QQInfoDao.class);
			return qqInfoDao.selectQQRecordsByQQ(new QQRecordsSelect(tablename, qq));
		}
	}

	/**
	 * 查询QQ记录数据，根据qq群号查询所有相关记录
	 */
	public List<QQRecordInfo> selectQQRecordsByQQGroup(String tablename, long qq) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			QQInfoDao qqInfoDao = sqlSession.getMapper(QQInfoDao.class);
			return qqInfoDao.selectQQRecordsByQQGroup(new QQRecordsSelect(tablename, qq));
		}
	}

}
