package zx.soft.crm.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import zx.soft.crm.model.MemberExpAndPointRecord;
import zx.soft.crm.model.MemberExpRecord;
import zx.soft.crm.model.RecordQueryCondition;

import java.util.List;

public interface MemberExpRecordMapper {

	@Insert("insert into member_exp_record (uid, mid, reason, exp_change, create_time) " //
			+ " values (#{uid}, #{mid}, #{reason}, #{exp_change}, now())")
	void add(MemberExpRecord record);

	int countList(RecordQueryCondition condition);

	@Delete("delete FROM `member_exp_record` WHERE uid = #{0}  and mid = #{1}")
	int delete(long uid, long mid);

	List<MemberExpRecord> list(RecordQueryCondition condition);

	List<MemberExpAndPointRecord> listExpAndPoint(RecordQueryCondition condition);

}
