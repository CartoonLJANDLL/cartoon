package guomanwang.domain;

import java.util.Date;

public class Thread {

	private int id;
	private int userId;
	private int blockId;
	private int commitNumber;
	private int status;
	private int greatNum;
	public int getGreatNum() {
		return greatNum;
	}

	public void setGreatNum(int greatNum) {
		this.greatNum = greatNum;
	}

	private String title;
	private String content;
	private Date time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBlockId() {
		return blockId;
	}

	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}

	public int getCommitNumber() {
		return commitNumber;
	}

	public void setCommitNumber(int commitNumber) {
		this.commitNumber = commitNumber;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		if(time!=null) {
			return TimeTransformUtil.timetransform(time);
		}
		return null;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}