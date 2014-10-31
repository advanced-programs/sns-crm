package zx.soft.crm.service;

import org.springframework.stereotype.Service;

import zx.soft.crm.dao.*;
import zx.soft.crm.model.*;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

	@Inject
	private UserMapper userMapper;
	@Inject
	private TagInfoMapper tagInfoMapper;
	@Inject
	private UserTagMapper userTagMapper;
	@Inject
	private MemberLevelInfoMapper memberLevelInfoMapper;
	@Inject
	private MemberPointRecordMapper memberPointRecordMapper;
	@Inject
	private MemberMapper memberMapper;
	@Inject
	private UserContactMapper userContactMapper;
	@Inject
	private MemberExpRecordMapper memberExpRecordMapper;
	@Inject
	private AddressMapper addressMapper;
	@Inject
	private PlatformMapper platformMapper;
	@Inject
	private UserExtMapper userExtMapper;
	@Inject
	private UserLastActiveMappper userLastActiveMappper;

	public Address add(Address address) {
		if (address.getAddress_id() == 0) {
			addressMapper.add(address);
		} else {
			addressMapper.addNoIncrement(address);
		}
		return addressMapper.get(address.getAddress_id());
	}

	public void add(MemberExpRecord record) {
		checkMid(record.getUid(), record.getMid());
		memberExpRecordMapper.add(record);
		memberMapper.addExp(record);
	}

	public MemberLevelInfo add(MemberLevelInfo memberLevelInfo) {
		if (memberLevelInfo.getMember_level_id() == 0) {
			memberLevelInfoMapper.add(memberLevelInfo);
		} else {
			memberLevelInfoMapper.update(memberLevelInfo);
		}
		return memberLevelInfoMapper.get(memberLevelInfo.getUid(), memberLevelInfo.getMember_level_id());
	}

	public void add(MemberPointRecord record) {
		checkMid(record.getUid(), record.getMid());
		memberPointRecordMapper.add(record);
		memberMapper.addPoint(record);
	}

	public TagInfo add(TagInfo tagInfo) {
		if (tagInfo.getTag_id() == 0) {
			tagInfoMapper.add(tagInfo);
		} else {
			tagInfoMapper.update(tagInfo);
		}
		return tagInfoMapper.get(tagInfo.getUid(), tagInfo.getTag_id());
	}

	public User add(User user) {
		user.defaultValue();
		userMapper.add(user);
		return getUser(user.getUid(), user.getMid());
	}

	public UserTag add(UserTag userTag) {
		checkMid(userTag.getUid(), userTag.getMid());
		checkTagId(userTag.getUid(), userTag.getTag_id());
		userTagMapper.add(userTag);
		return userTag;
	}

	public UserLastActive addOrUpdate(UserLastActive userLastActive) {
		if (userLastActiveMappper.query(userLastActive.getUid(), userLastActive.getMid()) != null) {
			userLastActiveMappper.update(userLastActive);
		} else {
			userLastActiveMappper.add(userLastActive);
		}
		return userLastActiveMappper.query(userLastActive.getUid(), userLastActive.getMid());
	}

	public Platform addOrUpdate(Platform platform) {
		if (platformMapper.query(platform.getUid(), platform.getMid(), platform.getPlatform()) != null) {
			platformMapper.update(platform);
		} else {
			platformMapper.add(platform);
		}
		return platformMapper.query(platform.getUid(), platform.getMid(), platform.getPlatform());
	}

	public UserContact addOrUpdate(UserContact userContact) {
		if (userContactMapper.query(userContact.getUid(), userContact.getMid(), userContact.getContact_id()) != null) {
			userContactMapper.update(userContact);
		} else {
			userContactMapper.add(userContact);
		}
		return userContactMapper.query(userContact.getUid(), userContact.getMid(), userContact.getContact_id());
	}

	public UserExt addOrUpdate(UserExt userExt) {
		if (userExtMapper.query(userExt.getUid(), userExt.getMid()) != null) {
			userExtMapper.update(userExt);
		} else {
			userExtMapper.add(userExt);
		}
		return userExtMapper.query(userExt.getUid(), userExt.getMid());
	}

	public Member addOrUpdate(Member member) {
		checkMid(member.getUid(), member.getMid());
		checkMemberLevelId(member.getUid(), member.getMember_level_id());
		Member finded = memberMapper.get(member.getUid(), member.getMid());
		if (finded == null) {
			member.defaultValue();
			memberMapper.add(member);
			userMapper.joinMember(member.getUid(), member.getMid());
		} else {
			memberMapper.update(member);
		}
		return memberMapper.get(member.getUid(), member.getMid());
	}

	private void checkMemberLevelId(long uid, Integer memberLevelId) {
		if (memberLevelId == null) {
			return;
		}
		if (memberLevelInfoMapper.get(uid, memberLevelId) == null) {
			throw new IllegalArgumentException("member_level_id is not exist. uid=" + uid + ", member_level_id="
					+ memberLevelId);
		}
	}

	private void checkMid(long uid, long mid) {
		if (userMapper.get(uid, mid) == null) {
			throw new IllegalArgumentException("user is not exist. uid=" + uid + ", mid=" + mid);
		}
	}

	private void checkTagId(long uid, int tagId) {
		if (tagInfoMapper.get(uid, tagId) == null) {
			throw new IllegalArgumentException("tagInfo is not exist. uid=" + uid + ", tag_id=" + tagId);
		}
	}

	public int countList(UserQueryCondition condition) {
		return userMapper.countList(condition);
	}

	public void delete(UserTag userTag) {
		checkMid(userTag.getUid(), userTag.getMid());
		checkTagId(userTag.getUid(), userTag.getTag_id());
		userTagMapper.delete(userTag);
	}

	public void deleteAddress(long uid, long mid, int address_id) {
		addressMapper.delete(mid, address_id);
	}

	public void deleteMember(long uid, long mid) {
		checkMid(uid, mid);
		userMapper.exitMember(uid, mid);
		memberMapper.delete(uid, mid);
	}

	public void deleteMemberLevelInfo(long uid, int member_level_id) {
		memberLevelInfoMapper.delete(uid, member_level_id);
	}

	public void deleteTagInfo(long uid, int tag_id) {
		tagInfoMapper.delete(uid, tag_id);
	}

	private void fillUserExtInfo(User user) {
		if (user == null) {
			return;
		}
		if (user.getIs_member() == 1) {
			user.setMember(memberMapper.get(user.getUid(), user.getMid()));
		}
		user.setPlatforms(platformMapper.list(user.getUid(), user.getMid()));
		user.setTags(userTagMapper.getUserTags(user.getUid(), user.getMid()));
		user.setUserExts(Arrays.asList(userExtMapper.query(user.getUid(), user.getMid())));
		user.setUserContacts(userContactMapper.queryForList(user.getUid(),user.getMid()));
		user.setUserLastActives(Arrays.asList(userLastActiveMappper.query(user.getUid(),user.getMid())));
	}

	public Member getMember(long uid, long mid) {
		return memberMapper.get(uid, mid);
	}

	public List<MemberLevelInfo> getMemberLevelInfos(long uid) {
		return memberLevelInfoMapper.list(uid);
	}

	public List<TagInfo> getTagInfos(long uid) {
		return tagInfoMapper.list(uid);
	}

	public User getUser(long uid, long mid) {
		User user = userMapper.get(uid, mid);
		if (user == null) {
			return null;
		}
		fillUserExtInfo(user);
		return user;
	}

	public List<User> list(UserQueryCondition condition) {
		List<User> result = userMapper.list(condition);
		for (User user : result) {
			fillUserExtInfo(user);
		}
		return result;
	}

	public List<Address> listAddress(long mid) {
		return addressMapper.list(mid);
	}

	public List<MemberExpAndPointRecord> listExpAndPointRecord(RecordQueryCondition condition) {
		return memberExpRecordMapper.listExpAndPoint(condition);
	}

	public int listExpAndPointRecordCount(RecordQueryCondition condition) {
		return memberExpRecordMapper.countList(condition) + memberPointRecordMapper.countList(condition);
	}

	public List<? extends Object> listExpRecord(RecordQueryCondition condition) {
		return memberExpRecordMapper.list(condition);
	}

	public int listExpRecordCount(RecordQueryCondition condition) {
		return memberExpRecordMapper.countList(condition);
	}

	public List<MemberPointRecord> listPointRecord(RecordQueryCondition condition) {
		return memberPointRecordMapper.list(condition);
	}

	public int listPointRecordCount(RecordQueryCondition condition) {
		return memberPointRecordMapper.countList(condition);
	}

	public List<Map<String, Object>> queryByUid(long uid, int platform) {
		if(platform == 0){
			return platformMapper.queryByUid(uid);
		}else {
			return platformMapper.queryByUidAndlatform(uid, platform);
		}
	}

	public List<UserIncrement> queryByUidAndDate(long uid, Date start, Date end) {
		return platformMapper.queryByUidAndDate(uid, start, end);
	}

	public List<Map<String, Object>> queryGenderByUid(long uid, int platform) {
		if(platform == 0){
			return userMapper.queryGenderByUid(uid);
		}else {
			return userMapper.queryGenderByUidAndPlatform(uid, platform);
		}
	}

	public List<Map<String, Object>> queryProvinceByUid(long uid, int platform) {
		if(platform == 0){
			return userExtMapper.queryProvinceByUid(uid);
		}else {
			return userExtMapper.queryProvinceByUidAndPlatform(uid, platform);
		}
	}

	public List<Map<String, Object>> queryStatusByUid(long uid, int platform) {
		if(platform == 0){
			return userMapper.queryStatusByUid(uid);
		}else {
			return userMapper.queryStatusByUidAndPlatform(uid, platform);
		}
	}

	public Address update(Address address) {
		addressMapper.update(address);
		return addressMapper.get(address.getAddress_id());
	}

	public Member update(Member member) {
		checkMemberLevelId(member.getUid(), member.getMember_level_id());
		memberMapper.update(member);
		return memberMapper.get(member.getUid(), member.getMid());
	}

	public User update(User user) {
		userMapper.update(user);
		return getUser(user.getUid(), user.getMid());
	}

	public List<Platform> queryByUidAndUserId(long uid, String platform_user_id){
		return platformMapper.queryByUidAndUserId(uid,platform_user_id);
	}

	public void deleteAllByUidAndMid(long uid, long mid){
		userContactMapper.delete(uid,mid);
		userExtMapper.delete(uid, mid);
		userLastActiveMappper.delete(uid, mid);
		userTagMapper.deleteUserTag(uid, mid);
		platformMapper.delete(uid, mid);
		userMapper.delete(uid, mid);
		memberExpRecordMapper.delete(uid, mid);
		memberPointRecordMapper.delete(uid, mid);
		addressMapper.deleteAll(mid);
		memberMapper.deleteMember(uid, mid);
	}

	public int queryUserCountByUid(long uid) {
		return userMapper.queryCountByUid(uid);
	}

	public int queryMemberCountByUid(long uid) {
		return memberMapper.queryCountByUid(uid);
	}

	public List<TagInfo> queryTagInfo(long uid, long mid){
		return tagInfoMapper.queryTagInfo(uid, mid);
	}

}
