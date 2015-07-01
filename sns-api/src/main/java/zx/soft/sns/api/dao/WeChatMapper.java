package zx.soft.sns.api.dao;

import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import zx.soft.sns.api.domain.WeChatPublicAccount;

public interface WeChatMapper {

	/**
	 * 插入微信公共帐号信息
	 */
	public void insertWeChatPA(WeChatPublicAccount weChatPublicAccount);

	/**
	 * 根据微信公共帐号wid查询全部信息
	 */
	@Select("SELECT `wid`,`name`,`open_id`,`head_url`,`description`,`verify_info`,`last_article_url` "
			+ "FROM `wechat_public_accounts` WHERE `wid` = #{wid}")
	@ConstructorArgs()
	@ResultType(value = WeChatPublicAccount.class)
	public WeChatPublicAccount selectWeChatPA(@Param("wid") String wid);

	/**
	 * 根据微信公共帐号wid查询名称
	 */
	@Select("SELECT `name` FROM `wechat_public_accounts` WHERE `wid` = #{wid}")
	public String selectWeChatPAName(@Param("wid") String wid);

	/**
	 * 更新帐号
	 */
	@Update("UPDATE `wechat_public_accounts` SET `name` = #{name},`head_url` = #{head_url},`description` = #{description},"
			+ "`verify_info` = #{verify_info},`last_article_url` = #{last_article_url},`lasttime` = NOW() WHERE "
			+ "`wid` = #{wid}")
	public void updateWeChatPA(WeChatPublicAccount weChatPublicAccount);

	/**
	 * 删除微信公共号
	 */
	@Delete("DELETE FROM `wechat_public_accounts` WHERE `wid` = #{0}")
	public void deleteWeChatPA(String p);

}
