package zx.soft.crm.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import zx.soft.crm.dao.UserTagMapper;
import zx.soft.crm.model.UserTag;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/webapp/WEB-INF/applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public class UserTagMapperTest {

	@Inject
	private UserTagMapper userTagMapper;

	@Test
	public void testAdd() {
		assertEquals(1, userTagMapper.add(new UserTag(1, 102, 2)));
		assertEquals(1, userTagMapper.add(new UserTag(1, 102, 2)));
		// 重复添加不会导致重复的数据
		List<UserTag> userTags = userTagMapper.getUserTags(1, 102);
		assertEquals("[UserTag [uid=1, mid=102, tag_id=2]]", userTags.toString());
	}

	@Test
	public void testDelete() {
		int result = userTagMapper.deleteUserTag(1, 101);
		assertEquals(2, result);
	}

	@Test
	public void testGet() {
		List<UserTag> userTags = userTagMapper.getUserTags(1, 101);
		assertEquals("[UserTag [uid=1, mid=101, tag_id=1], UserTag [uid=1, mid=101, tag_id=3]]", userTags.toString());
	}

}
