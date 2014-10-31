package zx.soft.crm.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import zx.soft.crm.model.UserContact;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SnowMan
 * Date: 14-7-15
 * Time: PM1:29
 * 用户扩展表
 */
public interface UserContactMapper {

	@Insert("INSERT INTO user_contact (uid, mid, contact_id, `value`)" +
			" values(#{uid},#{mid},#{contact_id},#{value})")
	void add(UserContact userContact);

	@Select("SELECT id, uid, mid, contact_id, value FROM user_contact " +
			"where uid = #{0} and mid = #{1} and contact_id = #{2}")
	UserContact query(long uid, long mid, int contact_id);

	@Update("UPDATE user_contact set value = #{value} " +
			"where uid = #{uid} and mid = #{mid} and contact_id = #{contact_id}")
	void update(UserContact userContact);

	@Delete("delete FROM `user_contact` WHERE uid = #{0}  and mid = #{1}")
	int delete(long uid, long mid);

	@Select("select id, uid, mid, contact_id, value from user_contact where uid=#{0} and mid=#{1}")
	List<UserContact> queryForList(long uid, long mid);
}
