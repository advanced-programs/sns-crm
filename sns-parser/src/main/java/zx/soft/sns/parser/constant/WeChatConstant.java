package zx.soft.sns.parser.constant;

/**
 * 微信公共号常量类
 * 
 * @author wgybzb
 *
 */
public class WeChatConstant {

	/**
	 * type=1：http://weixin.sogou.com/weixin?type=1&query=XXX   微信公共号
	 * type=2：http://weixin.sogou.com/weixin?type=2&query=XXX   微信文章
	 */

	// 根地址
	public static final String ROOT_URL = "http://weixin.sogou.com/";

	// 微信页面
	public static final String PAGE_WEIXIN = "weixin?";

	// 公共号类型
	public static final String TYPE_PUBLIC_ACCOUNT = "1";

	// 文章类型
	public static final String TYPE_ARTICLE = "2";

	// 页码
	public static final String PAGE = "page";

	// 其它参数
	public static final String OTHER_PARAMS = "ie=utf8&_ast=1418312960&_asf=null&w=01029901&p=40040100&dp=1&cid=null";

	/**
	 * 公共号详细信息页面地址：包括帐号信息和文章列表
	 * http://weixin.sogou.com/gzh?openid=oIWsFtzkvPAKoyJ-jOVgpp8V0k98
	 */

	public static final String PAGE_PUBLIC_ACCOUNT = "gzh?";

	public static final String OPEN_ID = "openid";

	/**
	 * 公共号文章信息JS接口地址：所有文章列表
	 * http://weixin.sogou.com/gzhjs?cb=sogou.weixin.gzhcb&openid=oIWsFtzkvPAKoyJ-jOVgpp8V0k98&page=9
	 */

	public static final String PAGE_PUBLIC_ACCOUNT_JSAPI = "gzhjs?";

	/**
	 * 图片地址
	 * 小图地址：http://img01.sogoucdn.com/app/a/100520090/oIWsFtzkvPAKoyJ-jOVgpp8V0k98
	 * 大图地址：http://wx.qlogo.cn/mmhead/Q3auHgzwzM6beian4BRu40jup1znM2Je1PhOUOUNrGPTomW2ichUommg/0/0
	 */

}
