package guomanwang.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import guomanwang.domain.Message;
import guomanwang.domain.User;
import guomanwang.service.MessageService;
import guomanwang.service.UserService;

/**
 * Websocket处理器
 */
 
public class WebSocketHandler extends TextWebSocketHandler {
	@Autowired
	@Qualifier("UserServiceimpl")
	private UserService userService;
	@Autowired
	@Qualifier("MessageServiceimpl")
	private MessageService messageservice;
	
	// 已建立连接的用户
	private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();
 
	/**
	 * 处理前端发送的文本信息 js调用websocket.send时候，会调用该方法
	 * 
	 * @param session
	 * @param message
	 * @throws Exception
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		int userid = (int) session.getAttributes().get("WEBSOCKET_USERID");
		Message msg=new Message();
		msg.setReceiver_status(1);
		msg.setTime(new Date());
		msg.setSender_status(1);
		msg.setSenderid(userid);
		msg.setType("私信");
		// 分割成id和信息内容
		String[] messageInfo = message.getPayload().split("@");
		System.out.println(messageInfo[0]);
		if (messageInfo.length != 2) {
			sendMessageToUser(userid, new TextMessage("服务器出错请稍后再发送吧"));
		} else {
			int target = Integer.parseInt(messageInfo[0]);
			String content = messageInfo[1];
			msg.setReceiverid(target);
			msg.setContent(content);
			messageservice.insertMessage(msg);
			// 遍历所有已连接用户
			for (WebSocketSession user : users) {
				if (user.getAttributes().get("WEBSOCKET_USERID").equals(target)) {
					//遇到匹配用户 连接正常则发送消息
					if (user.isOpen()) {
						sendMessageToUser(target, new TextMessage(userid+"@"+content));
					}else{//若异常则发送失败
						sendMessageToUser(userid, new TextMessage("对方在线异常,发送失败"));
					}
					return;
				}
			}
			//未找到匹配用户 发送失败
			sendMessageToUser(userid, new TextMessage(target+"@0"));
		}
	}
 
	/**
	 * 当新连接建立的时候，被调用 连接成功时候，会触发页面上onOpen方法
	 * 
	 * @param session
	 * @throws Exception
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		users.add(session);
		int userid = (int) session.getAttributes().get("WEBSOCKET_USERID");
		System.out.println("用户编号 " + userid + "已上线！");
	}
 
	/**
	 * 当连接关闭时被调用
	 * 
	 * @param session
	 * @param status
	 * @throws Exception
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		int userid = (int) session.getAttributes().get("WEBSOCKET_USERID");
		System.out.println("用户 " + userid + "连接已关闭，状态: " + status);
		users.remove(session);
	}
 
	/**
	 * 传输错误时调用
	 * 
	 * @param session
	 * @param exception
	 * @throws Exception
	 */
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		int userid = (int) session.getAttributes().get("WEBSOCKET_USERID");
		if (session.isOpen()) {
			session.close();
		}
		System.out.println("用户: " + userid+ " websocket connection closed......");
		users.remove(session);
	}
 
	/**
	 * 给所有在线用户发送消息
	 * 
	 * @param message
	 */
	public void sendMessageToUsers(TextMessage message) {
		for (WebSocketSession user : users) {
			try {
				if (user.isOpen()) {
					user.sendMessage(message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
 
	/**
	 * 给某个用户发送消息
	 * 
	 * @param userName
	 * @param message
	 */
	public void sendMessageToUser(int userid, TextMessage message) {
		for (WebSocketSession user : users) {
			if (user.getAttributes().get("WEBSOCKET_USERID").equals(userid)) {
				try {
					if (user.isOpen()) {
						user.sendMessage(message);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
}
