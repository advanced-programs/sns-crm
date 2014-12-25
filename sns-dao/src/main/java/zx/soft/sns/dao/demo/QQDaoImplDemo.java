package zx.soft.sns.dao.demo;

import java.util.List;

import zx.soft.sns.dao.common.MybatisConfig;
import zx.soft.sns.dao.domain.qq.QQAccountInfo;
import zx.soft.sns.dao.domain.qq.QQAccountInsert;
import zx.soft.sns.dao.qq.QQDaoImpl;
import zx.soft.utils.json.JsonUtils;

public class QQDaoImplDemo {

	public static void main(String[] args) {

		QQDaoImpl qqDaoImpl = new QQDaoImpl(MybatisConfig.ServerEnum.sns);
		/**
		 * 插入QQ帐号信息
		 */
		qqDaoImpl.insertQQAccount(new QQAccountInsert.Builder("qq_accounts", 10001L, "pony").setAge(45).setGender("男")
				.setQqGroup(123456789L).build());
		qqDaoImpl.insertQQAccount(new QQAccountInsert.Builder("qq_accounts", 10001L, "pony").setAge(45).setGender("男")
				.setQqGroup(987654321L).build());
		/**
		 * 根据QQ帐号qq查询全部信息（包括群信息）
		 */
		List<QQAccountInfo> accounts = qqDaoImpl.selectQQAccounts("qq_accounts", 10001L);
		System.out.println(JsonUtils.toJson(accounts));
		/**
		 * 根据QQ帐号qq查询名称
		 */
		System.out.println(qqDaoImpl.isExistedQQAccount("qq_accounts", 10001L, 123456789L));
		System.out.println(qqDaoImpl.isExistedQQAccount("qq_accounts", 10001L, 987654321L));
		System.out.println(qqDaoImpl.isExistedQQAccount("qq_accounts", 10002L, 987654321L));
		System.out.println(qqDaoImpl.isExistedQQAccount("qq_accounts", 10001L, 98765431L));
		/**
		 * 删除QQ号信息
		 */
		qqDaoImpl.deleteQQAccount("qq_accounts", 10001L, 123456789L);
		qqDaoImpl.deleteQQAccount("qq_accounts", 10001L, 987654321L);
	}

}
