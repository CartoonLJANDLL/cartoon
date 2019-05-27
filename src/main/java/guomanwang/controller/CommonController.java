package guomanwang.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import guomanwang.domain.Block;
import guomanwang.domain.Company;
import guomanwang.domain.Defaulthead;
import guomanwang.domain.Information;
import guomanwang.domain.MD5Cripy;
import guomanwang.domain.Page;
import guomanwang.domain.Sign;
import guomanwang.domain.TimerTask;
import guomanwang.domain.User;
import guomanwang.service.BlockService;
import guomanwang.service.CompanyService;
import guomanwang.service.DefaultheadService;
import guomanwang.service.InformationService;
import guomanwang.service.SignService;
import guomanwang.service.UserService;
import guomanwang.service.UserThreadService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
@RequestMapping("/")
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
	@Autowired
	@Qualifier("UserServiceimpl")
	private UserService userService;
	@Autowired
	@Qualifier("UserThreadServiceimpl")
	private UserThreadService userthreadService;
	@Autowired
	@Qualifier("DefaultheadServiceimpl")
	private DefaultheadService defaultheadService;
	@Autowired
	@Qualifier("SignServiceimpl")
	private SignService signservice;
	
	@RequestMapping("/news")
	public String news(Model model){
		List<Company> companies=companyService.getallcompany();
		model.addAttribute("companies",companies);
		return "common/news";
	}
	@RequestMapping("/opera")
	public String opera(Model model) {
		return "common/opera";
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
		return "common/samecompanynews";
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
			json.put("id", information.getId());
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
		return "common/shownews";
	}
	@Scheduled(cron = "0 30 22 ? * *")//每天22点30启动自动抓取动漫资讯任务
	@RequestMapping("/refreshnews")
	@ResponseBody
	public JSONObject refreshnews() throws IOException, InterruptedException{
		String stringArray[] = {"python3 /usr/local/scary_wawayu.py","python3 /usr/local/scary_xi1.py","python3 /usr/local/scary_rocen.py","python3 /usr/local/scary_chaoshen.py", 
		"python3 /usr/local/scary_cgyear.py","python3 /usr/local/scary_haoliners.py","python3 /usr/local/scary_gamersky.py","python3 /usr/local/scary_qsmy.py"};
		int addition=0;
		JSONObject json=new JSONObject();
		json.put("code",0);
		json.put("msg","成功执行了自动抓取资讯任务，但没有新的资讯加入");
		for(int i=0;i<stringArray.length;i++) {
			System.out.println("开始执行"+stringArray[i]);
			addition=addition+TimerTask.autoscarynews(stringArray[i]);
		}
		if(addition>0){
			json.put("code",1);
			json.put("msg","成功执行了自动抓取资讯任务，添加了"+addition+"条资讯");
		}
		return json;
	}
	 @Scheduled(cron = "0 30 23 ? * *")//每天23点30启动自动抓取动漫视频任务
	@RequestMapping("/refreshOperas")
		public JSONObject refreshOperas() throws IOException, InterruptedException{
			String str = "python3 /usr/local/cartoon.py";
			int addition=0;
			JSONObject json=new JSONObject();
			json.put("code",1);
			json.put("msg","成功执行了自动抓取资讯任务，但没有新的资讯加入");
			System.out.println("开始执行动漫视频抓取任务，后面说的是假的");
			addition=TimerTask.autoscarynews(str);
			
			return json;
		}
	@RequestMapping("/login")
	public String login(){
		return "common/login";
	}
	@RequestMapping("/register")
	public String register(){
		return "common/register";
	}
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	@RequestMapping("/")
	public String redrictindex(){
		return "redirect:index";
	}
	@RequestMapping("/forget")
	public String forget(){
		return "common/forget";
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
		return "common/luntan";
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
		return "common/luntan";
	}
	//点击一下资讯超链接对应得资讯点击量+1
	@RequestMapping("/addviewcount")
	@ResponseBody
	public JSONObject addviewcount(int newsid){
		JSONObject json=new JSONObject();
		Information news=this.informationService.getinformationbyid(newsid);
		news.setViewcount(news.getViewcount()+1);
		json.put("msg","点击量更新失败！");
		if(this.informationService.update(news)>0) {
			json.put("msg","点击量更新成功！");
		}
		return json;
	}
	//用户登录
	@RequestMapping("/islogin")
	@ResponseBody
	public JSONObject isLogin(String telnumber, String password, Model model,
			HttpServletRequest request) {
		JSONObject json= new JSONObject();
		List<User> userlist=this.userService.isLogin(telnumber);
		HttpSession session = request.getSession();
		for(User user:userlist) {
			String passwordByMd5 = MD5Cripy.MD5(password);
			if(user.getPassword().equals(passwordByMd5)&&user.getStatus()==1) {
				json.put("code",1);
				json.put("msg","登录成功！");
				session.setAttribute("user", user);
				Sign sign=signservice.isSign(user.getUserid());
				if(sign!=null) {
					session.setAttribute("signstatus",1);
					System.out.println(sign);
				}
			}
			else if(user.getStatus()==0)
			{
					json.put("code",0);
					json.put("msg","该账号已禁用！请更换账号登录！");
			}
			else {
				json.put("code",0);
				json.put("msg","账号或密码输入错误，请重试！");
			}
		}			
			return json;
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
					json.put("msg","该号码还未注册！请先进行注册");
				}
				return json;
			}
		//用户注册
		@RequestMapping("/zhuce")
		@ResponseBody
		public JSONObject zhuce(String username,String password,String cellphone,String vali,HttpServletRequest request) {
				User user=new User();
				JSONObject json= new JSONObject();
				List<Defaulthead> defaultheadlist=this.defaultheadService.getallDefaulthead();
			if(vali.equals(request.getSession().getAttribute("valiNum"))) {
				//验证码正确后的操作		
				try{
					username = URLDecoder.decode(username,"UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				int defaultheadid=(int)(Math.random()*(defaultheadlist.size()));
				System.out.println(defaultheadlist.get(defaultheadid).getUrl());
				user.setPhone(cellphone);
				user.setGradeValue(0);
				user.setHonor(1);
				user.setStatus(1);
				user.setRegisterday(new Date());
				user.setName(username);
				user.setHeadurl(defaultheadlist.get(defaultheadid).getUrl());
				user.setPassword(MD5Cripy.MD5(password));
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
				user.setPassword(MD5Cripy.MD5(password));
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
}