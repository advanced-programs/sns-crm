package zx.soft.crm.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import zx.soft.crm.model.Platform;
import zx.soft.crm.model.UserIncrement;

public interface PlatformMapper {

	@Select("select uid, mid, platform, platform_user_id, create_time " //
			+ " from platform where uid = #{0} and mid = #{1}")
	List<Platform> list(long uid, long mid);

	@Insert("INSERT INTO platform (uid, mid, platform, platform_user_id, create_time)" +
			" values(#{uid},#{mid},#{platform},#{platform_user_id}, now())")
	void add(Platform platform);

	@Update("update platform set platform_user_id = #{platform_user_id} " +
			"where uid = #{uid} and mid = #{mid} and platform = #{platform}")
	void update(Platform platform);

	@Select("select uid, mid, platform, platform_user_id, create_time " //
			+ " from platform where uid = #{0} and mid = #{1} and platform = #{2}")
	Platform query(long uid, long mid, int platform);

	@Select("select uid, mid, platform, platform_user_id, create_time " //
			+ " from platform where uid = #{0} and platform_user_id = #{1}")
	List<Platform> queryByUidAndUserId(long uid, String platform_user_id);

	@Select("SELECT COUNT(*) AS count, platform, DATE_FORMAT(last_active_time, '%Y-%m-%d') AS " +
			"DAY FROM platform JOIN user_last_active ON platform.uid = user_last_active.uid " +
			"AND platform.mid = user_last_active.mid WHERE platform.uid = #{0} " +
			"AND last_active_time >=  #{1} AND last_active_time <= #{2} GROUP BY DAY , platform")
	List<UserIncrement> queryByUidAndDate(long uid, Date start, Date end);

	//用户属性统计
	@Select("select platform,count(*) as count from platform where uid = #{0} group by platform ")
	List<Map<String, Object>> queryByUid(long uid);

	//用户属性统计带
	@Select("select platform,count(*) as count from platform where uid = #{0} and platform = #{1} group by platform ")
	List<Map<String, Object>> queryByUidAndlatform(long uid,int platform);

	@Delete("delete FROM `platform` WHERE uid = #{0}  and mid = #{1}")
	int delete(long uid, long mid);

}
