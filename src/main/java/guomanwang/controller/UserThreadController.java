package guomanwang.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guomanwang.domain.UserThread;
import guomanwang.service.UserThreadService;
@RequestMapping("/userthread")
@Controller("UserThreadController")
public class UserThreadController {

	@Autowired
	@Qualifier("UserThreadServiceimpl")
	private UserThreadService userThreadService;
	//展示所有帖子  和  右下角热帖
	@RequestMapping("/alluserthreads")
	public String selectAllUserThreads(int blockId,Model model) {
		
		List<UserThread> userThreads = userThreadService.selectUserThreadsByUserIdBlockId(blockId);
		/*System.out.println(userThreads.get(1).getContent());*/
		//右下角热帖
		List<UserThread> hotThreads = new ArrayList<UserThread>();
		hotThreads = userThreadService.selectHotThread(blockId);
/*		System.out.println("hotThreads"+hotThreads.get(0).getContent());*/
		model.addAttribute("userThreads",userThreads);
		model.addAttribute("hotThreads",hotThreads);
		
		return "block_index";
	}
	
        //按热度排行
	@RequestMapping("/userThreadhotranking")
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
	//未接帖子
	@RequestMapping("/unfinisheduserthread")
	public String unfinishedUserThread(int blockId,Model model) {
		List<UserThread> unfinished = new ArrayList<UserThread>();
		unfinished = userThreadService.selectUnfinishThread(blockId);
		model.addAttribute("userThreads",unfinished);
		List<UserThread> hotThreads = new ArrayList<UserThread>();
		hotThreads = userThreadService.selectHotThread(blockId);
		System.out.println("hotThreads"+hotThreads.get(0).getContent());
		model.addAttribute("hotThreads",hotThreads);
		return "block_index";
	}
	
	//已结帖子
	@RequestMapping("/finisheduserthread")
	public String finishedUserThread(int blockId,Model model) {
		List<UserThread> finished = new ArrayList<UserThread>();
		finished = userThreadService.selectFinishedThread(blockId);
		model.addAttribute("userThreads",finished);
		List<UserThread> hotThreads = new ArrayList<UserThread>();
		hotThreads = userThreadService.selectHotThread(blockId);
		System.out.println("hotThreads"+hotThreads.get(0).getContent());
		model.addAttribute("hotThreads",hotThreads);
		return "block_index";
	}
	
	//热议帖子
	@RequestMapping("/hotuserthread")
	public String hotUserThread(int blockId,Model model) {
		List<UserThread> hot = new ArrayList<UserThread>();
		hot = userThreadService.selectHotThread(blockId);
		model.addAttribute("userThreads",hot);
		List<UserThread> hotThreads = new ArrayList<UserThread>();
		hotThreads = userThreadService.selectHotThread(blockId);
		System.out.println("hotThreads"+hotThreads.get(0).getContent());
		model.addAttribute("hotThreads",hotThreads);
		return "block_index";
	}
	
	//精华帖子
	@RequestMapping("/animauserthread")
	public String animaUserThread(int blockId,Model model) {
		List<UserThread> anima = new ArrayList<UserThread>();
		anima = userThreadService.selectAnimaThread(blockId);
		model.addAttribute("userThreads",anima);
		List<UserThread> hotThreads = new ArrayList<UserThread>();
		hotThreads = userThreadService.selectHotThread(blockId);
		System.out.println("hotThreads"+hotThreads.get(0).getContent());
		model.addAttribute("hotThreads",hotThreads);
		return "block_index";
	}
	
	//按最新
	@RequestMapping("/newuserthread")
	public String newUserThread(int blockId,Model model) {
		List<UserThread> newthread = new ArrayList<UserThread>();
		newthread = userThreadService.selectAnimaThread(blockId);
		model.addAttribute("userThreads",newthread);
		List<UserThread> hotThreads = new ArrayList<UserThread>();
		hotThreads = userThreadService.selectHotThread(blockId);
		System.out.println("hotThreads"+hotThreads.get(0).getContent());
		model.addAttribute("hotThreads",hotThreads);
		return "block_index";
	}
		
		
		
		
}
