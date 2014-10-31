package zx.soft.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import zx.soft.crm.model.MemberLevelInfo;

public interface MemberLevelInfoMapper {

	@Insert("insert into member_level_info (uid, level_name, level_description, exp_start, exp_end, discount) " //
			+ " values (#{uid}, #{level_name}, #{level_description}, #{exp_start}, #{exp_end}, #{discount})")
	@Options(useGeneratedKeys = true, keyProperty = "member_level_id")
	void add(MemberLevelInfo memberLevelInfo);

	@Delete("delete from member_level_info where uid = #{0} and member_level_id = #{1}")
	void delete(long uid, int member_level_id);

	@Select("select member_level_id, uid, level_name, level_description, exp_start, exp_end, discount " //
			+ " from member_level_info where uid = #{0} and member_level_id = #{1}")
	MemberLevelInfo get(long uid, int member_level_id);

	@Select("select member_level_id, uid, level_name, level_description, exp_start, exp_end, discount " //
			+ " from member_level_info where uid = #{0}")
	List<MemberLevelInfo> list(long uid);

	@Insert("replace into member_level_info (member_level_id, uid, level_name, level_description, exp_start, exp_end, discount) " //
			+ " values (#{member_level_id}, #{uid}, #{level_name}, #{level_description}, #{exp_start}, #{exp_end}, #{discount})")
	void update(MemberLevelInfo memberLevelInfo);

}
