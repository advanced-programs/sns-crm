package zx.soft.sns.dao.qq;

import java.util.List;

import zx.soft.sns.dao.domain.QQRecordInfo;
import zx.soft.sns.dao.domain.QQRecordInsert;
import zx.soft.sns.dao.domain.QQRecordsSelect;

public interface QQInfoDao {

	/**
	 * 插入QQ记录数据
	 */
	public void insertQQRecord(QQRecordInsert qqRecordInsert);

	/**
	 * 查询QQ记录数据，根据qq号查询所有相关记录
	 */
	public List<QQRecordInfo> selectQQRecordsByQQ(QQRecordsSelect qqRecordsSelect);

	/**
	 * 查询QQ记录数据，根据qq群号查询所有相关记录
	 */
	public List<QQRecordInfo> selectQQRecordsByQQGroup(QQRecordsSelect qqRecordsSelect);


}
