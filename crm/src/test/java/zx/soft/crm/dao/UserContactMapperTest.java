package zx.soft.crm.dao;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import zx.soft.crm.dao.UserContactMapper;
import zx.soft.crm.model.UserContact;

import javax.inject.Inject;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: SnowMan
 * Date: 14-7-15
 * Time: PM1:52
 * 用户联系信息表Test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/webapp/WEB-INF/applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public class UserContactMapperTest {

	@Inject
	private UserContactMapper userContactMapper;

	@Test
	public void testAdd() {
		UserContact userContact = new UserContact().setContact_id(1).setMid(103).setUid(2).setValue("test");
		userContactMapper.add(userContact);
		UserContact result = userContactMapper.query(2, 103, 1);
		assertEquals(result.toString(), "UserContact{id=" + result.getId() + ", uid=2, mid=103, contact_id=1, " +
				"value='test'}");
	}

	@Test
	public void testUpdate() {
		UserContact userContact = new UserContact().setContact_id(2).setMid(101).setUid(1).setValue("changbo");
		userContactMapper.update(userContact);
		UserContact result = userContactMapper.query(1, 101, 2);
		assertEquals(result.toString(), "UserContact{id=1, uid=1, mid=101, contact_id=2, value='changbo'}");
	}

	@Test
	public void testQuery() {
		UserContact result = userContactMapper.query(1, 101, 2);
		assertEquals(result.toString(), "UserContact{id=1, uid=1, mid=101, contact_id=2, value='999999'}");
	}

	@Test
	public void toJson() {
		UserContact userContact = new UserContact().setContact_id(2).setMid(101).setUid(1).setValue("changbo");
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			System.out.println(objectMapper.writeValueAsString(userContact));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete(){
		int result = userContactMapper.delete(1,101);
		assertEquals(1,result);
	}

	@Test
	public void testQueryForList(){
		List<UserContact> result = userContactMapper.queryForList(1, 101);
		assertEquals(result.get(0).toString(),"UserContact{id=1, uid=1, mid=101, contact_id=2, value='999999'}");
	}

}
