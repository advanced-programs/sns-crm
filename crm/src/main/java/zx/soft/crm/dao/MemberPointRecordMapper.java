package zx.soft.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import zx.soft.crm.model.MemberPointRecord;
import zx.soft.crm.model.RecordQueryCondition;

public interface MemberPointRecordMapper {

	@Insert("insert into member_point_record (uid, mid, reason, point_change, create_time) " //
			+ " values (#{uid}, #{mid}, #{reason}, #{point_change}, now())")
	void add(MemberPointRecord record);

	List<MemberPointRecord> list(RecordQueryCondition condition);

	int countList(RecordQueryCondition condition);

	@Delete("delete FROM `member_point_record` WHERE uid = #{0}  and mid = #{1}")
	int delete(long uid, long mid);

}
