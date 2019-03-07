package guomanwang.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import guomanwang.domain.Page;
import guomanwang.domain.User;
import guomanwang.domain.ValiMsgUtils;
import guomanwang.mapper.UserMapper;
import guomanwang.service.UserService;
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
		return	ValiMsgUtils.send(phone);
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
	//通过用户名或者手机号实现模糊查询
	@Override
	public List<User> searchuserbyname(String key) {
		return this.userMapper.searchuserbyname(key);
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
	public List<User> searchadminbyname(String key) {
		return this.userMapper.searchadminbyname(key);
	}
}
