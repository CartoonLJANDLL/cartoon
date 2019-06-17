package guomanwang.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import guomanwang.domain.Log;
import guomanwang.domain.User;
import guomanwang.service.LogService;
import guomanwang.util.IPUtil;

@Component
@Aspect
public class CartoonAOP {

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpSession session;
	@Autowired
	private LogService logService;
	private static Log log = new Log();
	private Logger logger = LoggerFactory.getLogger(CartoonAOP.class);
	@Pointcut("execution(* guomanwang.controller..*.*(..))")
	public void method() {
		
		System.out.println("执行了AOP中的 method方法---------------------");
	}
	@After("method()")
	public void after(JoinPoint joinPoint) {
		System.out.println("执行了after方法---------------------");
		System.err.println("this is after ..........");
	}
	
	  /*获取message，存入log*/
	 
	@AfterThrowing(pointcut = "method()", throwing = "e")
	public void afterThrowing( JoinPoint joinPoint, Exception e) {
		String message = e.getMessage();
		System.out.println("AOP捕获到异常了" + message + "HHHHHHHHHH");
		log.setLevel("ERROR");
		log.setMessage(message);
		log.setLevel("error");
	}
	
	/*  获取uri,host,userid值存入log，并将log写入数据库*/
	 
	@AfterReturning("method()")
	public void afterReturning(JoinPoint joinPoint) throws Exception{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		System.out.println("开始执行afterreturning方法---------------");
		log.setLevel("Info");
		String uri = request.getRequestURI();
		log.setUri(uri);
		String host = IPUtil.getIpAddr(request);
		log.setHost(host);
		User thisUser = (User)session.getAttribute("user");
		if( thisUser != null) {
			int userId = thisUser.getUserid();
			log.setUserid(userId);
		}
		//录入数据库
		int rs = this.logService.insertLogSelective(log);
		System.out.println("日志插入数据库条数结果" + rs);
	}
	
//	  获取ctime,endtime,exctime,clazz,method,params值存入log
	 
	@Around("method()")
	public Object around(ProceedingJoinPoint pjd) throws Throwable{
		System.out.println("开始执行around                 OOOOOOOOOOOO");
		log.setCtime(new Date());
		long startTime = System.currentTimeMillis();
		String className = pjd.getTarget().getClass().getName();
		log.setClazz(className);
		String methodName = pjd.getSignature().getName();
		log.setMethod(methodName);
		Object[] params = pjd.getArgs();
		String paramsStr = "";
		for( Object param : params) {
			paramsStr += ( param + ", ");
		}
		log.setParams(paramsStr);
		//执行方法，获取返回参数
		Object result = pjd.proceed();
		long endTime = System.currentTimeMillis();
		float excTime = (float)(endTime - startTime)/1000;
		log.setEndtime(new Date());
		log.setExctime(excTime);
		//System.out.println("AOPLOG  " + log.getParams()+ log.getClazz() + log.getMethod() + log.getFilename() + log.getMessage() + log.getLinenumber());
		
		return result;
	}
	
}
