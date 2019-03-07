package guomanwang.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import guomanwang.service.BlockService;
import guomanwang.service.SignService;
import guomanwang.service.ThreadService;
import guomanwang.service.UserCommitService;
import guomanwang.service.UserService;
import guomanwang.service.UserThreadService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import guomanwang.domain.Block;
import guomanwang.domain.Thread;
import guomanwang.domain.User;
import guomanwang.domain.UserCommit;
import guomanwang.domain.UserThread;

@RequestMapping("/thread")
@Controller("ThreadController")
public class ThreadController {
	@Autowired
	@Qualifier("ThreadServiceimpl")
	private ThreadService threadService;
	@Autowired
	@Qualifier("BlockServiceimpl")
	private BlockService blockService;
	@Autowired
	@Qualifier("UserThreadServiceimpl")
	private UserThreadService userThreadService;
	
	@Autowired
	@Qualifier("UserCommitServiceimpl")
	private UserCommitService usercommitService;
	@Autowired
	@Qualifier("SignServiceimpl")
	private SignService signservice;
	@Autowired
	@Qualifier("UserServiceimpl")
	private UserService userService;
	
	//发帖模块
	@ResponseBody
	@RequestMapping("/pushthread")
	public JSONObject addthread(int userid,int blockid,String title,String content,@RequestParam(value="status",defaultValue="0") int status,Model model,
			HttpServletRequest request,HttpSession session) {
		System.out.println("经过发帖模块");
		System.out.println(status);
		Thread thread=new Thread();
		JSONObject json= new JSONObject();
		Date time=new Date();
		User user=userService.getuserbyid(userid);
		thread.setBlockId(blockid);
		thread.setUserId(user.getUserid());
		thread.setStatus(status);
		thread.setTitle(title);
		thread.setContent(content);
		thread.setTime(time);
		User userinfo=new User();
		userinfo.setUserid(user.getUserid());
		userinfo.setHonor(user.getHonor());
		userinfo.setHeadurl(user.getHeadurl());
		userinfo.setGradeValue(user.getGradeValue()+10);
		int change_row=this.threadService.addthread(thread);
		if(change_row>0) {		
			json.put("msg","帖子发送成功！增加10点经验值");
			userService.updateuserinfo(userinfo);
		}
		else json.put("msg","帖子发送失败！请重试！");
		return json;
		
		
	}
	//展示版块下的所有帖子
		@RequestMapping("/block_index")
		public String selectAllUserThreads(HttpSession session,HttpServletRequest request,int blockId,@RequestParam(value="searchstatus",defaultValue="0") Integer searchstatus,Model model) {
			User user=(User)session.getAttribute("user");
			if(user!=null) {
				if(searchstatus!=1){
					List<UserThread> userThreads = userThreadService.selectUserThreadsByUserIdBlockId(blockId);
					for(int i=0;i<userThreads.size();i++) {
						userThreads.get(i).setGrade(userService.getusergradebyuserid(userThreads.get(i).getUserId()));
					}
					session.setAttribute("userThreads",userThreads);
				}
				int rs=0;
				List<UserThread> hotThreads = new ArrayList<UserThread>();
				List<UserThread> notices = new ArrayList<UserThread>();
				//右下角热帖
				hotThreads = userThreadService.selectHotThread(blockId);
				notices=userThreadService.selectAllNoticesByBlockId(blockId);
				List<UserCommit> topusers = new ArrayList<UserCommit>();
				topusers = usercommitService.selectTopUserByWeek();
				
				
					rs=signservice.getSignDays(user.getUserid());
					model.addAttribute("signdays",rs);
					
				Block block=new Block();
				block=blockService.findblockbyid(blockId);
				model.addAttribute("map",0);
				session.setAttribute("Block",block);		
				session.setAttribute("users",topusers);
				session.setAttribute("notices", notices);
				model.addAttribute("hotThreads",hotThreads);
				return "block_index";
			}
			return "redirect:/common/login";
		}
		//获得用户连续签到排名前20
		@ResponseBody
		@RequestMapping("/Top20SignUsers")
		public JSONObject getTop20SignUsers(Model model) {
			List<User> userlist=new ArrayList<User>();
			JSONObject jsonobject=new JSONObject();
			JSONArray jsonArray = new JSONArray();
			userlist=userService.getSignTop20Users();
			jsonobject.put("status", 0);
			model.addAttribute("userlist",userlist);
			for(User user:userlist) {
				JSONObject json=new JSONObject();
				json.put("uid",user.getUserid());
				json.put("days",user.getSignDays());
				json.put("username", user.getUsername());
				json.put("avatar", user.getHeadurl());
				jsonArray.add(json);
			}
			jsonobject.put("data", jsonArray);
			return jsonobject;
		}
		//通过关键字模糊搜索
		@ResponseBody
		@RequestMapping("searchtiezi")
		public JSONObject searchtiezi(HttpSession session,HttpServletRequest request,String key,Model model) {
			System.out.println("经过查询方法");
			Block block=(Block) session.getAttribute("Block");
			System.out.println(block.getId()+key);
			UserThread userthread=new UserThread();
			JSONObject json=new JSONObject();
			userthread.setBlockId(block.getId());
			userthread.setTitle(key);
			List<UserThread> searchresult= this.userThreadService.selectUserThreadsBykey(userthread);
			if(searchresult.size()>0) {
				for(int i=0;i<searchresult.size();i++) {
					searchresult.get(i).setGrade(userService.getusergradebyuserid(searchresult.get(i).getUserId()));
				}
				session.setAttribute("userThreads",searchresult);
				json.put("code", 1);
				json.put("msg","查到符合条件的结果!");
			}
			else {
				json.put("code", 0);
				json.put("msg","未查到符合条件的结果!");}
			return json;
		}
		//热议帖子 按热议
		@RequestMapping("/hotuserthread")
		public String hotUserThread(HttpSession session,int blockId,Model model) {
			List<UserThread> userThreads= new ArrayList<UserThread>();
				userThreads = userThreadService.selectHotRankingThread(blockId);
				for(int i=0;i<userThreads.size();i++) {
					userThreads.get(i).setGrade(userService.getusergradebyuserid(userThreads.get(i).getUserId()));
				session.setAttribute("userThreads",userThreads);
			}
			model.addAttribute("map",2);
			session.setAttribute("Block",blockService.findblockbyid(blockId));
			return "block_index";
		}
		
