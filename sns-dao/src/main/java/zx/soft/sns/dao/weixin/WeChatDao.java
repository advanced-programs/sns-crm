package zx.soft.sns.dao.weixin;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import zx.soft.sns.dao.domain.wechat.WeChatAInfo;
import zx.soft.sns.dao.domain.wechat.WeChatAInsert;
import zx.soft.sns.dao.domain.wechat.WeChatPAInfo;
import zx.soft.sns.dao.domain.wechat.WeChatPAInsert;

public interface WeChatDao {

	/**
	 * 插入微信公共帐号信息
	 */
	public void insertWeChatPA(WeChatPAInsert weChatPAInsert);

	/**
	 * 根据微信公共帐号wid查询全部信息
	 */
	@Select("SELECT `wid`,`name`,`open_id`,`head_url`,`description`,`verify_info`,`last_article_url` "
			+ "FROM ${tablename} WHERE `wid` = #{wid}")
	@ResultType(value = WeChatPAInfo.class)
	public WeChatPAInfo selectWeChatPA(@Param("tablename") String tablename, @Param("wid") String wid);

	/**
	 * 根据微信公共帐号wid查询名称
	 */
	@Select("SELECT `name` FROM ${tablename} WHERE `wid` = #{wid}")
	public String selectWeChatPAName(@Param("tablename") String tablename, @Param("wid") String wid);

	/**
	 * 删除微信公共号
	 */
	@Delete("DELETE FROM ${tablename} WHERE `wid` = #{wid}")
	public void deleteWeChatPA(@Param("tablename") String tablename, @Param("wid") String wid);

	/**
	 * 插入微信文章信息
	 */
	public void insertWeChatA(WeChatAInsert weChatAInsert);

	/**
	 * 根据微信文章aid查询全部信息
	 */
	@Select("SELECT `aid`,`title`,`url`,`pic_url`,`timestamp`,`wechat_name`,`wechat_open_id`,`content` "
			+ "FROM ${tablename} WHERE `aid` = #{aid}")
	@ResultType(value = WeChatAInfo.class)
	public WeChatAInfo selectWeChatA(@Param("tablename") String tablename, @Param("aid") String aid);

	/**
	 * 根据微信文章aid查询文章标题
	 */
	@Select("SELECT `title` FROM ${tablename} WHERE `aid` = #{aid}")
	public String selectWeChatATitle(@Param("tablename") String tablename, @Param("aid") String aid);

	/**
	 * 删除微信文章
	 */
	@Delete("DELETE FROM ${tablename} WHERE `aid` = #{aid}")
	public void deleteWeChatA(@Param("tablename") String tablename, @Param("aid") String aid);

}
