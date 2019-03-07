package guomanwang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import guomanwang.domain.Message;
import guomanwang.mapper.MessageMapper;
import guomanwang.service.MessageService;

@Service("MessageServiceimpl")
public class MessageServiceimpl implements MessageService{
	
	@Autowired
	@Qualifier("MessageMapper")
	private MessageMapper messagemapper;
	
	@Override
	public int update(Message message) {
		return this.messagemapper.update(message);
	}

	@Override
	public int insertMessage(Message message) {
		return this.messagemapper.insertMessage(message);
	}

	@Override
	public int deleteMessageById(int id) {
		return this.messagemapper.deleteMessageById(id);
	}

	@Override
	public Message getmessagebyid(int id) {
		return this.messagemapper.getmessagebyid(id);
	}

	@Override
	public List<Message> getmessagesbysenderid(int userid) {
		return this.messagemapper.getmessagesbysenderid(userid);
	}

	@Override
	public List<Message> getmessagesbyreceiverid(int userid) {
		return this.messagemapper.getmessagesbyreceiverid(userid);
	}

	@Override
	public int deleteAllMessageByreceiverid(int userid) {
		return this.messagemapper.deleteAllMessageByreceiverid(userid);
	}
	
}
