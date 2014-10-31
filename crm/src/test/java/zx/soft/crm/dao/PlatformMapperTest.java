package zx.soft.crm.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import zx.soft.crm.dao.PlatformMapper;
import zx.soft.crm.model.Platform;
import zx.soft.crm.model.UserIncrement;

import javax.inject.Inject;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/webapp/WEB-INF/applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public class PlatformMapperTest {

	@Inject
	private PlatformMapper platformMapper;

	@Test
	public void queryByUidAndlatform() {
		List<Map<String, Object>> mapList = platformMapper.queryByUidAndlatform(1, 1);
		Map<String, Object> resultMap = mapList.get(0);
		int platform = (int) resultMap.get("platform");
		long count = (long) resultMap.get("count");
		assertEquals(platform, 1);
		assertEquals(count, 1);
	}

	@Test
	public void testAdd() {
		Platform platform = new Platform().setPlatform(1).setPlatform_user_id("test").setMid(100).setUid(1);
		platformMapper.add(platform);
		Platform result = platformMapper.list(1, 100).get(0);
		System.out.println(result);
		assertEquals(result.toString(), "Platform [uid=1, mid=100, platform=1, platform_user_id=test]");
	}

	@Test
	public void testDelete() {
		int result = platformMapper.delete(1, 101);
		assertEquals(2, result);
	}

	@Test
	public void testList() {
		List<Platform> list = platformMapper.list(1, 101);
		assertEquals(2, list.size());
		assertEquals("Platform [uid=1, mid=101, platform=1, platform_user_id=2222222222]", list.get(0).toString());
	}

	@Test
	public void testQuery() {
		Platform platform = new Platform().setPlatform(1).setMid(101).setUid(1);
		Platform result = platformMapper.query(platform.getUid(), platform.getMid(), platform.getPlatform());
		assertEquals(result.toString(), "Platform [uid=1, mid=101, platform=1, platform_user_id=2222222222]");
	}

	@Test
	public void testQueryByUid() {
		long uid = 1L;
		List<Map<String, Object>> mapList = platformMapper.queryByUid(uid);
		Map<String, Object> resultMap = mapList.get(0);
		int platform = (int) resultMap.get("platform");
		long count = (long) resultMap.get("count");
		assertEquals(platform, 1);
		assertEquals(count, 1);
	}

	@Test
	public void testQueryByUidAndDate() {
		Date start = new Date(1395367839000L);
		Date end = new Date(1403316639000L);
		long uid = 1L;
		List<UserIncrement> userIncrements = platformMapper.queryByUidAndDate(uid, start, end);
		assertEquals(userIncrements.get(0).toString(), "UserIncrement{count=1, platform=1, day='2014-03-21'}");
	}

	@Test
	public void testQueryByUidAndUserId() {
		Platform platform = new Platform().setUid(1).setPlatform_user_id("qwerasdf");
		List<Platform> result = platformMapper.queryByUidAndUserId(platform.getUid(), platform.getPlatform_user_id());
		assertEquals(result.get(0).toString(), "Platform [uid=1, mid=101, platform=2, platform_user_id=qwerasdf]");
	}


//	@Test
//	public void toJson() {
//		Platform platform = new Platform().setPlatform(1).setPlatform_user_id("changbo").setMid(101).setUid(1);
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//			System.out.println(objectMapper.writeValueAsString(platform));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	@Test
	public void testUpdate() {
		Platform platform = new Platform().setPlatform(1).setPlatform_user_id("changbo").setMid(101).setUid(1);
		platformMapper.update(platform);
		Platform result = platformMapper.list(1, 101).get(0);
		assertEquals(result.toString(), "Platform [uid=1, mid=101, platform=1, platform_user_id=changbo]");
	}

}
