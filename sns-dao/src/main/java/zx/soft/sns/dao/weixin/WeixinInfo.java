package zx.soft.sns.dao.weixin;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sns.dao.common.MybatisConfig;
import zx.soft.sns.dao.domain.WeixinRecordInfo;
import zx.soft.sns.dao.domain.WeixinRecordInsert;
import zx.soft.sns.dao.domain.WeixinRecordSelect;

public class WeixinInfo {

	private static Logger logger = LoggerFactory.getLogger(WeixinInfo.class);

	private static SqlSessionFactory sqlSessionFactory;

	public WeixinInfo(MybatisConfig.ServerEnum server) {
		try {
			sqlSessionFactory = MybatisConfig.getSqlSessionFactory(server);
		} catch (RuntimeException e) {
			logger.error("WeixinInfo RuntimeException: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	/**
	 * 插入微信记录数据
	 */
	public void insertWeixinRecord(WeixinRecordInsert weixinRecordInsert) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			WeixinInfoDao weixinInfoDao = sqlSession.getMapper(WeixinInfoDao.class);
			weixinInfoDao.insertWeixinRecord(weixinRecordInsert);
		}
	}

	/**
	 * 查询微信记录数据，根据微信号查询所有相关记录
	 */
	public List<WeixinRecordInfo> selectWeixinRecordsByWid(String tablename, String wid) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			WeixinInfoDao weixinInfoDao = sqlSession.getMapper(WeixinInfoDao.class);
			return weixinInfoDao.selectWeixinRecordsByWid(new WeixinRecordSelect(tablename, wid));
		}
	}

}
