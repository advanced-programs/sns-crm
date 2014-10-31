package zx.soft.crm.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import zx.soft.crm.dao.MemberPointRecordMapper;
import zx.soft.crm.model.MemberPointRecord;
import zx.soft.crm.model.RecordQueryCondition;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/webapp/WEB-INF/applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public class MemberPointRecordMapperTest {

	static final String memberPointRecord_1 = "MemberPointRecord [uid=1, mid=101, reason=刮刮卡, point_change=10, create_time=Sun Mar 23 10:52:27 CST 2014]";

	static final String memberPointRecord_2 = "MemberPointRecord [uid=1, mid=101, reason=砸金蛋, point_change=5, create_time=Mon Mar 24 11:53:15 CST 2014]";

	@Inject
	private MemberPointRecordMapper mapper;

	private final long uid = 1;

	@Test
	public void testGetList() {
		RecordQueryCondition condition = new RecordQueryCondition().setUid(uid).setMid(101);
		assertEquals(2, mapper.countList(condition));
		List<MemberPointRecord> records = mapper.list(condition);
		assertEquals(2, records.size());
		assertEquals(memberPointRecord_1, records.get(0).toString());
	}

	@Test
	public void testGetList_page() {
		RecordQueryCondition condition = new RecordQueryCondition().setUid(uid).setMid(101).setPage(2)
				.setPer_page(1);
		assertEquals(2, mapper.countList(condition));
		List<MemberPointRecord> records = mapper.list(condition);
		assertEquals(1, records.size());
		assertEquals(memberPointRecord_2, records.get(0).toString());
	}

	@Test
	public void testGetList_time() {
		Date timepoint = new Date(1395590400000L); // 2014-03-24 00:00:00
		RecordQueryCondition condition = new RecordQueryCondition().setUid(uid).setMid(101)
				.setStart_time(timepoint);
		assertEquals(1, mapper.countList(condition));
		List<MemberPointRecord> records = mapper.list(condition);
		assertEquals(memberPointRecord_2, records.get(0).toString());

		condition.setStart_time(null).setEnd_time(timepoint);
		assertEquals(1, mapper.countList(condition));
		records = mapper.list(condition);
		assertEquals(memberPointRecord_1, records.get(0).toString());
	}

	@Test
	public void testDelete(){
		int result = mapper.delete(1, 101);
		assertEquals(2, result);
	}
}
