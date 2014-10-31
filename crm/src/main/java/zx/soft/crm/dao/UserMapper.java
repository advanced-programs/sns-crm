package zx.soft.crm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

import zx.soft.crm.model.User;
import zx.soft.crm.model.UserQueryCondition;

	public interface UserMapper 
    {

		@Insert("insert into user (uid, mid, is_member, identify, name, nick, gender, update_time) " //
				+ " values (#{uid}, #{mid}, #{is_member}, #{identify}, #{name}, #{nick}, #{gender}, now())")
		@Options(useGeneratedKeys = true, keyProperty = "mid")
		void add(User user);

		int countList(UserQueryCondition condition);

		@Update("update user set update_time = now(), is_member = 0 " //
				+ " where uid = #{0} and mid = #{1}")
		void exitMember(long uid, long mid);

		@Select("select uid, mid, is_member, identify, name, nick, gender, update_time, status " //
				+ " from user where uid = #{0} and mid = #{1}")
		User get(long uid, long mid);

		@Update("update user set update_time = now(), is_member = 1 " //
				+ " where uid = #{0} and mid = #{1}")
		void joinMember(long uid, long mid);

		List<User> list(UserQueryCondition condition);

		int update(User user);

		//用户属性统计
		@Select("select status, count(*) as count from user where uid = #{0} group by status ")
		List<Map<String, Object>> queryStatusByUid(long uid);

		//用户属性统计
		@Select("select gender, count(*) as count from user where uid = #{0} group by gender ")
		List<Map<String, Object>> queryGenderByUid(long uid);

		//用户属性统计
		@Select("SELECT status , count(*) as count FROM user LEFT JOIN platform ON user.uid = platform.uid " +
				"AND user.mid = platform.mid WHERE user.uid = #{0} AND platform.platform = #{1} GROUP BY STATUS ")
		List<Map<String, Object>> queryStatusByUidAndPlatform(long uid, int platform);

		//用户属性统计
		@Select("SELECT gender , count(*) as count FROM user LEFT JOIN platform ON user.uid = platform.uid " +
				"AND user.mid = platform.mid WHERE user.uid = #{0} AND platform.platform = #{1} GROUP BY STATUS ")
		List<Map<String, Object>> queryGenderByUidAndPlatform(long uid, int platform);

		@Delete("delete FROM `user` WHERE uid = #{0}  and mid = #{1}")
		int delete(long uid, long mid);

		@Select("SELECT count(1) as count FROM `user`  WHERE uid = #{0}")
		int queryCountByUid(long uid);


}
