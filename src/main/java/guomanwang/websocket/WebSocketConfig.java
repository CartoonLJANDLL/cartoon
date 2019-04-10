package guomanwang.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/** 
 * Spring WebSocket的配置，这里采用的是注解的方式 
 */  
  
@Configuration  
@EnableWebSocket  
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {  
  
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {  
        //1.注册WebSocket  
        String websocket_url = "/websocket/socketServer";                        //设置websocket的地址  
        registry.addHandler(webSocketHandler(), websocket_url).                          //注册Handler  
                addInterceptors(new WebSocketHandshakeInterceptor());                   //注册Interceptor  
  
        //2.注册SockJS，提供SockJS支持(主要是兼容ie8)  
        String sockjs_url = "/sockjs/socketServer";                              //设置sockjs的地址  
        registry.addHandler(webSocketHandler(), sockjs_url).                            //注册Handler  
                addInterceptors(new WebSocketHandshakeInterceptor()).                   //注册Interceptor  
                withSockJS();                                                           //支持sockjs协议  
    }  
  
    @Bean
    public TextWebSocketHandler webSocketHandler() {  
        return new WebSocketHandler();  
    }  
}
