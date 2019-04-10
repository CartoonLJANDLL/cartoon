package guomanwang.websocket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import guomanwang.domain.User;

/** 
 * WebSocket握手拦截器 
 */  
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {  
 
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {  
 
    	if (request instanceof ServletServerHttpRequest) {
    		ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;  
            HttpSession session = servletRequest.getServletRequest().getSession(false);  
            if (session != null) {
            	User user=(User)session.getAttribute("user");
                int userId = user.getUserid(); //这边获得登录时设置的唯一用户标识  
                attributes.put("WEBSOCKET_USERID", userId);  //将用户标识放入参数列表后，下一步的websocket处理器可以读取这里面的数据
            }  
        }  
        return true;  
    }  
  
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {  
        System.out.println("After Handshake");  
    }
}
