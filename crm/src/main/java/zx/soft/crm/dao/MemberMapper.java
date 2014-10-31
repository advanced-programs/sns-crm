package zx.soft.crm.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import zx.soft.crm.model.Member;
import zx.soft.crm.model.MemberExpRecord;
import zx.soft.crm.model.MemberPointRecord;

public interface MemberMapper {

	@Insert("replace into member (uid, mid, address, birthday, member_point"
			+ ", experience, id_card_no, member_card_no, member_level_id, update_time, status) " //
			+ " values (#{uid}, #{mid}, #{address}, #{birthday}, #{member_point}"
			+ ", #{experience}, #{id_card_no}, #{member_card_no}, #{member_level_id}, now(), 0)")
	void add(Member member);

	@Update("update member set update_time = now(), experience = experience + #{exp_change} " //
			+ " where uid = #{uid} and mid = #{mid} and status = 0")
	void addExp(MemberExpRecord record);

	@Update("update member set update_time = now(), member_point = member_point + #{point_change} " //
			+ " where uid = #{uid} and mid = #{mid} and status = 0")
	void addPoint(MemberPointRecord record);

	@Update("update member set update_time = now(), status = -1 " //
			+ " where uid = #{0} and mid = #{1} and status = 0")
	void delete(long uid, long mid);

	@Select("select uid, mid, address, birthday, member_point, experience"
			+ ", id_card_no, member_card_no, member_level_id, update_time " //
			+ "from member where uid = #{0} and mid = #{1} and status = 0")
	Member get(long uid, long mid);

	void update(Member member);

	@Delete("delete FROM `member` WHERE uid = #{0}  and mid = #{1}")
	int deleteMember(long uid, long mid);

	@Select("select count(1) as count from `member` where uid = #{0}")
	int queryCountByUid(long uid);

}
