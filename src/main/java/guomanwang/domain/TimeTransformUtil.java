package guomanwang.domain;

import java.util.Date;
import java.text.SimpleDateFormat;
//日期转换成指定格式的工具类
public class TimeTransformUtil {
	public static String timetransform(Date date) {
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
		
	}
}
