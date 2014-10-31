package zx.soft.crm.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import zx.soft.crm.dao.TagInfoMapper;
import zx.soft.crm.model.TagInfo;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/webapp/WEB-INF/applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public class TagInfoMapperTest {

	@Inject
	private TagInfoMapper tagInfoMapper;

	@Test
	public void testAdd() {
		final long uid = 2;
		TagInfo tagInfo = new TagInfo(uid, "白富美");
		tagInfoMapper.add(tagInfo);
		// tagId为自增主键
		assertTrue(tagInfo.getTag_id() > 0);
		assertEquals(tagInfo.toString(), tagInfoMapper.get(uid, tagInfo.getTag_id()).toString());
	}

	@Test
	public void testCRUD() {
		final long uid = 3;
		TagInfo tagInfo = new TagInfo(uid, 10, "白富美");
		tagInfoMapper.update(tagInfo);
		List<TagInfo> tagInfos = tagInfoMapper.list(uid);
		assertEquals(1, tagInfos.size());
		assertEquals("TagInfo [uid=3, tag_id=10, tag_name=白富美]", tagInfos.get(0).toString());
		// 测试修改
		tagInfo.setTag_name("富二代");
		tagInfoMapper.update(tagInfo);
		assertEquals("TagInfo [uid=3, tag_id=10, tag_name=富二代]", tagInfoMapper.list(uid).get(0).toString());
		// 测试删除
		tagInfoMapper.delete(uid, tagInfo.getTag_id());
		assertEquals(0, tagInfoMapper.list(uid).size());
	}

	@Test
	public void testGet() {
		assertEquals("TagInfo [uid=1, tag_id=1, tag_name=土豪]", tagInfoMapper.get(1, 1).toString());
	}

	@Test
	public void testQueryTagInfo() {
		List<TagInfo> tagInfo = tagInfoMapper.queryTagInfo(1, 101);
		assertEquals(tagInfo.get(0).toString(), "TagInfo [uid=1, tag_id=1, tag_name=土豪]");
	}

}
