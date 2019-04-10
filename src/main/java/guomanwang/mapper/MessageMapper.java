package guomanwang.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import guomanwang.domain.Message;
@Repository("MessageMapper")
public interface MessageMapper {
	public int update(Message message);
	public int insertMessage(Message message);
	public int deleteMessageById(int id);
	public Message getmessagebyid(int id);
	public List<Message> getmessagesbysenderid(int userid);
	public List<Message> getmessagesbyreceiverid(int userid);
	public List<Message> getfriendmsgsbyuserid(int userid);
	public int deleteAllMessageByreceiverid(int userid);
}
