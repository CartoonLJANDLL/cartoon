package guomanwang.service;
import guomanwang.domain.Page;
import guomanwang.domain.User;
import net.sf.json.JSONObject;

import java.util.List;

public interface UserService {

	List<User> isLogin(String telnumber);
	String sendMsg(String phone);
	int register(User user);
	int resetpassbyphone(User user);
	int updateuserinfo(User user);
	int getusernumber();
	public int deleteUserById(int userid);
	///分页获得用户信息
	public List<User> getuserlist(Page page);
	//通过手机号获得用户信息
	public List<User> selectuserinfo(String phone);
	//根据用户名或者手机号实现用户的模糊查询
	public List<User> searchuserbyname(String key);
	//通过id获得用户信息
    public User getuserbyid(int userid);
	//获得连续签到前20名用户
	public List<User> getSignTop20Users();
	//分页获得管理员信息
	public List<User> getadminlist(Page page);
	//根据用户名或者手机号实现管理员的模糊查询
	public List<User> searchadminbyname(String key);
    //通过usereid获得用户等级信息
    public int getusergradebyuserid(int userid);
	//获得所有用户，包括管理员
	public List<User> getallusers();
	//获得近一个月的pv和uv数据返回的参数分别为日期、每日pv、每日uv
	public JSONObject getmonthpvuv();
	//根据输入的日期范围获得该时间段内每日新增用户
	public List<User> getregistercount(String startdate, String enddate);
}
