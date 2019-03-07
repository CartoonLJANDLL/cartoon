package guomanwang.domain;
//好友关系
public class FriendRelation {
	private int id;
	//好友请求发送方
	private int userid;
	//好友请求接收方
	private int friendid;
	//表示接受方是否同意的字段，1为同意，0为对方还未处理该请求
	private int receiver_status;
	
	public int getReceiver_status() {
		return receiver_status;
	}
	public void setReceiver_status(int receiver_status) {
		this.receiver_status = receiver_status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getFriendid() {
		return friendid;
	}
	public void setFriendid(int friendid) {
		this.friendid = friendid;
	}
	
}
