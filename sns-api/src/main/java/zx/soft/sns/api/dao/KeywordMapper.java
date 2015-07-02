package zx.soft.sns.api.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

public interface KeywordMapper {

	/**
	 * 插入关键词
	 */
	@Insert("INSERT INTO `wechat_keywords` (`kid`,`keyword`,`lasttime`) VALUES (#{0},#{1},NOW())")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void insertKeyword(long kid, String keyword);

	/**
	 * 读取多个关键词
	 */
	@Select("SELECT `id`,`keyword` FROM `wechat_keywords` LIMIT ${num}")
	@ResultType(value = HashMap.class)
	public List<Map<String, Object>> selectKeywords(@Param("num") String num);

	/**
	 * 删除关键词
	 */
	@Delete("DELETE FROM `wechat_keywords` WHERE `id` = #{0}")
	public void deleteKeyword(int id);

}
