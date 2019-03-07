package guomanwang.domain;

import java.util.Date;

public class Block {
	private int id;
	private int threadnum;
	private String name;
	private int master_id;
	private Date last_time;
	private String photo;
	private String abstracts;
	
	public String getphoto() {
		return photo;
	}
	public void setphoto(String photo) {
		this.photo = photo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getThreadnum() {
		return threadnum;
	}
	public void setThreadnum(int threadnum) {
		this.threadnum = threadnum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getlast_time() {
		if(last_time!=null) {
			return TimeTransformUtil.timetransform(last_time);
		}
	    return null; 
	}
	public void setlast_time(Date last_time) {
		this.last_time = last_time;
	}
	public int getMaster_id() {
		return master_id;
	}
	public void setMaster_id(int master_id) {
		this.master_id = master_id;
	}
	public String getAbstracts() {
		return abstracts;
	}
	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}
	
}
