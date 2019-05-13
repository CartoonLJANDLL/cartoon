package guomanwang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import guomanwang.domain.Page;
import guomanwang.domain.User;

@Repository("UserMapper")
public interface UserMapper {
	public int save(User user);
	//更新个人信息
	public int update(User user);
	//根据手机号找回密码
	public int findpassbyphone(User user);
	//通过手机号获得用户
	public List<User> selectuserinfo(String telnumber);
	//获得用户数量
	public int getusernumber();
	//通过id获得用户信息
    public User getuserbyid(int userid);
    //通过usereid获得用户等级信息
    public int getusergradebyuserid(int userid);
	//删除用户
	public int deleteUserById(int userid);
	//分页获得用户信息
	public List<User> getuserlist(Page page);
	//根据用户名或者手机号实现用户的模糊查询
	public List<User> searchuserbyname(String key);
	//分页获得管理员信息
	public List<User> getadminlist(Page page);
	//根据用户名或者手机号实现管理员的模糊查询
	public List<User> searchadminbyname(String key);
	//获得连续签到前20名用户
	public List<User> getSignTop20Users();
	//获得所有用户，包括管理员
	public List<User> getallusers();
	//获得近一个月的pv和uv数据返回的参数分别为日期、每日pv、每日uv
	public List<User> getmonthpvuv();
	//根据输入的日期范围获得该时间段内每日新增用户
	public List<User> getregistercount(@Param("startdate")String startdate, @Param("enddate")String enddate);
}
