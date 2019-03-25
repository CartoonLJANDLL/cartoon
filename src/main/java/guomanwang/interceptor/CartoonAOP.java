package guomanwang.interceptor;

import guomanwang.domain.Log;
import guomanwang.domain.User;
import guomanwang.interceptor.Operation;
import guomanwang.service.LogService;
import guomanwang.util.IPUtil;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
	@Pointcut("execution(* guomanwang.controller.*.*(..))")
	public void method() {
		
		System.out.println("执行了AOP中的 method方法---------------------");
	}
	@After("method()")
	public void after(JoinPoint joinPoint) {
		System.out.println("执行了after方法---------------------");
		System.err.println("this is after ..........");
	}
	
	@AfterReturning("method()")
	public void afterReturning(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		System.out.println("开始执行afterreturning方法---------------");
		
		log.setLogid(null);
		log.setCtime(new Date());
		long startTime = System.currentTimeMillis();
		
		String uri = request.getRequestURI();
		log.setUri(uri);
		String host = IPUtil.getIpAddr(request);
		log.setHost(host);
		/*//执行方法，获取返回参数
		Object result = pjd.proceed();
		//方法执行后
		String message = operation.name();*/
		/*log.setMessage(message);*/
		User thisUser = (User)session.getAttribute("user");
		if( thisUser != null) {
			int userId = thisUser.getUserid();
			log.setUserid(userId);
		}
		long endTime = System.currentTimeMillis();
		float excTime = (float)(endTime - startTime)/1000;
		log.setEndtime(new Date());
		log.setExctime(excTime);
		
		//通过SpringAop切面joinpoint类对象，获取该类或该方法或该方法的参数
		//获取当前方法
		Class<? extends Object> clazz = joinPoint.getTarget().getClass();
		String controllerOperation = clazz.getName();
		if( clazz.isAnnotationPresent( Operation.class)) {
			//当前controller操作的名称
			controllerOperation = clazz.getAnnotation( Operation.class).name();
			//类名
			log.setClazz(controllerOperation);
		}
		//参数名
		Class<? extends Object> params1 = joinPoint.getArgs().getClass();
		String paramNames = params1.getName();
		log.setParams(paramNames);
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		//clazz类下的所有方法
		Method[] methods = clazz.getDeclaredMethods();
		String methodOperation = "";
		for( Method m : methods) {
			if( m.equals( method)) {
				methodOperation = m.getName();
				if( m.isAnnotationPresent(Operation.class)) {
					methodOperation = m.getAnnotation(Operation.class).name();
				}
			}
		}
		//方法名
		log.setMethod(methodOperation);		
		Object username = request.getSession().getAttribute("user");
		if( username != null) {
			logger.info( username + "执行了 " + controllerOperation + "下的 " + methodOperation + "  操作！IP地址为" + request.getRemoteHost());
		}else {
			logger.info( "未知用户执行了 " + controllerOperation + "下的 " + methodOperation + "  操作！IP地址为" + request.getRemoteHost());
		}
		
		//录入数据库
		int rs = this.logService.insertLogSelective(log);
		System.out.println("日志插入数据库条数结果" + rs);
	}
	
	
	@Around("within(guomanwang.controller..*) && @annotation(operation)")
	public Object around(ProceedingJoinPoint pjd, Operation operation) throws Throwable{
		System.out.println("执行了around                 OOOOOOOOOOOO");
		log.setLogid(null);
		log.setCtime(new Date());
		long startTime = System.currentTimeMillis();
		
		//类名
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
		String uri = request.getRequestURI();
		log.setUri(uri);
		String host = IPUtil.getIpAddr(request);
		log.setHost(host);
		//执行方法，获取返回参数
		Object result = pjd.proceed();
		
		//方法执行后
		String message = operation.name();
		log.setMessage(message);
		User thisUser = (User)session.getAttribute("user");
		if( thisUser != null) {
			int userId = thisUser.getUserid();
			log.setUserid(userId);
		}
		long endTime = System.currentTimeMillis();
		float excTime = (float)(endTime - startTime)/1000;
		log.setEndtime(new Date());
		log.setExctime(excTime);
		System.out.println("AOPLOG  " + log.getClazz() + log.getFilename() + log.getMessage() + log.getLinenumber());
		//录入数据库
		int rs = this.logService.insertLogSelective(log);
		System.out.println("日志插入数据库条数结果" + rs);
		return result;
	}
	
}
