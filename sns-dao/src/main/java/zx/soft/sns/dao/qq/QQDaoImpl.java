package zx.soft.sns.dao.qq;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sns.dao.common.MybatisConfig;
import zx.soft.sns.dao.domain.qq.QQAccountInfo;
import zx.soft.sns.dao.domain.qq.QQAccountInsert;

public class QQDaoImpl {

	private static Logger logger = LoggerFactory.getLogger(QQDaoImpl.class);

	private static SqlSessionFactory sqlSessionFactory;

	public QQDaoImpl(MybatisConfig.ServerEnum server) {
		try {
			sqlSessionFactory = MybatisConfig.getSqlSessionFactory(server);
		} catch (RuntimeException e) {
			logger.error("Exception:{}, StackTrace:{}", e.getMessage(), e.getStackTrace());
			throw new RuntimeException(e);
		}
	}

	/**
	 * 插入QQ帐号信息
	 */
	public void insertQQAccount(QQAccountInsert qqAccountInsert) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			QQDao qqDao = sqlSession.getMapper(QQDao.class);
			qqDao.insertQQAccount(qqAccountInsert);
		}
	}

	/**
	 * 根据QQ帐号qq查询全部信息（包括群信息）
	 */
	public List<QQAccountInfo> selectQQAccounts(String tablename, long qq) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			QQDao qqDao = sqlSession.getMapper(QQDao.class);
			return qqDao.selectQQAccounts(tablename, qq);
		}
	}

	/**
	 * 判断记录是否存在
	 */
	public boolean isExistedQQAccount(String tablename, long qq, long qqGroup) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			QQDao qqDao = sqlSession.getMapper(QQDao.class);
			if (qqDao.selectQQAccountName(tablename, qq, qqGroup) == null) {
				return Boolean.FALSE;
			} else {
				return Boolean.TRUE;
			}
		}
	}

	/**
	 * 删除QQ号信息
	 */
	public void deleteQQAccount(String tablename, long qq, long qqGroup) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			QQDao qqDao = sqlSession.getMapper(QQDao.class);
			qqDao.deleteQQAccount(tablename, qq, qqGroup);
		}
	}

}
