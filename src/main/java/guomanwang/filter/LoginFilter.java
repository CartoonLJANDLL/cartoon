package guomanwang.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.ArrayUtils;

import guomanwang.domain.User;
import guomanwang.util.JDBCUtil;

public class LoginFilter implements Filter {

	/*
	 * right1为需要登录后才能访问的页面，right2为需要登录且有权限才能访问的页面
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	JDBCUtil jdbcUtil = new JDBCUtil();
	//被拒绝后去登录页面
	String loginstr = "/login";
	String rightError = "/rightError";
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}
	public static <T> T[] concat(T[] first, T[] second){
		
		T[] both = (T[]) ArrayUtils.addAll(first, second);
        return both;
	}

	public boolean doOrNotdoFilte(String[] str, String uri) {
		boolean boo = false;
		if( str == null)
			return boo;
		for( String s : str) {
			if( uri.indexOf( s) > -1) {
				boo = true;
				return boo;
			}
		}
		return boo;
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//过滤的URI
		String[] right1 = null;
		try {
			right1 = jdbcUtil.splitStringBy( jdbcUtil.selectAccessById(1));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		String[] right2 = null;
		try {
			right2 = jdbcUtil.splitStringBy( jdbcUtil.selectAccessById(2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] right3 = null;
		try {
			right3 = jdbcUtil.splitStringBy( jdbcUtil.selectAccessById(3));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] right4 = null;
		try {
			right4 = jdbcUtil.splitStringBy( jdbcUtil.selectAccessById(4));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		right1 = concat(right1, right2);
		right1 = concat(right1, right3);
		right1 = concat(right1, right4);
		right2 = concat(right2, right3);
		right2 = concat(right2, right4);
		right3 = concat(right3, right4);
		
		System.out.println("right1" + right1.length + "right2" + right2.toString() + "right3" + right3.length + "right4" + right4);
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		//请求的uri
		String uri = req.getRequestURI();
		System.out.println( "Request URI " + uri);
		boolean dofilter = doOrNotdoFilte( right1, uri);
		boolean accessdofilter = doOrNotdoFilte( right2, uri);
		boolean filtRight3 = doOrNotdoFilte( right3, uri);
		boolean filtRight4 = doOrNotdoFilte( right4, uri);
		if( filtRight4) {
			System.out.println("正在访问需要4级权限的内容");
			Object obj = req.getSession().getAttribute("user");
			if( obj == null) {
				System.out.println( "没登录，过滤掉，不许访问");
				resp.sendRedirect( req.getContextPath() + loginstr);
				return ;
			}else {
				User user = (User)req.getSession().getAttribute("user");
				if( user.getStatus() != 4) {//权限未达到，
					System.out.println("权限未达到，去哪里呢报404错误，资源未找到，骗他们");
					resp.sendRedirect( req.getContextPath() + rightError);
					return ;
				}
			}
		}
		if( filtRight3) {
			System.out.println("正在访问需要san级权限的内容");
			Object obj = req.getSession().getAttribute("user");
			if( obj == null) {
				System.out.println( "没登录，过滤掉，不许访问");
				resp.sendRedirect( req.getContextPath() + loginstr);
				return ;
			}else {
				User user = (User)req.getSession().getAttribute("user");
				if( user.getStatus() != 3) {//权限未达到，
					System.out.println("权限未达到，去哪里呢报404错误，资源未找到，骗他们");
					resp.sendRedirect( req.getContextPath() + rightError);
					return ;
				}
			}
		}
		if( accessdofilter) {
			System.out.println("正在访问需要二级权限的内容");
			Object obj = req.getSession().getAttribute("user");
			if( obj == null) {
				System.out.println( "没登录，过滤掉，不许访问");
				resp.sendRedirect( req.getContextPath() + loginstr);
				return ;
			}else {
				User user = (User)req.getSession().getAttribute("user");
				if( user.getStatus() != 2) {//权限未达到，
					System.out.println("权限未达到，去哪里呢报404错误，资源未找到，骗他们");
					resp.sendRedirect( req.getContextPath() + rightError);
					return ;
				}
			}
		}
		if( dofilter) {
			System.out.println("由于访问了要登录的内容,要进行过滤");
			Object obj = req.getSession().getAttribute("user");
			if( obj == null) {
				System.out.println( "没登录，过滤掉，不许访问");
				resp.sendRedirect( req.getContextPath() + loginstr);
				return ;
			}
		}
		
		
		
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
