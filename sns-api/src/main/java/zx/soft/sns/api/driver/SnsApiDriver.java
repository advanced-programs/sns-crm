package zx.soft.sns.api.driver;

import zx.soft.sns.api.server.SnsApiServer;
import zx.soft.utils.driver.ProgramDriver;

/**
 * 驱动类
 *
 * @author wanggang
 *
 */
public class SnsApiDriver {

	/**
	 * 主函数
	 */
	public static void main(String[] args) {

		int exitCode = -1;
		ProgramDriver pgd = new ProgramDriver();
		try {
			// 运行在hefei07机器上
			pgd.addClass("snsApiServer", SnsApiServer.class, "微信公共帐号相关CURD接口服务");
			pgd.driver(args);
			// Success
			exitCode = 0;
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}

		System.exit(exitCode);

	}

}
