package zx.soft.crm.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import zx.soft.crm.model.UserTag;

import java.util.List;

public interface UserTagMapper {

	@Insert("replace into user_tag values (#{uid}, #{mid}, #{tag_id})")
	int add(UserTag userTag);

	@Delete("delete from user_tag where uid = #{uid} and mid = #{mid} and tag_id = #{tag_id}")
	void delete(UserTag userTag);

	@Delete("delete FROM `user_tag` WHERE uid = #{0}  and mid = #{1}")
	int deleteUserTag(long uid, long mid);

	@Select("select uid, mid, tag_id " //
			+ " from user_tag where uid = #{0} and mid = #{1}")
	List<UserTag> getUserTags(long uid, long mid);

}
