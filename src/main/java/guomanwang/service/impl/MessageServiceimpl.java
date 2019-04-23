package guomanwang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import guomanwang.domain.Message;
import guomanwang.domain.User;
import guomanwang.mapper.FriendRelationMapper;
import guomanwang.mapper.MessageMapper;
import guomanwang.service.MessageService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("MessageServiceimpl")
public class MessageServiceimpl implements MessageService{
	
	@Autowired
	@Qualifier("MessageMapper")
	private MessageMapper messagemapper;
	@Autowired
	@Qualifier("FriendRelationMapper")
	private FriendRelationMapper friendrelationmapper;
	
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
	
	@Override
	public JSONArray getfriendmsgsbyuserid(int userid) {
		JSONObject jsonobject= new JSONObject();
		JSONArray jsonArray = new JSONArray();
		//根据用户id获得所有好友信息
		List<User> friends=this.friendrelationmapper.getfriendsbyuserid(userid);
		//根据用户id获得所有好友信息
		List<Message> msgs=this.messagemapper.getfriendmsgsbyuserid(userid);
		//封装成json数据返回
		jsonobject.put("count",friends.size());
		jsonobject.put("uid", userid);
		for(int i=0;i<friends.size();i++) {
			JSONObject msgjson= new JSONObject();
			JSONArray jsonarray = new JSONArray();
			msgjson.put("fid",friends.get(i).getUserid());
			for(int j=0;j<msgs.size();j++) {
				if(msgs.get(j).getSenderid()==friends.get(i).getUserid()||msgs.get(j).getReceiverid()==friends.get(i).getUserid()) {
					jsonarray.add(new Gson().toJson(msgs.get(j)));
				}
			}
			msgjson.put("data",jsonarray);
			jsonArray.add(msgjson);
		}
		return jsonArray;
	}

	
}
