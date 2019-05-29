package guomanwang.domain;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class User {
	private int userId;
	
	
	private int Grade;
	//表示等级经验值"
	@NotNull(message="请输入等级经验")
	@Max(value=99,message="输入的等级经验不合法")
	@Min(value=0,message="输入的等级经验不合法")
	private int gradeValue;
	//表示用户身份，1为注册用户，2为版主，3为管理员，4为超级用户
	private int Honor;
	@NotBlank(message = "请输入性别")
	private String Sex;
	@NotBlank(message = "请输入用户名")
	private String userName;
	private String passWord;
	
	@NotBlank(message="请输入手机号")
	private String telPhone;
	private String headUrl;
	@NotBlank(message="请输入Introduce")
	private String Introduce;
	//表示注册时间
	private Date registerday;
	//表示账号状态，1为启用，0为禁用
	private int status;
	//连续签到天数
	private int signdays;
	public int getSignDays() {
		return signdays;
	}
	public void setSignDays(int signdays) {
		this.signdays = signdays;
	}
	public int getUserid() {
		return userId;
	}
	public void setUserid(int userid) {
		this.userId = userid;
	}
	public int getGrade() {
		return Grade;
	}
	public void setGrade(int grade) {
		this.Grade = grade;
	}
	public int getHonor() {
		return Honor;
	}
	public void setHonor(int Honor) {
		this.Honor = Honor;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String Sex) {
		this.Sex = Sex;
	}
	public String getPassword() {
		return passWord;
	}
	public void setPassword(String passWord) {
		this.passWord = passWord;
	}
	public String getUsername() {
		return userName;
	}
	public void setName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return telPhone;
	}
	public void setPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	public String getHeadurl() {
		return headUrl;
	}
	public void setHeadurl(String headUrl) {
		this.headUrl = headUrl;
	}
	public String getIntroduce() {
		return Introduce;
	}
	public void setIntroduce(String Introduce) {
		this.Introduce = Introduce;
	}
	public int getGradeValue() {
		return gradeValue;
	}
	public void setGradeValue(int gradeValue) {
		this.gradeValue = gradeValue;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRegisterday() {
		if(registerday!=null) {
			return TimeTransformUtil.timetransform(registerday).substring(0,10);
		}
	    return null; 
	}
	public void setRegisterday(Date registerday) {
		this.registerday = registerday;
	}
}
