package zx.soft.crm.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import zx.soft.crm.model.TagInfo;

import java.util.List;

public interface TagInfoMapper {

	@Insert("insert into tag_info (uid, tag_name) " //
			+ " values (#{uid}, #{tag_name})")
	@Options(useGeneratedKeys = true, keyProperty = "tag_id")
	void add(TagInfo tagInfo);

	@Delete("delete from tag_info where uid = #{0} and tag_id = #{1}")
	void delete(long uid, int tag_id);

	@Select("select uid, tag_id, tag_name " //
			+ " from tag_info where uid = #{0} and tag_id = #{1}")
	TagInfo get(long uid, int tag_id);

	@Select("select tag_id, uid, tag_name " //
			+ " from tag_info where uid = #{0}")
	List<TagInfo> list(long uid);

	@Select("SELECT  tag_info.tag_id,tag_info.uid,tag_info.tag_name FROM  `user_tag` " +
			"LEFT JOIN tag_info ON user_tag.tag_id = tag_info.tag_id " +
			"WHERE user_tag.uid =#{0} AND user_tag.mid = #{1}")
	List<TagInfo> queryTagInfo(long uid, long mid);

	@Insert("replace into tag_info (tag_id,uid, tag_name) " //
			+ " values (#{tag_id}, #{uid} ,#{tag_name})")
	void update(TagInfo tagInfo);


}