		//精华帖子
		@RequestMapping("/animauserthread")
		public String animaUserThread(HttpSession session,int blockId,Model model) {
			List<UserThread> userThreads= new ArrayList<UserThread>();
				userThreads = userThreadService.selectAnimaThread(blockId);
				for(int i=0;i<userThreads.size();i++) {
				userThreads.get(i).setGrade(userService.getusergradebyuserid(userThreads.get(i).getUserId()));
				}
				session.setAttribute("userThreads",userThreads);
			model.addAttribute("map",1);
			session.setAttribute("Block",blockService.findblockbyid(blockId));
			return "block_index";
		}
		
		//按最新(时间倒序）
		@RequestMapping("/newuserthread")
		public String newUserThread(HttpSession session,int blockId,Model model) {
				List<UserThread> userThreads= new ArrayList<UserThread>();
					userThreads = userThreadService.selectNewRankingThread(blockId);
					for(int i=0;i<userThreads.size();i++) {
						userThreads.get(i).setGrade(userService.getusergradebyuserid(userThreads.get(i).getUserId()));
					}
					session.setAttribute("userThreads",userThreads);
				model.addAttribute("map",3);
				session.setAttribute("Block",blockService.findblockbyid(blockId));
				return "block_index";
		}
		//帖子页面根据帖子id删除帖子
		@ResponseBody
		@RequestMapping("/deletethread")
		public JSONObject deletethread(int id) {
			JSONObject json=new JSONObject();
			int change_row=this.threadService.deleteThreadById(id);
			if(change_row>0) {
				json.put("code",1);
				json.put("msg","成功删除！");
			}
			else {
				json.put("code",0);
				json.put("msg","删除失败，请重试！");
				}
			return json;
			
		}
		//更新帖子状态（加精、置顶）
		@ResponseBody
		@RequestMapping("/updateThread")
		public JSONObject updateThreadById(int id,int status,Model model) {
			Thread thread = new Thread();
			Thread thisthread=new Thread();
			JSONObject json=new JSONObject();
			System.out.println(id+status);
			thisthread=threadService.selectThreadById(id);
			thread.setStatus(status);
			thread.setId(id);
			thread.setBlockId(thisthread.getBlockId());
			thread.setUserId(thisthread.getUserId());
			int result = threadService.updateThread(thread);
			if(result>0) {
				json.put("code", 1);
				json.put("msg","已设置为精华贴");
			}
			else {
				json.put("code", 0);
				json.put("msg","精华贴设置失败");
			}
			return json;
			
		}

		//按热度排行  
		@RequestMapping("/hotranking")
		public String selectHotRanking (int blockId,  Model model) {
			List<UserThread> userThreads = new ArrayList<UserThread>();
			userThreads = userThreadService.selectHotRankingThread(blockId);
			model.addAttribute("threads",userThreads);
			List<UserThread> hotThreads = new ArrayList<UserThread>();
			hotThreads = userThreadService.selectHotThread(blockId);
			System.out.println("hotThreads"+hotThreads.get(0).getContent());
			model.addAttribute("hotThreads",hotThreads);
			return "block_index";
		}
		
		//我发表的帖子
		@RequestMapping("/mypushedthreads")
		public String myPushedThreads(int userId, Model model) {
			List<UserThread> myThreads = new ArrayList<UserThread>();
			myThreads = userThreadService.selectMyPushedThread(userId);
			
			model.addAttribute("userThreads",myThreads);
			
			return "block_index";
		}
		
		
		
		
			
}
