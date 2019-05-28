package guomanwang.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mysql.fabric.Response;

import guomanwang.domain.User;
import guomanwang.domain.UserCommit;
import guomanwang.domain.UserThread;
import guomanwang.domain.Commit;
import guomanwang.domain.Defaulthead;
import guomanwang.domain.FriendRelation;
import guomanwang.domain.MD5Cripy;
import guomanwang.domain.Message;
import guomanwang.domain.Sign;
import guomanwang.domain.Thread;
import guomanwang.domain.TimeTransformUtil;
import guomanwang.service.CommitService;
import guomanwang.service.DefaultheadService;
import guomanwang.service.FriendRelationService;
import guomanwang.service.MessageService;
import guomanwang.service.SignService;
import guomanwang.service.ThreadService;
import guomanwang.service.UserCommitService;
import guomanwang.service.UserService;
import guomanwang.service.UserThreadService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RequestMapping("/user")
@Controller("UserController")
public class UserController {
	@Autowired
	@Qualifier("UserServiceimpl")
	private UserService userService;
	@Autowired
	@Qualifier("UserThreadServiceimpl")
	private UserThreadService userthreadService;
	@Autowired
	@Qualifier("ThreadServiceimpl")
	private ThreadService threadService;
	@Autowired
	@Qualifier("CommitServiceimpl")
	private CommitService commitService;
	@Autowired
	@Qualifier("DefaultheadServiceimpl")
	private DefaultheadService defaultheadService;
	@Autowired
	@Qualifier("UserCommitServiceimpl")
	private UserCommitService userCommitService;
	@Autowired
	@Qualifier("SignServiceimpl")
	private SignService signservice;
	@Autowired
	@Qualifier("FriendRelationServiceimpl")
	private FriendRelationService friendrelationservice;
	@Autowired
	@Qualifier("MessageServiceimpl")
	private MessageService messageservice;
	
