package guomanwang.domain;

import java.util.Date;

public class CommitZan {
	private int id;
	private  int commitid;
	private  int userid;
	private Date time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCommitid() {
		return commitid;
	}
	public void setCommitid(int commitid) {
		this.commitid = commitid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
