package guomanwang.domain;

import java.util.Date;

public class Commit_User {

	int id;
	int userid;
	int commitid;
	int status;
	Date time;
	
	public Commit_User() {
		
	}
	public Commit_User(int id, int userid, int commitid, int status, Date time) {
		super();
		this.id = id;
		this.userid = userid;
		this.commitid = commitid;
		this.status = status;
		this.time = time;
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
	public int getCommitid() {
		return commitid;
	}
	public void setCommitid(int commitid) {
		this.commitid = commitid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
}
