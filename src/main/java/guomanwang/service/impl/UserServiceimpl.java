package guomanwang.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import guomanwang.domain.Page;
import guomanwang.domain.User;
import guomanwang.util.SendSms;
import guomanwang.mapper.UserMapper;
import guomanwang.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service("UserServiceimpl")
public class UserServiceimpl implements UserService {
	@Autowired
	@Qualifier("UserMapper")
	private UserMapper userMapper;
	
	@Override
	public List<User> isLogin(String telnumber) {
		return this.userMapper.selectuserinfo(telnumber);
	}
	@Override
	public String sendMsg(String phone) {
		return	SendSms.sendsms(phone);
	}
	@Override
	public int register(User user) {
		return this.userMapper.save(user);
	}
	@Override
	public int resetpassbyphone(User user) {
		return this.userMapper.findpassbyphone(user);
	}
	@Override
	public int updateuserinfo(User user) {
		return this.userMapper.update(user);
	}
	@Override
	public int getusernumber() {
		return this.userMapper.getusernumber();
	}
	@Override
	public int deleteUserById(int userid) {
		return this.userMapper.deleteUserById(userid);
	}
	//获得所有用户信息
	@Override
	//分页获得用户信息
		public List<User> getuserlist(Page page){
		return this.userMapper.getuserlist(page);
	}
	//通过手机号获得用户信息
	@Override
	public List<User> selectuserinfo(String phone) {
		return  this.userMapper.selectuserinfo(phone);
	}
	@Override
	public User getuserbyid(int userid) {
		return this.userMapper.getuserbyid(userid);
	}
	@Override
	public List<User> getSignTop20Users() {
		return this.userMapper.getSignTop20Users();
	}
	@Override
	public int getusergradebyuserid(int userid) {
		return this.userMapper.getusergradebyuserid(userid);
	}
	@Override
	public List<User> getadminlist(Page page) {
		return this.userMapper.getadminlist(page);
	}
	@Override
	public List<User> searchadmin(User user) {
		return this.userMapper.searchadmin(user);
	}
	@Override
	public List<User> searchuser(User user) {
		return this.userMapper.searchuser(user);
	}
	@Override
	public List<User> getallusers() {
		return this.userMapper.getallusers();
	}
	@Override
	public List<User> getregistercount(String startdate, String enddate) {
		System.out.println("开始日期："+startdate+"，结束日期："+enddate);  
		return this.userMapper.getregistercount(startdate,enddate);
	}
	@Override
	public JSONObject getmonthpvuv() {
		JSONArray jsonarray=new JSONArray();
		JSONArray jsonarray1=new JSONArray();
		JSONArray jsonarray2=new JSONArray();
		JSONObject json=new JSONObject();
		List<User> monthpvuv=this.userMapper.getmonthpvuv();
		for(User user:monthpvuv) {
			jsonarray.add(user.getRegisterday().substring(0,10));
			jsonarray1.add(user.getSignDays());
			jsonarray2.add(user.getUserid());
		}
		json.put("date",jsonarray);
		json.put("pv",jsonarray1);
		json.put("uv",jsonarray2);
		return json;
	}
	@Override
	public JSONObject getsexcount(){
		JSONObject json=new JSONObject();
		List<User> sexcount=this.userMapper.getsexcount();
		for(User user:sexcount) {
			if(user.getSex().equals("男")) {
				json.put("male",user.getSignDays());
			}
			else if(user.getSex().equals("女")) {
				json.put("female",user.getSignDays());
			}
			else {
				json.put("others",user.getSignDays());
			}
		}
		return json;
	}
	@Override
	public JSONObject getThreadToptenUser() throws Exception {
        List<User> users = this.userMapper.getThreadToptenUser();
        JSONArray xArray = new JSONArray();
        JSONArray yArray = new JSONArray();
        JSONObject reJsonObject = new JSONObject();
        
        for( int i = 0; i < users.size(); i++) {
        	xArray.add( users.get(i).getUsername());
        	yArray.add( users.get(i).getSignDays());
        }
        reJsonObject.put("name", xArray);
        reJsonObject.put("y", yArray);
        
		return reJsonObject;
	}
	@Override
	public JSONObject getSignMostUsers(JSONObject param) throws Exception {
		int num = param.getInt( "num");
		List<User> users = this.userMapper.getThreadToptenUser();
        JSONArray xArray = new JSONArray();
        JSONArray yArray = new JSONArray();
        JSONObject reJsonObject = new JSONObject();
        
        for( int i = 0; i < users.size() && i < num; i++) {
        	xArray.add( users.get(i).getUsername());
        	yArray.add( users.get(i).getSignDays());
        }
        reJsonObject.put("name", xArray);
        reJsonObject.put("y", yArray);
	        
			return reJsonObject;
	}
}
