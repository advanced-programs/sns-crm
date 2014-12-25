package zx.soft.sns.dao.demo;

import java.util.Date;

import zx.soft.sns.dao.common.MybatisConfig;
import zx.soft.sns.dao.domain.wechat.WeChatAInfo;
import zx.soft.sns.dao.domain.wechat.WeChatAInsert;
import zx.soft.sns.dao.domain.wechat.WeChatPAInfo;
import zx.soft.sns.dao.domain.wechat.WeChatPAInsert;
import zx.soft.sns.dao.weixin.WeChatDaoImpl;
import zx.soft.utils.json.JsonUtils;

public class WeChatDaoImplDemo {

	public static void main(String[] args) {

		WeChatDaoImpl weChatDaoImpl = new WeChatDaoImpl(MybatisConfig.ServerEnum.sns);
		/**
		 * 插入微信公共帐号信息
		 */
		weChatDaoImpl
				.insertWeChatPA(new WeChatPAInsert.Builder("wechat_public_accounts", "RHZB126", "自媒体")
						.setDescription("自媒体——烦嚣中我们自己的小憩之地.")
						.setHeadUrl("http://img01.sogoucdn.com/app/a/100520090/oIWsFtzkvPAKoyJ-jOVgpp8V0k98")
						.setLastArticleUrl(
								"http://mp.weixin.qq.com/s?__biz=MjM5NDQxMzYyNA==&mid=201497524&idx=1&sn=d622ffa6cad4476fbd6e276b7176d17e&3rd=MzA3MDU4NTYzMw==&scene=6#rd")
						.setOpenId("oIWsFtzkvPAKoyJ-jOVgpp8V0k98")
						.setVerifyInfo("来自腾讯微博认证资料:姚银珠,深圳市润弘珠宝投资有限公司销售主管. @自媒体姚银珠").build());
		/**
		 * 根据微信公共帐号wid查询全部信息
		 */
		WeChatPAInfo weChatPAInfo = weChatDaoImpl.selectWeChatPA("wechat_public_accounts", "RHZB126");
		System.out.println(JsonUtils.toJson(weChatPAInfo));
		/**
		 * 判断微信公共帐号是否存在
		 */
		System.out.println(weChatDaoImpl.isWeChatPAExisted("wechat_public_accounts", "RHZB126"));
		System.out.println(weChatDaoImpl.isWeChatPAExisted("wechat_public_accounts", "RHZB1261"));
		/**
		 * 删除微信公共号
		 */
		weChatDaoImpl.deleteWeChatPA("wechat_public_accounts", "RHZB126");
		/**
		 * 插入微信文章信息
		 */
		weChatDaoImpl
				.insertWeChatA(new WeChatAInsert.Builder("wechat_articles", "123abcdef789", "阳朔悦榕庄，我自隐于山水间",
						"http://mp.weixin.qq.com/s?__biz=MjM5NDcyODYzNQ==&mid=201368614&idx=1&sn=422cce388b4e"
								+ "03c2f085aa842f20af89&3rd=MzA3MDU4NTYzMw==&scene=6#rd")
						.setContent(
								"推开客厅木格落地窗跨入私家小院,脚下踩着郁郁青草,头顶披着簇簇竹林,跨过青石墙望出去是灰白两色的徽派建筑屋瓦,"
										+ "和秀丽的群山&mdash;&mdash;草、树、建筑与山融合而生,万物和谐,唯待心沉静下来,和躯体一道体会<em><!--red_beg-->"
										+ "自<!--red_end--></em>然之灵&hellip;&hellip;这是阳朔悦榕庄,落户阳朔的第一家奢华度假酒店.她的美妙...")
						.setPicUrl(
								"http://img01.store.sogou.com/net/a/04/link?appid=100520031&url=http://mmbiz.qpic.cn/mmbiz/SHvOty90B0Q"
										+ "UE2kvuccUcLuOlmBibjPfn5CSSW9ia6iaQ1ibS9hVPicgicoJE2tYy2JV5tBtkXFq9olNficlJt7BHgXsA/0")
						.setTimestamp(new Date(1418362090L * 1000)).setWeChatName("DOWNIA")
						.setWeChatOpenId("oIWsFt_9Q1h33rR3qUfRWFIe0-Z8").build());
		/**
		 * 根据微信文章aid查询全部信息
		 */
		WeChatAInfo weChatAInfo = weChatDaoImpl.selectWeChatA("wechat_articles", "123abcdef789");
		System.out.println(JsonUtils.toJson(weChatAInfo));
		/**
		 * 判断微信文章是否存在
		 */
		System.out.println(weChatDaoImpl.isWeChatAExisted("wechat_articles", "123abcdef789"));
		System.out.println(weChatDaoImpl.isWeChatAExisted("wechat_articles", "123abcdef7891"));
		/**
		 * 删除微信文章
		 */
		weChatDaoImpl.deleteWeChatA("wechat_articles", "123abcdef789");
	}

}