	//获得好友聊天记录
	@RequestMapping("/getfriendsmsgsbyuserid")
	@ResponseBody
	public JSONArray getfriendsmsgsbyuserid(HttpSession session,HttpServletRequest request) {
		User user=(User)session.getAttribute("user");
		JSONArray friendmsgs=this.messageservice.getfriendmsgsbyuserid(user.getUserid());
		return friendmsgs;
	}
	//用户个人中心
	@RequestMapping("/user_index")
	public String user_index(Model model,HttpSession session,
			HttpServletRequest request) {
		User userinfo=(User)session.getAttribute("user");
		if(userinfo==null) {
			return "redirect:/login";
		}
		else {
			List<UserThread> threadlist=this.userthreadService.selectMyPushedThread(userinfo.getUserid());
			if(threadlist!=null) {model.addAttribute("threadlist", threadlist);}
				return "user/user_index";
		}	
	}
	//用户主页
	@RequestMapping("/user_home")
	public String user_home(HttpSession session,HttpServletRequest request,int userId,Model model){
			List<UserThread> mycommits=this.userthreadService.selectMyCommitThread(userId);
			List<UserThread> threadlist=this.userthreadService.selectMyPushedThread(userId);
			User user=(User)session.getAttribute("user");
			FriendRelation friendrelation=new FriendRelation();
			if(user!=null) {
				friendrelation.setFriendid(userId);
				friendrelation.setUserid(user.getUserid());
				int friendstatus=friendrelationservice.isfriend(friendrelation);
				request.setAttribute("friendstatus",friendstatus);
			}
			User userinfo=this.userService.getuserbyid(userId);
			request.setAttribute("mycommits",mycommits);
			request.setAttribute("threadlist",threadlist);
			request.setAttribute("userinfo",userinfo);
			return "user/user_home";
		
	}
	//用户设置
	@RequestMapping("/user_setting")
	public String user_setting(HttpServletRequest request){
		HttpSession session = request.getSession();
		List<Defaulthead> defaultheadlist=this.defaultheadService.getallDefaulthead();
		User user=(User)session.getAttribute("user");
		if(user!=null) {
			request.setAttribute("defaultheadlist", defaultheadlist);
			return "user/user_setting";
		}
		return "redirect:/login";
	}
	//用户好友
	@RequestMapping("/user_friends")
	public String user_friends(HttpServletRequest request){
		HttpSession session = request.getSession();		
		User user=(User)session.getAttribute("user");
		if(user!=null) {
			List<User> friends=this.friendrelationservice.getfriendsbyuserid(user.getUserid());
			request.setAttribute("friends", friends);
			return "user/user_friends";
		}
		return "redirect:/login";
	}
	//用户消息
	@RequestMapping("/user_message")
	public String user_message(HttpServletRequest request,HttpSession session){
		User user=(User)session.getAttribute("user");
		if(user!=null) {
		List<Message> messages=this.messageservice.getmessagesbyreceiverid(user.getUserid());
		List<User> requestfriends=this.friendrelationservice.getrequestsbyuserid(user.getUserid());
		List<User> askrequests=this.friendrelationservice.getasksbyuserid(user.getUserid());
		List<Message> systemmessages=new ArrayList<Message>();
		List<Message> friendmessages=new ArrayList<Message>();
		List<Message> othermessages=new ArrayList<Message>();
		List<User> friends=this.friendrelationservice.getfriendsbyuserid(user.getUserid());
		for(Message msg:messages) {
			if(msg.getType().equals("私信")) {
				for(User friend:friends) {
					if(msg.getReceiverid()==friend.getUserid()||msg.getSenderid()==friend.getUserid()) {
						friendmessages.add(msg);
					}
				}
			}
			else if(msg.getType().equals("帖子")) {
				othermessages.add(msg);
			}
			else { systemmessages.add(msg);}
		}
		request.setAttribute("systemmessages", systemmessages);
		request.setAttribute("friendmessages", friendmessages);
		request.setAttribute("othermessages", othermessages);
		request.setAttribute("requestfriends", requestfriends);
		request.setAttribute("askrequests", askrequests);
		return "user/user_message";
		}
		return "redirect:/login";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception {
	       session.removeAttribute("user");
	       session.invalidate();
	       return "redirect:/common/index";
	   }
	
	@RequestMapping("/changepassword")
	@ResponseBody
	public JSONObject changepassword(String password,String newpassword, Model model,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		JSONObject json= new JSONObject();
		System.out.println("经过密码更改方法");
		User userinfo=(User)session.getAttribute("user");
		User user=new User();
		String pass=MD5Cripy.MD5(password);
		String newpass=MD5Cripy.MD5(newpassword);
		if(pass.equals(userinfo.getPassword())) {
			user.setPassword(newpass);
			user.setPhone(userinfo.getPhone());
			user.setGradeValue(userinfo.getGradeValue());
			user.setHonor(userinfo.getHonor());
			user.setIntroduce(userinfo.getIntroduce());
			user.setSex(userinfo.getSex());
			user.setUserid(userinfo.getUserid());
			user.setStatus(1);
			int change_row=this.userService.updateuserinfo(user);
			if(change_row>0) {
				session.removeAttribute("user");
			    session.invalidate();
			    json.put("code", 1);
			    json.put("msg","密码修改成功，请重新登录！");
			}
		}
		else {
			json.put("code", 0);
		    json.put("msg","密码修改失败，当前密码输入错误，请重试！");
		}
			return json;
		
	}
	//更新个人资料
	@RequestMapping("/updateuserinfo")
	@ResponseBody
	public JSONObject resetinfo(String cellphone,String username,String sex,String introduce, Model model,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		JSONObject json= new JSONObject();
		User userinfo=(User)session.getAttribute("user");
		User user=new User();
			user.setPhone(cellphone);
			user.setName(username);
			user.setIntroduce(introduce);
			user.setSex(sex);
			user.setStatus(1);
			user.setUserid(userinfo.getUserid());
			user.setGradeValue(userinfo.getGradeValue());
			user.setHonor(userinfo.getHonor());
			int change_row=this.userService.updateuserinfo(user);
			if(change_row>0) {
				List<User> userlist=this.userService.isLogin(cellphone);
				for(User one:userlist) {
						session.setAttribute("user", one);
					}
				json.put("code",1);
				json.put("msg","个人资料更新成功！");
			}
			else {
				json.put("code",0);
				json.put("msg","个人资料更新失败！");
				}
			return json;
		
	}
	//在系统默认头像中选择头像
		@RequestMapping("/selectdefaultheadimage")
		@ResponseBody
		public JSONObject selectdefaultheadimage(Model model,String imageurl,HttpSession session) {
			User userinfo=(User)session.getAttribute("user");
			JSONObject json= new JSONObject();
			User user=new User();
			user.setUserid(userinfo.getUserid());
			user.setHonor(userinfo.getHonor());
			user.setStatus(1);
			user.setGradeValue(userinfo.getGradeValue());
			user.setHeadurl(imageurl);
			int change_row=this.userService.updateuserinfo(user);
			if(change_row>0) {
				List<User> userlist=this.userService.isLogin(userinfo.getPhone());
				for(User one:userlist) {
						session.setAttribute("user", one);
					}
				json.put("code", 1);
				json.put("msg","头像修改成功！");
			}
			else {json.put("code", 0);
				json.put("msg","头像修改失败！");}
			return json;
		}
	//点击确认更改，更新用户信息(头像）
	@RequestMapping("/changeheadimage")
	@ResponseBody
	public JSONObject changeheadimage(Model model,HttpSession session,String headimage) {
		User userinfo=(User)session.getAttribute("user");
		System.out.println("经过changeheadimage");
		JSONObject json= new JSONObject();
		User user=new User();
		user.setUserid(userinfo.getUserid());
		user.setHonor(userinfo.getHonor());
		user.setGradeValue(userinfo.getGradeValue());
		user.setHeadurl(headimage);
		user.setStatus(1);
		int change_row=this.userService.updateuserinfo(user);
		if(change_row>0) {
			List<User> userlist=this.userService.isLogin(userinfo.getPhone());
			for(User one:userlist) {
					session.setAttribute("user", one);
				}
			json.put("msg","头像修改成功！");
		}
		else {json.put("msg","头像修改失败！");}
		return json;
	}
	//上传图片
	@RequestMapping("/uploadHeadImage")
	@ResponseBody
	public JSONObject uploadHeadImage(@RequestParam("file") MultipartFile file, @ModelAttribute User user, HttpServletRequest request,@RequestParam(value="src",defaultValue="/resources/img/people") String src, InputStream stream)
			throws IOException {
		Assert.notNull(file, "上传文件不能为空");
		HttpSession session = request.getSession();
		String path = request.getSession().getServletContext().getRealPath("/"+src);
		//String path="D:/Spring/guomanwang/src/main/webapp/resources/img/people";
		JSONObject json= new JSONObject();
		//System.currentTimeMillis()根据系统时间产生随机数，保证上传的图片名字不一样
		String name=System.currentTimeMillis()+file.getOriginalFilename();
		File dir = new File(path, name);
		src=src+"/"+name;
		System.out.println(src);
		if (!dir.exists()) {
			dir.mkdirs();
			json.put("msg","图片上传成功");
			json.put("code",0);
			JSONObject one= new JSONObject();
			one.put("src", "/guomanwang"+src);
			one.put("title","");
			json.put("data",one);
		} else {
			json.put("msg","图片上传失败");
			json.put("code",1);
			json.put("data","");
		}
		file.transferTo(dir);
		return json;
	}
	//用户签到
	@ResponseBody
	@RequestMapping("/sign")
	public JSONObject sign(int userid,HttpSession session,int signvalue) {
		JSONObject json= new JSONObject();
		User userinfo=userService.getuserbyid(userid);
		User user=new User();
		user.setUserid(userinfo.getUserid());
		user.setHonor(userinfo.getHonor());
		user.setHeadurl(userinfo.getHeadurl());
		user.setStatus(1);
		user.setGradeValue(userinfo.getGradeValue()+signvalue);
		Date date=new Date();
		Sign sign=new Sign();
		sign.setUserid(userid);
		sign.setSigndate(TimeTransformUtil.timetransform(date).substring(0, 10));
		int rs=signservice.Sign(sign);
		if(rs>0) {
			json.put("code", 1);
			json.put("msg", "签到成功！增加"+signvalue+"点经验");
			userService.updateuserinfo(user);
			session.setAttribute("signstatus",1);
		}
		else {
			json.put("code", 0);
			json.put("msg", "签到失败！");
		}
		return json;
	}
	//在用户个人中心界面根据帖子id删除帖子
	@RequestMapping("/deletethread")
	public String deletethread(int id,Model model) {
		int change_row=this.threadService.deleteThreadById(id);
		if(change_row>0) {
			model.addAttribute("msg","成功删除！");
		}
		else {model.addAttribute("msg","删除失败！请重试！");}
		return "redirect:/user/user_index";
		
	}
	//在用户我的好友界面根据好友id和当前登录用户id删除好友
	@ResponseBody
	@RequestMapping("/deletefriend")
	public JSONObject deletefriend(HttpSession session,int friendid,Model model) {
		JSONObject json= new JSONObject();
		User user=(User)session.getAttribute("user");
		FriendRelation friendrelation=new FriendRelation();
		friendrelation.setFriendid(friendid);
		friendrelation.setUserid(user.getUserid());
		int change_row=this.friendrelationservice.deleteFriend(friendrelation);
		if(change_row>0) {
			json.put("code",1);
			json.put("msg","操作成功！");
		}
		else {
			json.put("code",0);
			json.put("msg","操作失败！请重试！");}
		return json;	
	}
	//同意加好友请求
		@ResponseBody
		@RequestMapping("/passfriend")
		public JSONObject passfriend(HttpSession session,int friendid,Model model) {
			JSONObject json= new JSONObject();
			User user=(User)session.getAttribute("user");
			FriendRelation friendrelation=new FriendRelation();
			friendrelation.setFriendid(friendid);
			friendrelation.setUserid(user.getUserid());
			friendrelation.setReceiver_status(1);
			int change_row=this.friendrelationservice.update(friendrelation);
			if(change_row>0) {
				json.put("code",1);
				json.put("msg","操作成功！");
			}
			else {
				json.put("code",0);
				json.put("msg","操作失败！请重试！");}
			return json;	
		}
		//当前用户发送好友申请
		@ResponseBody
		@RequestMapping("/addfriend")
		public JSONObject addfriend(HttpSession session,int friendid,Model model) {
			JSONObject json= new JSONObject();
			User user=(User)session.getAttribute("user");
			FriendRelation friendrelation=new FriendRelation();
			friendrelation.setFriendid(friendid);
			friendrelation.setUserid(user.getUserid());
			friendrelation.setReceiver_status(0);
			int change_row=this.friendrelationservice.requests(friendrelation);
			if(change_row>0) {
				json.put("code",1);
				json.put("msg","好友申请发送成功！");
			}
			else {
				json.put("code",0);
				json.put("msg","操作失败！请重试！");}
				return json;	
			}
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}