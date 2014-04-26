package zx.soft.sns.dao.common;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigUtil {

	private static Logger logger = LoggerFactory.getLogger(ConfigUtil.class);

	public static Properties getProps(String confFileName) {
		Properties result = new Properties();
		logger.info("Load resource: " + confFileName);
		try (InputStream in = ConfigUtil.class.getClassLoader().getResourceAsStream(confFileName);) {
			result.load(in);
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
