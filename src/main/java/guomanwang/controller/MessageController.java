package guomanwang.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import guomanwang.domain.Message;
import guomanwang.service.MessageService;
import net.sf.json.JSONObject;

@RequestMapping("/message")
@Controller
public class MessageController {
	@Autowired
	@Qualifier("MessageServiceimpl")
	private MessageService messageservice;
	
	//用户发送一条私信
	@ResponseBody
	@RequestMapping("/postmessage")
	public JSONObject postmessage(int senderid,int receiverid,String content,String type) {
		JSONObject json = new JSONObject();
		Message message=new Message();
		message.setReceiver_status(1);
		message.setReceiverid(receiverid);
		message.setContent(content);
		message.setTime(new Date());
		message.setSender_status(1);
		message.setSenderid(senderid);
		message.setType(type);
		int change=this.messageservice.insertMessage(message);
		if(change>0) {
			json.put("code", 1);
			json.put("msg","消息发送成功！");
		}
		else {
			json.put("code", 0);
			json.put("msg","消息发送失败！请重试");
		}
		return json;
	}
	//用户删除一条消息(完全删除）
	@ResponseBody
	@RequestMapping("/deletemessage")
	public JSONObject deletemessage(int id) {
		JSONObject json = new JSONObject();
		int change=this.messageservice.deleteMessageById(id);
		if(change>0) {
			json.put("code", 1);
			json.put("msg","消息已删除！");
		}
		else {
			json.put("code", 0);
			json.put("msg","操作失败！请重试");
		}
		return json;
		}
	//用户删除一条消息（标记删除）*deletetype标记是删除接收的还是发出去的；1发2收
	@ResponseBody
	@RequestMapping("/updatemessage")
	public JSONObject updatemessage(int id,int deletetype) {
		JSONObject json = new JSONObject();
		Message message=new Message();
		if(deletetype==2) {
			message.setReceiver_status(0);
		}
		else {
			message.setSender_status(0);
		}
		message.setId(id);
		int change=this.messageservice.update(message);
		if(change>0) {
			json.put("code", 1);
			json.put("msg","消息已删除！");
		}
		else {
			json.put("code", 0);
			json.put("msg","操作失败！请重试");
		}
		return json;
		}
	//删除当前用户接收到的所有消息
	@ResponseBody
	@RequestMapping("/deleteallmessages")
	public JSONObject deleteallmessages(int userid) {
	JSONObject json = new JSONObject();
	int change=this.messageservice.deleteAllMessageByreceiverid(userid);
	if(change>0) {
		json.put("code", 1);
		json.put("msg","所有消息已删除！");
	}
	else {
		json.put("code", 0);
		json.put("msg","操作失败！请重试");
	}
	return json;
	}
}
