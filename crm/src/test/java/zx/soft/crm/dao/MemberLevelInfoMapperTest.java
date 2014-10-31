package zx.soft.crm.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import zx.soft.crm.dao.MemberLevelInfoMapper;
import zx.soft.crm.model.MemberLevelInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/webapp/WEB-INF/applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public class MemberLevelInfoMapperTest {

	@Inject
	private MemberLevelInfoMapper mapper;

	@Test
	public void testAdd() {
		final long uid = 2;
		MemberLevelInfo info = new MemberLevelInfo(uid, "testvip", "testdescription", 1000, 9999, 80);
		mapper.add(info);
		// member_level_id为自增主键
		assertTrue(info.getMember_level_id() > 0);
		assertEquals(info.toString(), mapper.get(uid, info.getMember_level_id()).toString());
	}

	@Test
	public void testCRUD() {
		final long uid = 3;
		MemberLevelInfo info = new MemberLevelInfo(8, uid, "testvip", "testdescription", 1000, 9999, 80);
		mapper.update(info);
		List<MemberLevelInfo> infos = mapper.list(uid);
		assertEquals(1, infos.size());
		assertEquals(
				"MemberLevelInfo [member_level_id=8, uid=3, level_name=testvip, level_description=testdescription, exp_start=1000, exp_end=9999, discount=80, status=0]",
				infos.get(0).toString());
		// 测试修改
		info.setLevel_name("testvip2").setLevel_description("testdescription2").setExp_start(10000).setExp_end(99999)
				.setDiscount(75);
		mapper.update(info);
		assertEquals(
				"MemberLevelInfo [member_level_id=8, uid=3, level_name=testvip2, level_description=testdescription2, exp_start=10000, exp_end=99999, discount=75, status=0]",
				mapper.list(uid).get(0).toString());
		// 测试删除
		mapper.delete(uid, info.getMember_level_id());
		assertEquals(0, mapper.list(uid).size());
	}

	@Test
	public void testGet() {
		assertEquals(
				"MemberLevelInfo [member_level_id=1, uid=1, level_name=vip1, level_description=我是vip1的描述, exp_start=100, exp_end=999, discount=90, status=0]",
				mapper.get(1, 1).toString());
	}

}
