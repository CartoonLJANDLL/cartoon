package guomanwang.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;

import guomanwang.domain.Block;
import guomanwang.domain.Company;
import guomanwang.domain.Information;
import guomanwang.domain.Page;
import guomanwang.domain.TimeTransformUtil;
import guomanwang.domain.TimerTask;
import guomanwang.domain.User;
import guomanwang.service.BlockService;
import guomanwang.service.CompanyService;
import guomanwang.service.InformationService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
@RequestMapping("/common")
@Controller
public class CommonController {
	@Autowired
	@Qualifier("BlockServiceimpl")
	private BlockService blockService;
	@Autowired
	@Qualifier("CompanyServiceimpl")
	private CompanyService companyService;
	@Autowired
	@Qualifier("InformationServiceimpl")
	private InformationService informationService;
	
	@RequestMapping("/register")
	public String register(){
		System.out.println("CommonController.register");
		return "register";
	}
	@RequestMapping("/news")
	public String news(Model model){
		List<Company> companies=companyService.getallcompany();
		model.addAttribute("companies",companies);
		return "news";
	}
	@RequestMapping("/opera")
	public String opera(Model model) {
		return "opera";
	}
	//根据关键字实现资讯的模糊搜索
	@ResponseBody
	@RequestMapping("/searchnews")
	public JSONObject searchnews(String key) {
		System.out.println("经过searchnews");
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonobject= new JSONObject();
		jsonobject.put("code",0);
		jsonobject.put("msg","未查询到符合条件的结果");
		List<Information> newslist=informationService.searchinformationbytitle(key);
		if(newslist.size()>0) {
			for(Information information:newslist) {
				JSONObject json= new JSONObject();
				json.put("title",information.getTitle());
				json.put("time",information.getTime());
				json.put("url",information.getUrl());
				json.put("company",information.getCompanyname());
				jsonArray.add(json);
			}
			jsonobject.put("code",1);
			jsonobject.put("msg","查询到符合条件的结果");
			jsonobject.put("count", newslist.size());
			jsonobject.put("data", jsonArray);
		}

		System.out.println(jsonobject);
		return jsonobject;
	}
	@RequestMapping("/samecompany")
	public String samecompany(Model model,int companyid) {
		Company company=companyService.getcompanybyid(companyid);
		model.addAttribute("company",company);
		return "samecompanynews";
	}
	@ResponseBody
	@RequestMapping("/samecompanynews")
	public JSONObject samecompanynews(Model model,Page one,int companyid,int page) {
		System.out.println("经过samecompanynews流加载方法");
		one.setRows(20);
		JSONArray jsonArray = new JSONArray();
		one.setOtherid(companyid);
		one.setPage(page);
		List<Information> newslist=informationService.selectinformationbycompany(one);
		for(Information information:newslist) {
			JSONObject json= new JSONObject();
			json.put("title",information.getTitle());
			json.put("time",information.getTime());
			json.put("url",information.getUrl());
			json.put("company",information.getCompanyname());
			jsonArray.add(json);
		}
		JSONObject jsonobject= new JSONObject();
		jsonobject.put("code",0);
		jsonobject.put("msg","");
		jsonobject.put("pages", informationService.getnumberbycompany(companyid)/20+1);
		jsonobject.put("data", jsonArray);
		System.out.println(jsonobject);
		return jsonobject;
	}
	@RequestMapping("/shownews")
	public String shownews(Model model){
		List<Information> news=informationService.getnewinformation();
		model.addAttribute("news",news);
		return "shownews";
	}
	@Scheduled(cron = "0 30 22 ? * *")//每天22点30启动自动抓取动漫资讯任务
	@RequestMapping("/reflash")
	public String reflash() throws IOException, InterruptedException{
		String stringArray[] = {"python3 /usr/local/scary_wawayu.py","python3 /usr/local/scary_xi1.py","python3 /usr/local/scary_rocen.py","python3 /usr/local/scary_chaoshen.py", 
		"python3 /usr/local/scary_cgyear.py","python3 /usr/local/scary_haoliners.py","python3 /usr/local/scary_gamersky.py","python3 /usr/local/scary_qsmy.py"};
		for(int i=0;i<stringArray.length;i++) {
			System.out.println("开始执行"+stringArray[i]);
			TimerTask.autoscarynews(stringArray[i]);
		}
		return "news";
	}
	@RequestMapping("/add")
	public String add(Model model,HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		System.out.println("经过发帖页面");
		User userinfo=(User)session.getAttribute("user");
		List<Block> blocks=this.blockService.selectAllBlock();
		if(userinfo==null) {
			return "login";
		}
		model.addAttribute("blocks",blocks);
		return "add";
	}
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	@RequestMapping("/tiezi")
	public String tiezi(){
		return "tiezi";
	}
	@RequestMapping("/forget")
	public String forget(){
		return "forget";
	}
	@RequestMapping("/luntan")
	public String luntan(@RequestParam(value="pn",defaultValue="1") int pn,
			Model model,HttpSession session) {
		PageHelper.startPage(pn,4);
		List<Block> blocks = blockService.selectAllBlock();
		PageInfo<Block> p=new PageInfo<Block>(blocks); 
		model.addAttribute("blocks", blocks);
		model.addAttribute("page",p);
		session.setAttribute("searchstatus",0);
		session.setAttribute("status",0);
		System.out.println("经过了论坛方法");
		return "luntan";
	}
	@RequestMapping("/luntan_hot")
	public String luntan_hot(@RequestParam(value="pn",defaultValue="1") int pn,
			Model model,HttpSession session) {
		PageHelper.startPage(pn,4);
		List<Block> blocks = blockService.selectHotRanking();
		PageInfo<Block> p=new PageInfo<Block>(blocks); 
		model.addAttribute("blocks", blocks);
		model.addAttribute("page",p);
		session.setAttribute("status",1);
		System.out.println("经过了论坛热度排行方法");
		return "luntan";
	}
	@RequestMapping("/admin")
	public String admin(HttpSession session){
		User userinfo=(User)session.getAttribute("user");
		//用户未登录跳转到登录页面
		if(userinfo==null) {
			return "redirect:login";
		}
		//用户不是管理员则跳转到首页
		else if(userinfo.getHonor()<=2) {
			return "redirect:index";
		}
		return "admin_index";
	}
	@RequestMapping("/jump/{path}")
	public String jump(@PathVariable("path") String path){
		return path;
	}
	@RequestMapping("/user/{path}")
	public String user(@PathVariable("path") String path){
		return "/user/"+path;
	}
	@RequestMapping("/thread/{path}")
	public String thread(@PathVariable("path") String path){
		return "/thread/"+path;
	}
	@RequestMapping("/admin/{path}")
	public String admin(@PathVariable("path") String path){
		return "/admin/"+path;
	}
}