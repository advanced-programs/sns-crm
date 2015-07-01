package zx.soft.sns.api.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 自定义的ObjectMapper类，用于处理时间格式
 * 
 * @author wanggang
 *
 */
public class CustomObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = -3341366863221844412L;

	public static DateFormat sinaDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);

	public CustomObjectMapper() {
		super();
		setDateFormat(sinaDateFormat);
	}

	/**
	 * 测试函数
	 */
	public static void main(String[] args) throws ParseException {
		System.out.println(sinaDateFormat.format(new Date()));
	}

}
