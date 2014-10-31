package zx.soft.crm.dao;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import zx.soft.crm.dao.UserLastActiveMappper;
import zx.soft.crm.model.UserLastActive;

import javax.inject.Inject;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: SnowMan
 * Date: 14-7-15
 * Time: PM2:40
 * 用户最后交互信息表Test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/webapp/WEB-INF/applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public class UserLastActiveMappperTest {

	@Inject
	private UserLastActiveMappper userLastActiveMappper;

	@Test
	public void test() {
		Date date = new Date();
		System.out.println(date.getTime() / 1000);
	}

	@Test
	public void testAdd() {
		Date date = new Date(1405439999000L);
		UserLastActive userLastActive = new UserLastActive().setUid(1).setMid(102).setLast_active_time(date)
				.setActive_count(1);
		userLastActiveMappper.add(userLastActive);
		UserLastActive result = userLastActiveMappper.query(1, 102);
		assertEquals(result.toString(), "UserLastActive{uid=1, mid=102, last_active_time=Tue Jul 15 23:59:59 CST 2014, active_count=1}");
	}

	@Test
	public void testDelete() {
		int result = userLastActiveMappper.delete(1, 101);
		assertEquals(result, 1);
	}

	@Test
	public void testQuery() {
		UserLastActive result = userLastActiveMappper.query(1, 101);
		assertEquals(result.toString(), "UserLastActive{uid=1, mid=101, last_active_time=Fri Mar 21 10:10:39 CST 2014, active_count=2}");
	}

	@Test
	public void testUpdate() {
		Date date = new Date(1405439999000L);
		System.out.println(date);
		UserLastActive userLastActive = new UserLastActive().setUid(1).setMid(101).setLast_active_time(date);
		userLastActiveMappper.update(userLastActive);
		UserLastActive result = userLastActiveMappper.query(1, 101);
		assertEquals(result.toString(), "UserLastActive{uid=1, mid=101, last_active_time=Tue Jul 15 23:59:59 CST 2014, active_count=3}");
	}

	@Test
	public void toJson() {
		Date date = new Date(1405439999000L);
		UserLastActive userLastActive = new UserLastActive().setUid(1).setMid(102).setLast_active_time(date)
				.setActive_count(1);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			System.out.println(objectMapper.writeValueAsString(userLastActive));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
