package guomanwang.util;

import javax.servlet.http.HttpServletRequest;

public class IPUtil {

	/*
	 * 获取IP地址
	 * @param request
	 * @return string IP
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if( ip != null && !"".equals( ip) && !"unKnown".equalsIgnoreCase( ip)) {
			//多次反向代理后会有多个IP值，第一个才是真实ip
			int index = ip.indexOf(",");
			if( index != -1) {
				return ip.substring( 0, index);
			}else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if( ip != null && !"".equals( ip) && !"unKnown".equalsIgnoreCase( ip)) {
			return ip;
		}
		return request.getRemoteAddr();

	}
}
