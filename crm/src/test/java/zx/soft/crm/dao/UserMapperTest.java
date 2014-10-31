package zx.soft.crm.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import zx.soft.crm.dao.UserMapper;
import zx.soft.crm.model.User;
import zx.soft.crm.model.UserQueryCondition;

import javax.inject.Inject;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/webapp/WEB-INF/applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public class UserMapperTest {

	final long uid = 1;
	final String member_100 = "User [uid=1, mid=100, is_member=0, identify=15000000000, name=张三, nick=张三昵称, gender=0, status=0]";
	final String member_101 = "User [uid=1, mid=101, is_member=1, identify=15888888888, name=李四, nick=李四昵称, gender=1, status=0]";
	final String member_102 = "User [uid=1, mid=101, is_member=1, identify=15888888888, name=李四, nick=李四昵称, gender=1, status=0]";
	final String member_103 = "User [uid=1, mid=100, is_member=0, identify=15000000000, name=张三, nick=张三昵称, gender=0, status=0]";
	@Inject
	private UserMapper userMapper;

	@Test
	public void testAdd() {
		User user = new User().setUid(200).defaultValue();
		userMapper.add(user);
		assertEquals(user.toString(), userMapper.get(200, user.getMid()).toString());

		user = new User().setUid(200).setIs_member(1).setIdentify("18888888888").setName("赵六").setNick("赵六昵称")
				.setGender(2);
		userMapper.add(user);
		assertEquals(user.toString(), userMapper.get(200, user.getMid()).toString());
	}

	@Test
	public void testGetUser() {
		User user = userMapper.get(uid, 101);
		assertEquals(member_102, user.toString());
		assertNull(userMapper.get(uid, 12345678)); // 没有该用户
	}

	@Test
	public void testGetUsers() {
		UserQueryCondition condition = new UserQueryCondition().setUid(uid);
		List<User> users = userMapper.list(condition);

		assertEquals(2, users.size());
		assertEquals(member_102, users.get(0).toString());
	}

	@Test
	public void testGetUsers_city() {
		UserQueryCondition condition = new UserQueryCondition().setUid(uid).setCity("合肥");
		List<User> users = userMapper.list(condition);
		assertEquals(1, users.size());
		assertEquals(member_101, users.get(0).toString());
	}

	@Test
	public void testGetUsers_gender() {
		UserQueryCondition condition = new UserQueryCondition().setUid(uid).setGender(1);
		List<User> users = userMapper.list(condition);
		assertEquals(1, users.size());
		assertEquals(member_101, users.get(0).toString());
	}

	@Test
	public void testGetUsers_has_activity() {
		UserQueryCondition condition = new UserQueryCondition().setUid(uid).setHas_activity(true);
		List<User> users = userMapper.list(condition);
		assertEquals(1, users.size());
		assertEquals(member_101, users.get(0).toString());

		condition.setHas_activity(false);
		users = userMapper.list(condition);
		assertEquals(1, users.size());
		assertEquals(member_100, users.get(0).toString());
	}

	@Test
	public void testGetUsers_has_email() {
		UserQueryCondition condition = new UserQueryCondition().setUid(uid).setHas_email(true);
		List<User> users = userMapper.list(condition);
		assertEquals(1, users.size());
		assertEquals(member_100, users.get(0).toString());

		condition.setHas_email(false);
		users = userMapper.list(condition);
		assertEquals(1, users.size());
		assertEquals(member_101, users.get(0).toString());
	}

	@Test
	public void testGetUsers_has_qq() {
		UserQueryCondition condition = new UserQueryCondition().setUid(uid).setHas_qq(true);
		List<User> users = userMapper.list(condition);
		assertEquals(1, users.size());
		assertEquals(member_101, users.get(0).toString());

		condition.setHas_qq(false);
		users = userMapper.list(condition);
		assertEquals(1, users.size());
		assertEquals(member_100, users.get(0).toString());
	}

	@Test
	public void testGetUsers_has_telephone() {
		UserQueryCondition condition = new UserQueryCondition().setUid(uid).setHas_telephone(true);
		List<User> users = userMapper.list(condition);
		assertEquals(1, users.size());
		assertEquals(member_100, users.get(0).toString());

		condition.setHas_telephone(false);
		users = userMapper.list(condition);
		assertEquals(1, users.size());
		assertEquals(member_101, users.get(0).toString());
	}

	@Test
	public void testGetUsers_is_member() {
		UserQueryCondition condition = new UserQueryCondition().setUid(uid).setIs_member(1);
		List<User> users = userMapper.list(condition);
		assertEquals(1, users.size());
		assertEquals(member_101, users.get(0).toString());
	}

	@Test
	public void testGetUsers_page() {
		UserQueryCondition condition = new UserQueryCondition().setUid(uid).setPage(2).setPer_page(1);
		List<User> users = userMapper.list(condition);
		assertEquals(1, users.size());
		assertEquals(member_103, users.get(0).toString());
	}

	@Test
	public void testGetUsers_platform() {
		UserQueryCondition condition = new UserQueryCondition().setUid(uid).setPlatform(1);
		List<User> users = userMapper.list(condition);
		assertEquals(1, users.size());
		assertEquals(member_101, users.get(0).toString());
	}

	@Test
	public void testGetUsers_province() {
		UserQueryCondition condition = new UserQueryCondition().setUid(uid).setProvince("安徽");
		List<User> users = userMapper.list(condition);
		assertEquals(1, users.size());
		assertEquals(member_101, users.get(0).toString());
	}

	@Test
	public void testGetUsers_tag_id() {
		UserQueryCondition condition = new UserQueryCondition().setUid(uid).setTag_id(3);
		List<User> users = userMapper.list(condition);
		assertEquals(1, users.size());
		assertEquals(member_101, users.get(0).toString());
	}

	@Test
	public void testList() {
		UserQueryCondition condition = new UserQueryCondition().setUid(1);
		List<User> list = userMapper.list(condition);
		assertEquals(2, list.size());
		assertEquals(2, userMapper.countList(condition));
	}

	@Test
	public void testQueryCountByUid() {
		int count = userMapper.queryCountByUid(1);
		assertEquals(count, 3);
	}

	@Test
	public void testQueryGenderByUid() {
		long uid = 1L;
		List<Map<String, Object>> mapList = userMapper.queryGenderByUid(uid);
		Map<String, Object> resultMap = mapList.get(0);
		int status = (int) resultMap.get("gender");
		long count = (long) resultMap.get("count");
		assertEquals(status, 0);
		assertEquals(count, 2);
	}

	@Test
	public void testQueryGenderByUidAndPlatform() {
		List<Map<String, Object>> mapList = userMapper.queryGenderByUidAndPlatform(1, 1);
		Map<String, Object> resultMap = mapList.get(0);
		int status = (int) resultMap.get("gender");
		long count = (long) resultMap.get("count");
		assertEquals(status, 1);
		assertEquals(count, 1);
	}

	@Test
	public void testQueryStatusByUid() {
		long uid = 1L;
		List<Map<String, Object>> mapList = userMapper.queryStatusByUid(uid);
		Map<String, Object> resultMap = mapList.get(0);
		int status = (int) resultMap.get("status");
		long count = (long) resultMap.get("count");
		assertEquals(status, -1);
		assertEquals(count, 1);
	}

	@Test
	public void testQueryStatusByUidAndPlatform() {
		List<Map<String, Object>> mapList = userMapper.queryStatusByUidAndPlatform(1, 1);
		Map<String, Object> resultMap = mapList.get(0);
		int status = (int) resultMap.get("status");
		long count = (long) resultMap.get("count");
		assertEquals(status, 0);
		assertEquals(count, 1);
	}

	@Test
	public void testUpdate() {
		User user = userMapper.get(uid, 100);
		user.setIdentify("18888888888").setName("张三更新").setGender(1).setStatus(1);
		userMapper.update(user);
		assertEquals(
				"User [uid=1, mid=100, is_member=0, identify=18888888888, name=张三更新, nick=张三昵称, gender=1, status=1]",
				userMapper.get(uid, 100).toString());

		user.setName(""); // 抹掉名字
		userMapper.update(user);
		assertEquals("User [uid=1, mid=100, is_member=0, identify=18888888888, name=, nick=张三昵称, gender=1, status=1]",
				userMapper.get(uid, 100).toString());
	}

//	@Test
//	public void toJson() {
//		User user = new User().setUid(1).setMid(1).setIs_member(1).setIdentify("11").setName("zhangsan").setGender(1)
//				.setStatus(1);
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//			System.out.println(objectMapper.writeValueAsString(user));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}
