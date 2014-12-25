package zx.soft.sns.dao.qq;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import zx.soft.sns.dao.domain.qq.QQAccountInfo;
import zx.soft.sns.dao.domain.qq.QQAccountInsert;

public interface QQDao {

	/**
	 * 插入QQ帐号信息
	 */
	public void insertQQAccount(QQAccountInsert qqAccountInsert);

	/**
	 * 根据QQ帐号qq查询全部信息（包括群信息）
	 */
	@Select("SELECT `qq`,`name`,`gender`,`age`,`qq_group` FROM ${tablename} WHERE `qq` = #{qq}")
	@ResultType(value = QQAccountInfo.class)
	public List<QQAccountInfo> selectQQAccounts(@Param("tablename") String tablename, @Param("qq") long qq);

	/**
	 * 根据QQ帐号qq查询名称
	 */
	@Select("SELECT `name` FROM ${tablename} WHERE `qq` = #{qq} AND `qq_group` = #{qqGroup}")
	public String selectQQAccountName(@Param("tablename") String tablename, @Param("qq") long qq,
			@Param("qqGroup") long qqGroup);

	/**
	 * 删除QQ号信息
	 */
	@Delete("DELETE FROM ${tablename} WHERE `qq` = #{qq} AND `qq_group` = #{qqGroup}")
	public void deleteQQAccount(@Param("tablename") String tablename, @Param("qq") long qq,
			@Param("qqGroup") long qqGroup);

}
