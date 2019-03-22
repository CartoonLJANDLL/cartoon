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

public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

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
		//过滤的URI
		String[] filteuris = new String[] { "/tiezi", "zan", "commit"}; 
		String loginstr = "/common/login";
		System.out.println( "Request URI " + uri);
		boolean dofilter = false;
		//判断是否过滤
		for( String s : filteuris) {
			if( uri.indexOf( s) > -1) {
				dofilter = true;
				break;
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
		
		
		/*String uripath = req.getServletPath().toString();
		if( session.getAttribute( "user") == null ){
			if( uripath.indexOf( "/tiezi") > 0 || uripath.indexOf( "zan") > 0 || uripath.indexOf( "commit") > 0) {
				
				System.out.println("OOOO" + req.getPathTranslated() + "HHH" + req.getPathInfo() +"SERVLET"+ req.getServletPath().toString());
				resp.sendRedirect( req.getContextPath() + "/common/login");
			}
			System.out.println( "/common/index");
			System.out.println("经过已登录的方法");
			System.out.println("LOGIN" + req.getContextPath() + "OOOO" + req.getPathTranslated() + "HHH" + req.getPathInfo() +"SERVLET"+ req.getServletPath().toString());
			
			chain.doFilter(request, response);
		}else {
			//已登录，继续请求下一级资源
			System.out.println("/common/index");
			System.out.println("经过未登录的方法");
			System.out.println("LOGIN" + req.getContextPath() + "OOOO" + req.getPathTranslated() + "HHH" + req.getPathInfo() +"SERVLET"+ req.getServletPath().toString());
			chain.doFilter(request, response);
		}
		*/

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
