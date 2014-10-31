package zx.soft.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

public class CustomObjectMapper extends ObjectMapper {

	public static DateFormat sinaDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);

	public static void main(String[] args) throws ParseException {
		System.out.println(sinaDateFormat.format(new Date()));
		Date parse = sinaDateFormat.parse("Wed Oct 23 16:58:17 +0800 2013");
		System.out.println(parse);
	}

	public CustomObjectMapper() {
		super();
		setDateFormat(sinaDateFormat);
		configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
	}

}
