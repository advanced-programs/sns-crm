package zx.soft.crm.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import zx.soft.crm.model.UserExt;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: SnowMan
 * Date: 14-7-15
 * Time: AM10:06
 * 用户扩展表Dao
 */
public interface UserExtMapper {

	@Insert("INSERT INTO user_ext (uid, mid, avatar, country, province, city, county, ip)" +
			" values(#{uid}, #{mid}, #{avatar}, #{country}, #{province},#{city},#{county},INET_ATON(#{ip}))")
	void add(UserExt userExt);

	@Delete("delete FROM `user_ext` WHERE uid = #{0}  and mid = #{1}")
	int delete(long uid, long mid);

	@Select("SELECT uid, mid, avatar, country, province, city, county, INET_NTOA(ip) ip FROM user_ext " +
			"where uid = #{0} and mid = #{1}")
	UserExt query(long uid, long mid);

	//用户属性统计
	@Select("select province, count(*) as count from user_ext where uid = #{0} group by province ")
	List<Map<String, Object>> queryProvinceByUid(long uid);

	@Update("update `user_ext` set avatar=#{avatar},country=#{country},province=#{province},city=#{city},county=#{county}," +
			"ip=INET_ATON(#{ip}) where uid=#{uid} and mid=#{mid}")
	void update(UserExt userExt);

	//用户属性统计
	@Select("SELECT province, count(*) as count FROM user_ext LEFT JOIN platform ON user_ext.uid = platform.uid " +
			"AND user_ext.mid = platform.mid WHERE user_ext.uid = #{0} and platform.platform=#{1} GROUP BY province")
	List<Map<String, Object>> queryProvinceByUidAndPlatform(long uid, int platform);
}
