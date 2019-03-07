package guomanwang.domain;

import java.util.Date;

public class Commit {

	private int id;
	private int threadId;
	private int userId;
	//为0即回复帖子楼主，其他数字则回复对应的回复id的楼层
	private int parentId;
	//点赞数
	private int zanNumber;
	private String content;
	private Date time;
	private int isliked;
	
	public int getZanNumber() {
		return zanNumber;
	}


	public void setZanNumber(int zanNumber) {
		this.zanNumber = zanNumber;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getThreadId() {
		return threadId;
	}


	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getParentId() {
		return parentId;
	}


	public void setParentId(int parentId) {
		this.parentId = parentId;
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


	public int getIsliked() {
		return isliked;
	}


	public void setIsliked(int isliked) {
		this.isliked = isliked;
	}
	
}
