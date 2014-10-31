package zx.soft.crm.dao;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import zx.soft.crm.dao.UserExtMapper;
import zx.soft.crm.model.UserExt;

import javax.inject.Inject;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: SnowMan
 * Date: 14-7-15
 * Time: AM10:15
 * 用户扩展表Test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/webapp/WEB-INF/applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public class UserExtMapperTest {

	@Inject
	private UserExtMapper userExtMapper;

	@Test
	public void testAdd() {
		UserExt userExt = new UserExt().setAvatar("/user").setMid(100).setUid(1).setCity("中国")
				.setCountry("陕西").setIp("127.0.0.1").setProvince("陕西").setCounty("新城区");
		userExtMapper.add(userExt);
		UserExt result = userExtMapper.query(1, 100);
		assertEquals(result.toString(), "UserExt{uid=1, mid=100, avatar='/user', country='陕西', province='陕西', city='中国', county='新城区', ip='127.0.0.1'}");
	}

	@Test
	public void testDelete() {
		int result = userExtMapper.delete(1, 101);
		assertEquals(1, result);
	}

	@Test
	public void testQuery() {
		UserExt result = userExtMapper.query(1, 101);
		System.out.println(result.toString());
		assertEquals(result.toString(), "UserExt{uid=1, mid=101, avatar='http://www.pp.cc/static/common/home_img/newindex/logo-index.png', country='中国', province='安徽', city='合肥', county='政务区', ip='124.73.138.23'}");
	}

	@Test
	public void testQueryProvinceByUid() {
		long uid = 1L;
		List<Map<String, Object>> mapList = userExtMapper.queryProvinceByUid(uid);
		Map<String, Object> resultMap = mapList.get(0);
		String province = (String) resultMap.get("province");
		long count = (long) resultMap.get("count");
		assertEquals(province, "安徽");
		assertEquals(count, 1);
	}

	@Test
	public void testUpdate() {
		UserExt userExt = new UserExt().setAvatar("/user").setMid(101).setUid(1).setCity("中国")
				.setCountry("陕西").setIp("127.0.0.1").setProvince("山西").setCounty("运城区");
		userExtMapper.update(userExt);
		UserExt result = userExtMapper.query(1, 101);
		assertEquals(result.toString(), "UserExt{uid=1, mid=101, avatar='/user', country='陕西', province='山西', city='中国', county='运城区', ip='127.0.0.1'}");
	}

	@Test
	public void toJson() {
		UserExt userExt = new UserExt().setAvatar("/user").setMid(100).setUid(1).setCity("中国")
				.setCountry("陕西").setIp("127.0.0.1").setProvince("山西").setCounty("运城区");
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			System.out.println(objectMapper.writeValueAsString(userExt));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testQueryProvinceByUidAndPlatform(){
		List<Map<String, Object>> mapList = userExtMapper.queryProvinceByUidAndPlatform(1,1);
		Map<String, Object> resultMap = mapList.get(0);
		String province = (String) resultMap.get("province");
		long count = (long) resultMap.get("count");
		assertEquals(province, "安徽");
		assertEquals(count, 1);
	}

}
