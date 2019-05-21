package guomanwang.domain;

import java.util.Date;

public class Information {
	private int id;
	private String title;
	private String content;
	private String url;
	private String status;
	private Date time;
	private int collectnum;
	private int company;
	private String companyname;
	private int viewcount;
	
	public int getViewcount() {
		return viewcount;
	}
	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}

	public int getCompany() {
		return company;
	}
	public void setCompany(int company) {
		this.company = company;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		return TimeTransformUtil.timetransform(time);
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getCollectnum() {
		return collectnum;
	}
	public void setCollectinum(int collectnum) {
		this.collectnum = collectnum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
