package guomanwang.domain;

public class UserThread extends Thread {

	private int grade;
	private int honor;
	private String sex;
	private String userName;
	private String headUrl;
	public String getHeadUrl() {
		return headUrl;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getHonor() {
		return honor;
	}
	public void setHonor(int honor) {
		this.honor = honor;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
}
