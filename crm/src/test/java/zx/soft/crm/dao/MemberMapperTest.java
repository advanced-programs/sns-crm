package zx.soft.crm.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import zx.soft.crm.dao.MemberMapper;
import zx.soft.crm.model.Member;
import zx.soft.crm.model.MemberExpRecord;
import zx.soft.crm.model.MemberPointRecord;

import javax.inject.Inject;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/webapp/WEB-INF/applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public class MemberMapperTest {

	final long uid = 1;
	@Inject
	private MemberMapper mapper;

	@Test
	public void testAdd() {
		mapper.add(new Member().setUid(1).setMid(999).defaultValue());
		assertEquals(
				"Member [uid=1, mid=999, address=, birthday=null, member_point=0, experience=0, id_card_no=, member_card_no=, member_level_id=null]",
				mapper.get(1, 999).toString());

		Date date = new Date(1395590400000L); // 2014-03-24 00:00:00
		Member member = new Member().setUid(1).setMid(888).setAddress("地址").setBirthday(date).setMember_point(111)
				.setExperience(222).setId_card_no("123456789012345678").setMember_card_no("222222")
				.setMember_level_id(2);
		mapper.add(member);
		assertEquals(
				"Member [uid=1, mid=888, address=地址, birthday=Mon Mar 24 00:00:00 CST 2014, member_point=111, experience=222, id_card_no=123456789012345678, member_card_no=222222, member_level_id=2]",
				mapper.get(1, 888).toString());
	}

	@Test
	public void testAddExp() {
		mapper.addExp(new MemberExpRecord().setUid(1).setMid(101).setExp_change(10));
		assertEquals(1009, mapper.get(uid, 101).getExperience().intValue());
	}

	@Test
	public void testAddPoint() {
		mapper.addPoint(new MemberPointRecord().setUid(1).setMid(101).setPoint_change(20));
		assertEquals(120, mapper.get(uid, 101).getMember_point().intValue());
	}

	@Test
	public void testGet() {
		Member member = mapper.get(uid, 101);
		assertEquals(
				"Member [uid=1, mid=101, address=安徽,合肥,政务区,平安大厦8楼, birthday=Sun Mar 21 00:00:00 CST 2010, member_point=100, experience=999, id_card_no=111111111111111111, member_card_no=88888888, member_level_id=1]",
				member.toString());
	}

	@Test
	public void testQueryCountByUid() {
		int count = mapper.queryCountByUid(1);
		assertEquals(count, 2);
	}

	@Test
	public void testUpdate() {
		Member member = mapper.get(uid, 101);
		member.setAddress("测试地址").setBirthday(new Date(1395590400000L))
				// 2014-03-24 00:00:00
				.setMember_point(10).setExperience(100).setId_card_no("222222222222222222")
				.setMember_card_no("66666666").setMember_level_id(2);
		mapper.update(member);
		Member updated = mapper.get(uid, 101);
		assertEquals(
				"Member [uid=1, mid=101, address=测试地址, birthday=Mon Mar 24 00:00:00 CST 2014, member_point=10, experience=100, id_card_no=222222222222222222, member_card_no=66666666, member_level_id=2]",
				updated.toString());
	}

}
