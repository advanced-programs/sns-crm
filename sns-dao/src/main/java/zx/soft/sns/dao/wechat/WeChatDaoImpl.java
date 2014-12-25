package zx.soft.sns.dao.wechat;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sns.dao.common.MybatisConfig;
import zx.soft.sns.dao.domain.wechat.WeChatAInfo;
import zx.soft.sns.dao.domain.wechat.WeChatAInsert;
import zx.soft.sns.dao.domain.wechat.WeChatPAInfo;
import zx.soft.sns.dao.domain.wechat.WeChatPAInsert;

public class WeChatDaoImpl {

	private static Logger logger = LoggerFactory.getLogger(WeChatDaoImpl.class);

	private static SqlSessionFactory sqlSessionFactory;

	public WeChatDaoImpl(MybatisConfig.ServerEnum server) {
		try {
			sqlSessionFactory = MybatisConfig.getSqlSessionFactory(server);
		} catch (RuntimeException e) {
			logger.error("Exception:{}, StackTrace:{}", e.getMessage(), e.getStackTrace());
			throw new RuntimeException(e);
		}
	}

	/**
	 * 插入微信公共帐号信息
	 */
	public void insertWeChatPA(WeChatPAInsert weChatPAInsert) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			WeChatDao weixinInfoDao = sqlSession.getMapper(WeChatDao.class);
			weixinInfoDao.insertWeChatPA(weChatPAInsert);
		}
	}

	/**
	 * 根据微信公共帐号wid查询全部信息
	 */
	public WeChatPAInfo selectWeChatPA(String tablename, String wid) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			WeChatDao weixinInfoDao = sqlSession.getMapper(WeChatDao.class);
			return weixinInfoDao.selectWeChatPA(tablename, wid);
		}
	}

	/**
	 * 判断微信公共帐号是否存在
	 */
	public boolean isWeChatPAExisted(String tablename, String wid) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			WeChatDao weixinInfoDao = sqlSession.getMapper(WeChatDao.class);
			if (weixinInfoDao.selectWeChatPAName(tablename, wid) == null) {
				return Boolean.FALSE;
			} else {
				return Boolean.TRUE;
			}
		}
	}

	/**
	 * 删除微信公共号
	 */
	public void deleteWeChatPA(String tablename, String wid) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			WeChatDao weixinInfoDao = sqlSession.getMapper(WeChatDao.class);
			weixinInfoDao.deleteWeChatPA(tablename, wid);
		}
	}

	/**
	 * 插入微信文章信息
	 */
	public void insertWeChatA(WeChatAInsert weChatAInsert) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			WeChatDao weixinInfoDao = sqlSession.getMapper(WeChatDao.class);
			weixinInfoDao.insertWeChatA(weChatAInsert);
		}
	}

	/**
	 * 根据微信文章aid查询全部信息
	 */
	public WeChatAInfo selectWeChatA(String tablename, String aid) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			WeChatDao weixinInfoDao = sqlSession.getMapper(WeChatDao.class);
			return weixinInfoDao.selectWeChatA(tablename, aid);
		}
	}

	/**
	 * 判断微信文章是否存在
	 */
	public boolean isWeChatAExisted(String tablename, String aid) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			WeChatDao weixinInfoDao = sqlSession.getMapper(WeChatDao.class);
			if (weixinInfoDao.selectWeChatATitle(tablename, aid) == null) {
				return Boolean.FALSE;
			} else {
				return Boolean.TRUE;
			}
		}
	}

	/**
	 * 删除微信文章
	 */
	public void deleteWeChatA(String tablename, String aid) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			WeChatDao weixinInfoDao = sqlSession.getMapper(WeChatDao.class);
			weixinInfoDao.deleteWeChatA(tablename, aid);
		}
	}

}
