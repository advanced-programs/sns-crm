package zx.soft.sns.parser.constant;

/**
 * 微信公共号常量类
 * 
 * @author wgybzb
 *
 */
public class WeChatConstant {

	/*
	 * type=1：http://weixin.sogou.com/weixin?type=1&query=XXX
	 * type=2：http://weixin.sogou.com/weixin?type=2&query=XXX
	 */

	// 根地址
	public static final String ROOT_URL = "http://weixin.sogou.com/weixin?";

	// 公共号类型
	public static final String TYPE_PUBLIC_ACCOUNT = "1";

	// 文章类型
	public static final String TYPE_ARTICLE = "2";

	// 其它参数
	public static final String OTHER_PARAMS = "ie=utf8&_ast=1418312960&_asf=null&w=01029901&p=40040100&dp=1&cid=null";

}
