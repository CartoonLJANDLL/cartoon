package guomanwang.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import guomanwang.domain.Block;
import guomanwang.domain.Cartoonfile;
import guomanwang.domain.Commit;
import guomanwang.domain.CommitZan;
import guomanwang.domain.Commit_User;
import guomanwang.domain.Message;
import guomanwang.domain.UserCommit;
import guomanwang.domain.UserThread;
import guomanwang.domain.Thread;
import guomanwang.domain.User;
import guomanwang.service.BlockService;
import guomanwang.service.CartoonfileService;
import guomanwang.service.CommitZanService;
import guomanwang.service.MessageService;
import guomanwang.service.ThreadService;
import guomanwang.service.UserCommitService;
import guomanwang.service.UserService;
import guomanwang.service.UserThreadService;
import guomanwang.service.impl.CommitServiceimpl;
import net.sf.json.JSONObject;
@RequestMapping("/commit")
@Controller("CommitController")
public class CommitController {

	@Autowired
	@Qualifier("BlockServiceimpl")
	private BlockService blockService;
	@Autowired
	@Qualifier("CommitServiceimpl")
	private CommitServiceimpl commitServiceimpl;
	@Autowired
	@Qualifier("UserCommitServiceimpl")
	private UserCommitService userCommitService;
	@Autowired
	@Qualifier("UserThreadServiceimpl")
	private UserThreadService userThreadService;
	@Autowired
	@Qualifier("ThreadServiceimpl")
	private ThreadService threadService;
	@Autowired
	@Qualifier("CartoonfileServiceimpl")
	private CartoonfileService cartoonfileService;
	@Autowired
	@Qualifier("CommitZanServiceimpl")
	private CommitZanService commitzanService;
	@Autowired
	@Qualifier("UserServiceimpl")
	private UserService userService;
	@Autowired
	@Qualifier("MessageServiceimpl")
	private MessageService messageservice;
	
	//展示所有评论
	@RequestMapping("/allusercommits")
	public String selectAllUserCommits(int threadId,Model model,HttpSession session) {
		User userinfo=(User)session.getAttribute("user");
		if(userinfo==null) {
			return "redirect:/login";
		}
		else {
			List<UserCommit> userCommits = new ArrayList<UserCommit>();
			UserThread userThread = new UserThread();
			Thread thread=new Thread();
			Thread oldthread=threadService.selectThreadById(threadId);
			thread.setId(threadId);
			thread.setStatus(oldthread.getStatus());
			thread.setUserId(oldthread.getUserId());
			thread.setBlockId(oldthread.getBlockId());
			thread.setGreatNum(oldthread.getGreatNum()+1);
			threadService.updateThread(thread);
			Commit commit=new Commit();
			commit.setThreadId(threadId);
			commit.setUserId(userinfo.getUserid());
			userThread = userThreadService.selectUserThreadByThreadId(threadId);
			userThread.setGrade(userService.getusergradebyuserid(userThread.getUserId()));
			userCommits = userCommitService.selectAllCommitsByThreadId(commit);
			for(int i=0;i<userCommits.size();i++) {
				userCommits.get(i).setGrade(userService.getusergradebyuserid(userCommits.get(i).getUserId()));
			}
			int blockId = userThread.getBlockId();
			Block block=new Block();
			block=blockService.findblockbyid(blockId);
			session.setAttribute("Block",block);
			List<UserThread> hotThreads = new ArrayList<UserThread>();
			hotThreads = userThreadService.selectHotThread(blockId);
			model.addAttribute("userCommits",userCommits);
			model.addAttribute("userThread",userThread);
			model.addAttribute("hotThreads",hotThreads);
			return "thread/tiezi";
		}
		
	}
	
	//发表评论,回复评论
	@ResponseBody
	@RequestMapping("/addcommit")
	public JSONObject addcommit(HttpSession session,String content,int threadId,int userId,int parentId,Model model) {
		System.out.println("经过回复评论模块");
		JSONObject json= new JSONObject();
		Commit commit = new Commit();
		commit.setContent(content);
		commit.setThreadId(threadId);
		commit.setParentId(parentId);
		commit.setUserId(userId);
		Date time=new Date();
		commit.setTime(time);
		User user=userService.getuserbyid(userId);
		User userinfo=new User();
		userinfo.setUserid(user.getUserid());
		userinfo.setHonor(user.getHonor());
		userinfo.setHeadurl(user.getHeadurl());
		userinfo.setGradeValue(user.getGradeValue()+5);
		userinfo.setStatus(1);
		int rs = commitServiceimpl.insertCommit(commit);
		if(rs>0) {
			userService.updateuserinfo(userinfo);
			Message message=new Message();
			Thread thread=threadService.selectThreadById(threadId);
			json.put("code", 1);
			json.put("msg", "评论成功！增加5点经验值");
			if(parentId==0) {				
				message.setReceiverid(thread.getUserId());
				message.setContent("评论了你的帖子<a class='fly-link' href='/guomanwang/commit/allusercommits?threadId="+thread.getId()+"'>"+thread.getTitle()+"</a>");
			}
			else
			{
				Commit replay=commitServiceimpl.selectCommitById(parentId);
				message.setReceiverid(replay.getUserId());
				message.setContent("在帖子<a class='fly-link' href='/guomanwang/commit/allusercommits?threadId="+thread.getId()+"'>"+thread.getTitle()+"</a>中@了你");
			}
			message.setReceiver_status(1);
			message.setTime(new Date());
			message.setSender_status(1);
			message.setSenderid(userId);
			message.setType("帖子");
			this.messageservice.insertMessage(message);
		}
		else {
			json.put("code", 0);
			json.put("msg", "评论失败！");
		}
		return json;
		
	}
	//删除评论
	@ResponseBody
	@RequestMapping("/deletecommit")
	public JSONObject deletecommit(int commitId,Model model) {
		System.out.println("经过删除评论模块"+commitId);
		JSONObject json= new JSONObject();
		int rs = commitServiceimpl.deleteCommitById(commitId);
		if(rs == 1) {
			json.put("msg", "删除成功!");
			json.put("code", 1);
		}else {
			json.put("msg", "删除失败，请重试!");
			json.put("code", 0);
		}
		return json;
	}
	
	//点赞评论
	@ResponseBody
	@RequestMapping("/dianzan")
	public JSONObject dianzan(int commitid,int userid,Model model) {
		System.out.println("经过点赞评论模块");
		JSONObject json= new JSONObject();
		CommitZan commitzan=new CommitZan();
		commitzan.setCommitid(commitid);
		Date time=new Date();
		commitzan.setTime(time);
		commitzan.setUserid(userid);
		int rs = commitzanService.dianzan(commitzan);
		if(rs == 1) {
			json.put("msg", "点赞+1!");
			json.put("code", 1);
		}else {
			json.put("msg", "点赞失败，请重试!");
			json.put("code", 0);
		}
		return json;
	}
	//取消点赞
		@ResponseBody
		@RequestMapping("/cancelzan")
		public JSONObject cancelzan(int commitid,int userid,Model model) {
			System.out.println("经过取消点赞模块");
			JSONObject json= new JSONObject();
			CommitZan commitzan=new CommitZan();
			commitzan.setCommitid(commitid);
			commitzan.setUserid(userid);
			int rs = commitzanService.cancelzan(commitzan);
			if(rs == 1) {
				json.put("msg", "点赞-1!");
				json.put("code", 1);
			}else {
				json.put("msg", "取消点赞失败，请重试!");
				json.put("code", 0);
			}
			return json;
		}
}
