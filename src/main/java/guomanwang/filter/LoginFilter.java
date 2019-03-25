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

import guomanwang.domain.User;

public class LoginFilter implements Filter {

	/*
	 * filteuris为需要登录后才能访问的页面，accessuris为需要登录且有权限才能访问的页面
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	//过滤的URI
	String[] filteuris = new String[] { "/tiezi", "zan", "commit"}; 
	String[] accessuris = new String[] {"/control"} ;
	//被拒绝后去登录页面
	String loginstr = "/common/login";
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	public boolean doOrNotdoFilte(String[] str, String uri) {
		boolean boo = false;
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
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		//请求的uri
		String uri = req.getRequestURI();
		System.out.println( "Request URI " + uri);
		boolean dofilter = doOrNotdoFilte( filteuris, uri);
		boolean accessdofilter = doOrNotdoFilte( accessuris, uri);
		if( dofilter) {
			System.out.println("由于访问了要登录的内容,要进行过滤");
			Object obj = req.getSession().getAttribute("user");
			if( obj == null) {
				System.out.println( "没登录，过滤掉，不许访问");
				resp.sendRedirect( req.getContextPath() + loginstr);
				return ;
			}
		}
		if( accessdofilter) {
			System.out.println("正在访问需要一定权限的内容");
			Object obj = req.getSession().getAttribute("user");
			if( obj == null) {
				System.out.println( "没登录，过滤掉，不许访问");
				resp.sendRedirect( req.getContextPath() + loginstr);
				return ;
			}else {
				User user = (User)req.getSession().getAttribute("user");
				if( user.getStatus() < 2) {//权限未达到，
					System.out.println("权限未达到，去哪里呢报404错误，资源未找到，骗他们");
					resp.sendRedirect( req.getContextPath() + loginstr);
				}
			}
		}
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
