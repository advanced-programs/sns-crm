package zx.soft.sns.dao.demo;

import java.util.List;

import zx.soft.sns.dao.common.MybatisConfig;
import zx.soft.sns.dao.domain.WeixinRecordInfo;
import zx.soft.sns.dao.domain.WeixinRecordInsert;
import zx.soft.sns.dao.weixin.WeixinInfo;

public class WeixinInfoDemo {

	public static void main(String[] args) {

		WeixinInfo weixinInfo = new WeixinInfo(MybatisConfig.ServerEnum.sns);

		WeixinRecordInsert weixinRecordInsert = new WeixinRecordInsert.Builder("weixin_info_0", "wgybzb", "测试名")
				.setHeadUrl("http://www.baidu.com").setOpenId("123jjfdhffuifip").setDescription("测试号")
				.setVerifyInfo("认证信息").build();
		weixinInfo.insertWeixinRecord(weixinRecordInsert);

		List<WeixinRecordInfo> result = weixinInfo.selectWeixinRecordsByWid("weixin_info_0", "wgybzb");
		System.out.println(result.get(0).toString());

	}
}
