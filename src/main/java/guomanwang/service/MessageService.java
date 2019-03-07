package guomanwang.service;

import java.util.List;

import guomanwang.domain.Message;

public interface MessageService {
	public int update(Message message);
	public int insertMessage(Message message);
	public int deleteMessageById(int id);
	public Message getmessagebyid(int id);
	public List<Message> getmessagesbysenderid(int userid);
	public List<Message> getmessagesbyreceiverid(int userid);
	public int deleteAllMessageByreceiverid(int userid);
}
