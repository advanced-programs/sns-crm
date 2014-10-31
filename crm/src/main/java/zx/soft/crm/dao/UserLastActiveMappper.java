package zx.soft.crm.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import zx.soft.crm.model.UserLastActive;

/**
 * Created with IntelliJ IDEA.
 * User: SnowMan
 * Date: 14-7-15
 * Time: PM2:31
 * 用户最后交互信息表DAO
 */
public interface UserLastActiveMappper {

	@Insert("INSERT INTO user_last_active(uid, mid, last_active_time, active_count) VALUES " +
			"(#{uid},#{mid},#{last_active_time},#{active_count})")
	void add(UserLastActive userLastActive);

	@Delete("delete FROM `user_last_active` WHERE uid = #{0}  and mid = #{1}")
	int delete(long uid, long mid);

	@Select("SELECT uid, mid, last_active_time, active_count FROM user_last_active " +
			"where uid = #{0} and mid = #{1}")
	UserLastActive query(long uid, long mid);

	@Update("UPDATE user_last_active set active_count = active_count + 1, last_active_time = #{last_active_time} " +
			"where uid = #{uid} and mid = #{mid}")
	void update(UserLastActive userLastActive);

}
