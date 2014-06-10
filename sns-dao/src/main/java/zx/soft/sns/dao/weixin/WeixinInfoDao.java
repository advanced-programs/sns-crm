package zx.soft.sns.dao.weixin;

import java.util.List;

import zx.soft.sns.dao.domain.WeixinRecordInfo;
import zx.soft.sns.dao.domain.WeixinRecordInsert;
import zx.soft.sns.dao.domain.WeixinRecordSelect;

public interface WeixinInfoDao {

	/**
	 * 插入微信记录数据
	 */
	public void insertWeixinRecord(WeixinRecordInsert weixinRecordInsert);

	/**
	 * 查询微信记录数据，根据微信号查询所有相关记录
	 */
	public List<WeixinRecordInfo> selectWeixinRecordsByWid(WeixinRecordSelect weixinRecordSelect);

}
