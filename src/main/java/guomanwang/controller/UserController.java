package guomanwang.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
	
	@RequestMapping("/islogin")
	@ResponseBody
	public JSONObject isLogin(String telnumber, String password, Model model,
			HttpServletRequest request) {
		JSONObject json= new JSONObject();
		List<User> userlist=this.userService.isLogin(telnumber);
		HttpSession session = request.getSession();
		for(User user:userlist) {
			if(user.getPassword().equals(password)) {
				json.put("code",1);
				json.put("msg","登录成功！");
				session.setAttribute("user", user);
				Sign sign=signservice.isSign(user.getUserid());
				if(sign!=null) {
					session.setAttribute("signstatus",1);
					System.out.println(sign);
				}
			}
			else {session.setAttribute("msg", "账号或密码输入错误！");
					json.put("code",0);
					json.put("msg","账号或密码输入错误！请重试！");
			}
		}			
			return json;
		}
	@RequestMapping("/user_index")
	public String user_index(Model model,HttpSession session,
			HttpServletRequest request) {
		User userinfo=(User)session.getAttribute("user");
		if(userinfo==null) {
			return "redirect:/common/login";
		}
		else {
			List<UserThread> threadlist=this.userthreadService.selectMyPushedThread(userinfo.getUserid());
			if(threadlist!=null) {model.addAttribute("threadlist", threadlist);}
				return "user_index";
		}	
	}
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
			return "user_home";
		
	}
	@RequestMapping("/user_setting")
	public String user_setting(HttpServletRequest request){
		HttpSession session = request.getSession();
		List<Defaulthead> defaultheadlist=this.defaultheadService.getallDefaulthead();
		User user=(User)session.getAttribute("user");
		if(user!=null) {
			request.setAttribute("defaultheadlist", defaultheadlist);
			return "user_setting";
		}
		return "redirect:/common/login";
	}
	@RequestMapping("/user_friends")
	public String user_friends(HttpServletRequest request){
		HttpSession session = request.getSession();		
		User user=(User)session.getAttribute("user");
		if(user!=null) {
			List<User> friends=this.friendrelationservice.getfriendsbyuserid(user.getUserid());
			List<User> requestfriends=this.friendrelationservice.getrequestsbyuserid(user.getUserid());
			List<User> askrequests=this.friendrelationservice.getasksbyuserid(user.getUserid());
			request.setAttribute("friends", friends);
			request.setAttribute("requestfriends", requestfriends);
			request.setAttribute("askrequests", askrequests);
			return "user_friends";
		}
		return "redirect:/common/login";
	}
	@RequestMapping("/user_message")
	public String user_message(HttpServletRequest request){
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		if(user==null) {
			return "redirect:/common/login";
		}
		else {
			List<Message> messages=this.messageservice.getmessagesbyreceiverid(user.getUserid());
			request.setAttribute("mymessages", messages);
		}
		
		return "user_message";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception {
	       session.removeAttribute("user");
	       session.invalidate();
	       return "redirect:/common/index";
	   }
	//注册时发送验证码
	@RequestMapping("/sendmsg")
	@ResponseBody
	public JSONObject sendmsg(String cellphone, Model model,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("电话号码为:"+cellphone);
		JSONObject json= new JSONObject();
		List<User> one=this.userService.selectuserinfo(cellphone);
		if(one.size()>0) {
			json.put("code",2);
			json.put("msg","该号码已注册请登录！");
		}
		else {
			String code = this.userService.sendMsg(cellphone);
			System.out.println("验证码为:"+code);
			if (code!="") {
				request.getSession().setAttribute("valiNum",code);
				json.put("code",1);
				json.put("msg","验证码发送成功！");
			} else {
				json.put("code",0);
				json.put("msg","验证码发送失败！请重试！");
			}
		}
		return json;
	}
	//找回密码时发送验证码
		@RequestMapping("/sendcode")
		@ResponseBody
		public JSONObject sendcode(String cellphone, Model model,
				HttpServletRequest request,HttpServletResponse response) throws IOException {
			System.out.println("电话号码为:"+cellphone);
			JSONObject json= new JSONObject();
			List<User> one=this.userService.selectuserinfo(cellphone);
			if(one.size()>0) {
				String code = this.userService.sendMsg(cellphone);
				System.out.println("验证码为:"+code);
				if (code!="") {
					request.getSession().setAttribute("code",code);
					json.put("code",1);
					json.put("msg","验证码发送成功！");
				} else {
					json.put("code",0);
					json.put("msg","验证码发送失败！请重试！");
				}
			}
			else {
				json.put("code",2);
				json.put("msg","你还未注册！");
			}
			return json;
		}
	//用户注册
	@RequestMapping("/register")
	@ResponseBody
	public JSONObject register(String username,String password,String cellphone,String vali,HttpServletRequest request) {
			User user=new User();
			JSONObject json= new JSONObject();
			List<Defaulthead> defaultheadlist=this.defaultheadService.getallDefaulthead();
		if(vali.equals(request.getSession().getAttribute("valiNum"))) {
			//验证码正确后的操作		
			try {
				username = URLDecoder.decode(username,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			int defaultheadid=(int)(Math.random()*(defaultheadlist.size()));
			System.out.println(defaultheadlist.get(defaultheadid).getUrl());
			user.setPhone(cellphone);
			user.setGradeValue(0);
			user.setHonor(1);
			user.setName(username);
			user.setHeadurl(defaultheadlist.get(defaultheadid).getUrl());
			user.setPassword(password);
			int change_row=this.userService.register(user);
			if(change_row>0) {
				json.put("code",1);
				json.put("msg","注册成功！");
			}
			else {
				json.put("code",0);
				json.put("msg","注册失败！请重试！");
			}
		}
		else {
			json.put("code", 0);
			json.put("msg","注册失败！验证码有误！");
		}
			return json;
		
	}
	@RequestMapping("/resetpassword")
	@ResponseBody
	public JSONObject resetpassword(String cellphone,String password,String vali, Model model,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("填写电话号码为:"+cellphone);
		System.out.println("填写的验证码为:"+vali);
		JSONObject json= new JSONObject();
		User user=new User();
		if(vali.equals(request.getSession().getAttribute("code"))) {
			user.setPhone(cellphone);
			user.setPassword(password);
			int change_row=this.userService.resetpassbyphone(user);
			if(change_row>0) {
				json.put("code",1);
				json.put("msg","密码重置成功！可以登录啦！");
			}
			else {
				json.put("code",0);
				json.put("msg","密码重置失败！请重试！");
			}
		}
		else {
			json.put("code",0);
			json.put("msg","密码重置失败！验证码有误！");
		}
		return json;
		
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
		if(password.equals(userinfo.getPassword())) {
			user.setPassword(newpassword);
			user.setPhone(userinfo.getPhone());
			user.setGradeValue(userinfo.getGradeValue());
			user.setHonor(userinfo.getHonor());
			user.setIntroduce(userinfo.getIntroduce());
			user.setSex(userinfo.getSex());
			user.setUserid(userinfo.getUserid());
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
	//上传头像图片
	@RequestMapping("/uploadHeadImage")
	@ResponseBody
	public JSONObject uploadHeadImage(@RequestParam("file") MultipartFile file, @ModelAttribute User user, HttpServletRequest request, InputStream stream)
			throws IOException {
		Assert.notNull(file, "上传文件不能为空");
		HttpSession session = request.getSession();
		String src="/resources/img/people/";
		String path = request.getSession().getServletContext().getRealPath("/"+src);
		//String path="D:/Spring/guomanwang/src/main/webapp/resources/img/people";
		JSONObject json= new JSONObject();
		//System.currentTimeMillis()根据系统时间产生随机数，保证上传的图片名字不一样
		String name=System.currentTimeMillis()+file.getOriginalFilename();
		File dir = new File(path, name);
		src=src+name;
		System.out.println(src);
		if (!dir.exists()) {
			dir.mkdirs();
			json.put("msg","上传成功");
			json.put("code",0);
			json.put("src",src);
		} else {
			json.put("msg","上传失败");
			json.put("code",1);
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