package guomanwang.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import guomanwang.domain.Block;
import guomanwang.domain.Defaulthead;
import guomanwang.domain.Information;
import guomanwang.domain.MD5Cripy;
import guomanwang.domain.Page;
import guomanwang.domain.PeoplenumExample;
import guomanwang.domain.Thread;
import guomanwang.domain.User;
import guomanwang.domain.UserGrade;
import guomanwang.service.BlockService;
import guomanwang.service.CompanyService;
import guomanwang.service.DefaultheadService;
import guomanwang.service.InformationService;
import guomanwang.service.OperaService;
import guomanwang.service.PeoplenumService;
import guomanwang.service.ThreadService;
import guomanwang.service.UserGradeService;
import guomanwang.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RequestMapping("/admin")
@Controller
public class AdminController {
	@Autowired
	@Qualifier("BlockServiceimpl")
	private BlockService blockService;
	@Autowired
	@Qualifier("ThreadServiceimpl")
	private ThreadService threadService;
	@Autowired
	@Qualifier("UserServiceimpl")
	private UserService userService;
	@Autowired
	@Qualifier("OperaServiceimpl")
	private OperaService operaService;
	@Autowired
	@Qualifier("InformationServiceimpl")
	private InformationService informationService;
	@Autowired
	@Qualifier("DefaultheadServiceimpl")
	private DefaultheadService defaultheadService;
	@Autowired
	@Qualifier("UserGradeServiceimpl")
	private UserGradeService usergradeService;
	@Autowired
	@Qualifier("PeoplenumServiceimpl")
	private PeoplenumService peoplenumService;
	@Autowired
	@Qualifier("CompanyServiceimpl")
	private CompanyService companyService;
	
	@RequestMapping("/index")
	public String admin(HttpSession session){
		User userinfo=(User)session.getAttribute("user");
		//用户未登录跳转到登录页面
		if(userinfo==null) {
			return "redirect:/login";
		}
		//用户不是管理员则跳转到首页
		else if(userinfo.getHonor()<=2) {
			return "redirect:/common/index";
		}
		return "admin/admin_index";
	}
	@RequestMapping("/main")
	public String main(Model model) {
		System.out.println("经过main");
		int threadnumber=this.threadService.getThreadNumber();
		int usernumber=this.userService.getusernumber();
		int newsnumber=this.informationService.getinformationnum();
		model.addAttribute("threadnumber",threadnumber);
		model.addAttribute("usernumber",usernumber);
		model.addAttribute("newsnumber",newsnumber);
		return "admin/main";
	}
	//打印资讯列表
	@RequestMapping("/newslist")
	public String newslist(Model model) {
		System.out.println("经过newslist");
		return "admin/newsList";
	}
	//返回用户数据展示页面
	@RequestMapping("/luntandata")
	public String luntandata() {
		return "admin/luntandata";
	}
	//返回用户数据展示页面
	@RequestMapping("/userdata")
	public String userdata() {
		return "admin/userdata";
	}
	//返回用户数据展示页面
	@RequestMapping("/newsdata")
	public String newsdata() {
		return "admin/newsdata";
	}
	//返回用户数据展示页面
	@RequestMapping("/operadata")
	public String operadata() {
		return "admin/operadata";
	}
	//返回后台番剧管理页面
	@RequestMapping("/operaList")
	public String operalist(Model model) {
		System.out.println("经过operalist");
		return "admin/operaList";
	}
	//打印用户列表
	@RequestMapping("/userList")
	public String userList(Model model) {
		System.out.println("经过userList");
		return "admin/userList";
	}
	//管理员设置主页
	@RequestMapping("/adminList")
	public String adminList(Model model) {
		System.out.println("经过adminlist");
		return "admin/adminlist";
	}
	//添加资讯
	@RequestMapping("/addnews")
	public String addnews(Model model) {
		System.out.println("经过addnews");
		return "admin/addnews";
	}
	//添加番剧
	@RequestMapping("/addopera")
	public String addopera(Model model) {
		System.out.println("经过addopera");
		return "admin/addopera";
	}
	//板块列表
	@RequestMapping("/blockList")
	public String blockset(Model model) {
		System.out.println("经过addnews");
		return "admin/blockList";
	}
	//用户权限和版主设置
	@RequestMapping("/setUser")
	public String setUser(Model model) {
		List<Block> blocks=this.blockService.selectAllBlock();
		model.addAttribute("blocks",blocks);
		System.out.println("经过setUser");
		return "admin/setUser";
	}
	//用户权限和版主设置
	@RequestMapping("/setBlock")
	public String setBlock(Model model) {
		List<User> users=this.userService.getallusers();
		model.addAttribute("users",users);
		System.out.println("经过setBlock");
		return "admin/setBlock";
	}
	//等级相关信息设置
	@RequestMapping("/userGrade")
	public String userGrade(Model model) {
		System.out.println("经过userGrade");
		return "admin/userGrade";
	}
	//用户默认头像管理
		@RequestMapping("/images")
		public String userImages(Model model) {
			List<Defaulthead> defaultheadlist=this.defaultheadService.getallDefaulthead();
			System.out.println("经过images");
			model.addAttribute("defaultheadlist",defaultheadlist);
			return "admin/images";
		}
	//获得每日新增注册用户数据
	@ResponseBody
	@RequestMapping("/getregisterdata")
	public JSONObject getregisterdata(@RequestParam(value="startdate",defaultValue="2019-04-01")String startdate,@RequestParam(value="enddate",defaultValue="2019-04-25")String enddate) {
		JSONArray jsonarray=new JSONArray();
		JSONArray jsonarray1=new JSONArray();
		JSONObject json=new JSONObject();
		List<User> counters= this.userService.getregistercount(startdate,enddate);
		for(User user:counters) {
			jsonarray.add(user.getRegisterday().substring(0,10));
			jsonarray1.add(user.getSignDays());
		}
		json.put("x",jsonarray);
		json.put("y",jsonarray1);
		return json;
	}
	//获得近一个月的网站pv和uv数据
	@ResponseBody
	@RequestMapping("/getmonthpvuv")
	public JSONObject getmonthpvuv() {
		return this.userService.getmonthpvuv();
	}
	//获得用户活跃时间和停留时间数据
	@ResponseBody
	@RequestMapping("/getuseractive")
	public JSONObject getuseractive() throws Exception {
		PeoplenumExample example=new PeoplenumExample();
		return this.peoplenumService.selectuseractive(example);
	}
	//获得资讯来源网站或公司的分组数据
	@ResponseBody
	@RequestMapping("/getcompanycount")
	public JSONArray getcompanycount() throws Exception {
		return this.companyService.getcompanycount();
	}
	//获得所有资讯的标题
	@ResponseBody
	@RequestMapping("/getinformationtitle")
	public JSONObject getinformationtitle() throws Exception {
		return this.informationService.getinformationtitle();
	}
	//获得注册用户的性别比例
	@ResponseBody
	@RequestMapping("/getsexcount")
	public JSONObject getsexcount(){
		return this.userService.getsexcount();
	}
	//获得点击量前十的资讯,预留参数接口，可以自定义获取点击量前多少名的资讯
	@ResponseBody
	@RequestMapping("/gettopviewnews")
	public JSONObject gettopviewnews(@RequestParam(value="limit",defaultValue="10")int limit){
		return this.informationService.gettopviewnews(10);
	}
	//更新设置用户权限或者增加一个新用户
	@ResponseBody
	@RequestMapping("/resetUser")
	public JSONObject resetUser(Model model,int userid,String username,@RequestParam(value="sex",defaultValue="保密")String sex,String userPhone,int gradeValue,int userStatus,@RequestParam(value="blockId",defaultValue="0") int blockId) {
		System.out.println("经过resetUser");
		JSONObject json=new JSONObject();
		User user=new User();
		user.setGradeValue(gradeValue);
		user.setHonor(userStatus);
		user.setName(username);
		user.setPhone(userPhone);
		user.setSex(sex);
		user.setStatus(1);
		if(userid!=0) {
			user.setUserid(userid);
			int change=this.userService.updateuserinfo(user);
			if(change>0&&blockId!=0) {
				Block block=new Block();
				block.setMaster_id(userid);
				block.setId(blockId);
				this.blockService.updateBlock(block);
			}
			else if(change>0&&blockId==0) {
				json.put("code", 1);
				json.put("msg","用户信息更新成功");
			}
			else if(change==0) {
				json.put("code", 0);
				json.put("msg","用户信息更新失败");
			}
		}
		else {
			user.setPassword(MD5Cripy.MD5("123456"));
			user.setStatus(1);
			user.setRegisterday(new Date());
			int row=this.userService.register(user);
			if(row>0&&blockId!=0) {
				Block block=new Block();
				block.setMaster_id(userid);
				block.setId(blockId);
				this.blockService.updateBlock(block);
			}
			else if(row>0&&blockId==0) {
				json.put("code", 1);
				json.put("msg","成功添加一个新用户！");
			}
			else if(row==0) {
				json.put("code", 0);
				json.put("msg","新增用户失败1");
			}
		}
		return json;
	}
	//获得所有资讯信息
	@ResponseBody
	@RequestMapping("/getallnews")
	public JSONObject getallnews(Model model,Page page,@RequestParam("limit") int limit) {
		System.out.println("经过getallnews");
		JSONArray jsonArray = new JSONArray();
	    page.setRows(limit);
		List<Information> newslist=informationService.getinformation(page);
		for(Information information:newslist) {
			JSONObject json= new JSONObject();
			json.put("id",information.getId());
			json.put("title",information.getTitle());
			json.put("time",information.getTime());
			json.put("url",information.getUrl());
			json.put("collectnum",information.getCollectnum());
			json.put("company",information.getCompanyname());
			jsonArray.add(json);
		}
		JSONObject jsonobject= new JSONObject();
		jsonobject.put("code",0);
		jsonobject.put("msg","");
		jsonobject.put("count", informationService.getinformationnum());
		jsonobject.put("data", jsonArray);
		System.out.println(jsonobject);
		return jsonobject;
	}	
	//后端获得番剧分页信息
	@ResponseBody
	@RequestMapping("/getoperalist")
	public JSONObject getoperalist(int page,@RequestParam("limit") int limit) throws Exception {
		JSONObject param=new JSONObject();
		param.put("page",page);
		param.put("limit",limit);
		JSONObject operas = operaService.selectAllOpera(param);
		operas.put("code", 0);
		operas.put("msg", "");
		return operas;
	}
	//后端获得番剧查询结果
	@ResponseBody
	@RequestMapping("/searchoperasbyname")
	public JSONObject searchoperasbyname(int page,@RequestParam("limit") int limit,String key) {
		JSONObject param=new JSONObject();
		param.put("page",page);
		param.put("limit",limit);
		param.put("name",key);
		JSONObject operas = operaService.selectOperaByName(param);
		operas.put("code", 0);
		operas.put("msg", "");
		return operas;
	}
	//番剧类型占比 搞笑 青春 科幻 校园 其他 历史 推理 冒险","剧情","恋爱,其他在所有的都算完后用总数减
	//前端传值optypes（默认为上面）为所要看的番剧类型占比，optypenum（默认为10）为所要看的类型数量，都要封装在一个jsonobject中，都含有默认值
	@ResponseBody()
	@RequestMapping("/operatypenumrate")
	public JSONArray operaPlaynumRate(/*JSONObject param*/) throws Exception{
		JSONArray jsonObject = new JSONArray();
		JSONArray jsonArrayType = new JSONArray();
		JSONObject param = new JSONObject();
		int n = 10;
		System.out.println("HHHHHHHHHHHHH");
		String[] opTypes = {"搞笑","青春","科幻","校园","历史","推理","冒险","剧情","恋爱","其他"}; 
		if( param.has( "optypenum")) {
			n = param.getInt( "optypenum");
		}else {
			param.put("optypenum", n);
		}System.out.println("HHHHHHHHHHHHH");
		if( !param.has( "optypes")) {
			for( int i = 0; i < param.getInt( "optypenum"); i++) {
				jsonArrayType.add( opTypes[i]);
				System.out.println("HHHHHHHHHHHHH" + jsonArrayType.getString(i));
			}
			System.out.println("HHHHHHHHHHHHH" + jsonArrayType.getString(0));
			param.put("optypes", jsonArrayType);
		}
		System.out.println("HHHHHHHHHHHHH4" + param.get("optypes").toString());
		jsonObject = this.operaService.operaTypeNumRate( param);
		
		return jsonObject;
	}
		//番剧点击量op_playnum或者op_collectnum降序排序前端json数据sort包含{"sort","点击量"}或者{"sort","收藏量"}
		//默认点击量排序
		@ResponseBody()
		@RequestMapping("/clicknumsort")
		public JSONObject operaClicknumSort(/*JSONObject sort*/) throws Exception{
			JSONObject jsonObject = new JSONObject();
			/*if( sort.has( "sort")) {
				if( "点击量".equals( sort.getString("sort"))) {
					sort.put("sort", "op_playnum");
				}else {
					if( "收藏量".equals( sort.getString("sort"))) {
						sort.put("sort", "op_collectnum");
					}
				}
			}else {
				sort.put("sort", "op_playnum");
			}*/
			JSONObject sort = new JSONObject();
			sort.put("sort", "op_playnum");
			sort.put("startdate", "2019-05-01");
			sort.put("enddate", "2019-06-05");
			System.out.println("tiantian");
			jsonObject = this.operaService.operaSortSelective(sort);
			System.out.println("===============hhhhhhhhh");
			return jsonObject;
		}
		@ResponseBody()
		@RequestMapping("/collectnumsort")
		public JSONObject operaCollectnumSort(/*JSONObject sort*/) throws Exception{
			JSONObject jsonObject = new JSONObject();
			/*if( sort.has( "sort")) {
				if( "点击量".equals( sort.getString("sort"))) {
					sort.put("sort", "op_playnum");
				}else {
					if( "收藏量".equals( sort.getString("sort"))) {
						sort.put("sort", "op_collectnum");
					}
				}
			}else {
				sort.put("sort", "op_playnum");
			}*/
			JSONObject sort = new JSONObject();
			sort.put("sort", "op_collectnum");
			sort.put("startdate", "2019-05-01");
			sort.put("enddate", "2019-06-05");
			System.out.println("tiantian");
			jsonObject = this.operaService.operaSortSelective(sort);
			System.out.println("===============hhhhhhhhh");
			return jsonObject;
		}
		
		
	//获得最新的10条帖子
		@ResponseBody
		@RequestMapping("/getnewthread")
		public JSONObject getnewthread(Model model) {
			System.out.println("经过getnewthread");
			JSONArray jsonArray = new JSONArray();
			List<Thread> threadnewlist=this.threadService.getnewThread();
			for(Thread thread:threadnewlist) {
				JSONObject json= new JSONObject();
				json.put("id",thread.getId());
				json.put("title",thread.getTitle());
				json.put("time",thread.getTime());
				json.put("collectnum",thread.getContent());
				jsonArray.add(json);
			}
			JSONObject jsonobject= new JSONObject();
			jsonobject.put("code",0);
			jsonobject.put("msg","");
			jsonobject.put("count", threadnewlist.size());
			jsonobject.put("data", jsonArray);
			System.out.println(jsonArray);
			return jsonobject;
		}
	//获得所有用户信息
		@ResponseBody
		@RequestMapping("/getuserlist")
		public JSONObject getuserlist(Model model,Page page,@RequestParam("limit") int limit) {
			System.out.println("经过getuserlist");
			JSONArray jsonArray = new JSONArray();
			page.setRows(limit);
			List<User> userlist=userService.getuserlist(page);
			for(User user:userlist) {
				JSONObject json= new JSONObject();
				json.put("Id",user.getUserid());
				json.put("userName",user.getUsername());
				json.put("userPhone",user.getPhone());
				json.put("userSex",user.getSex());
				json.put("userStatus",user.getHonor());
				json.put("status",user.getStatus());
				json.put("gradeValue",user.getGradeValue());
				jsonArray.add(json);
			}
			JSONObject jsonobject= new JSONObject();
			jsonobject.put("code",0);
			jsonobject.put("msg","");
			jsonobject.put("count", userlist.size());
			jsonobject.put("data", jsonArray);
			System.out.println(jsonobject);
			return jsonobject;
		}
		//获得所有主题板块列表
		@ResponseBody
		@RequestMapping("/getblocklist")
		public JSONObject getblocklist(Model model,Page page,@RequestParam("limit") int limit) {
			System.out.println("经过getblocklist");
			JSONArray jsonArray = new JSONArray();
			page.setRows(limit);
			List<Block> blocklist=this.blockService.getblocklist(page);
			for(Block block:blocklist) {
				JSONObject json= new JSONObject();
				json.put("Id",block.getId());
				json.put("Name",block.getName());
				json.put("Photo",block.getphoto());
				json.put("Threadnum",block.getThreadnum());
				json.put("Last_time",block.getlast_time());
				json.put("Abstracts",block.getAbstracts());
				json.put("masterid",block.getMaster_id());
				if(block.getMaster_id()!=0) {
					User user=this.userService.getuserbyid(block.getMaster_id());
					json.put("User",user.getUsername());
				}
				else json.put("User","暂无");
				jsonArray.add(json);
			}
			JSONObject jsonobject= new JSONObject();
			jsonobject.put("code",0);
			jsonobject.put("msg","");
			jsonobject.put("count", blocklist.size());
			jsonobject.put("data", jsonArray);
			System.out.println(jsonobject);
			return jsonobject;
		}
		//获得所有管理员信息
		@ResponseBody
		@RequestMapping("/getadminlist")
		public JSONObject getadminlist(Model model,Page page,@RequestParam("limit") int limit) {
			System.out.println("经过getadminlist");
			JSONArray jsonArray = new JSONArray();
			page.setRows(limit);
			List<User> userlist=userService.getadminlist(page);
			for(User user:userlist) {
				JSONObject json= new JSONObject();
				json.put("Id",user.getUserid());
				json.put("userName",user.getUsername());
				json.put("userPhone",user.getPhone());
				json.put("userSex",user.getSex());
				json.put("userStatus",user.getHonor());
				json.put("status",user.getStatus());
				json.put("gradeValue",user.getGradeValue());
				jsonArray.add(json);
				}
			JSONObject jsonobject= new JSONObject();
			jsonobject.put("code",0);
			jsonobject.put("msg","");
			jsonobject.put("count", userlist.size());
			jsonobject.put("data", jsonArray);
			System.out.println(jsonobject);
			return jsonobject;
		}
	//根据关键字实现资讯的模糊搜索
	@ResponseBody
	@RequestMapping("/searchnews")
	public JSONObject searchnews(String key) {
		JSONObject jsonobject= new JSONObject();
		jsonobject=informationService.searchinformationbytitle(key);
		jsonobject.put("code",0);
		return jsonobject;
	}
	//根据关键字实现用户的的模糊搜索
		@ResponseBody
		@RequestMapping("/searchuser")
		public JSONObject searchuser(String key) {
			System.out.println("经过searhuser");
			List<User> userlist=this.userService.searchuserbyname(key);
			JSONObject jsonobject= new JSONObject();
			JSONArray jsonArray = new JSONArray();
			if(userlist!=null) {
			for(User user:userlist) {
				JSONObject json= new JSONObject();
				json.put("Id",user.getUserid());
				json.put("userName",user.getUsername());
				json.put("userPhone",user.getPhone());
				json.put("userSex",user.getSex());
				json.put("userStatus",user.getHonor());
				json.put("status",user.getStatus());
				json.put("gradeValue",user.getGradeValue());
				jsonArray.add(json);
				}
			jsonobject.put("code",0);
			jsonobject.put("msg","");
			jsonobject.put("count", userlist.size());
			jsonobject.put("data", jsonArray);
			}
			else jsonobject.put("msg","没有查到结果！");
			System.out.println(jsonobject);
			return jsonobject;
		}
		//根据关键字实现用户的的模糊搜索
		@ResponseBody
		@RequestMapping("/searchadmin")
		public JSONObject searchadmin(String key) {
			System.out.println("经过searhadmin");
			List<User> userlist=this.userService.searchadminbyname(key);
			JSONObject jsonobject= new JSONObject();
			JSONArray jsonArray = new JSONArray();
			if(userlist!=null) {
			for(User user:userlist) {
				JSONObject json= new JSONObject();
				json.put("Id",user.getUserid());
				json.put("userName",user.getUsername());
				json.put("userPhone",user.getPhone());
				json.put("userSex",user.getSex());
				json.put("userStatus",user.getHonor());
				json.put("status",user.getStatus());
				json.put("gradeValue",user.getGradeValue());
				jsonArray.add(json);
				}
			jsonobject.put("code",0);
			jsonobject.put("msg","");
			jsonobject.put("count", userlist.size());
			jsonobject.put("data", jsonArray);
			}
			else jsonobject.put("msg","没有查到结果！");
			System.out.println(jsonobject);
			return jsonobject;
		}
	//根据newsid删除资讯  
	@ResponseBody
	@RequestMapping("/deletenews")
	public JSONObject deletenews(Model model,int id) {
		System.out.println("经过deletenews");
		JSONObject json= new JSONObject();
		int change_row=informationService.deleteInformationById(id);
		if(change_row>0) {
			json.put("code", 1);
			json.put("msg", "资讯删除成功！");
		}
		else {
			json.put("msg", "资讯删除失败！");
			json.put("code", 0);
		}	
		return json;
	}
	//根据blockid删除版块  
		@ResponseBody
		@RequestMapping("/deleteblock")
		public JSONObject deleteblock(Model model,int id) {
			System.out.println("经过deleteblock");
			JSONObject json= new JSONObject();
			int change_row=this.blockService.deleteBlockById(id);
			if(change_row>0) {
				json.put("code", 1);
				json.put("msg", "版块删除成功！");
			}
			else {
				json.put("msg", "版块删除失败！");
				json.put("code", 0);
			}	
			return json;
		}
	//根据newsid数组实现批量删除资讯  
		@ResponseBody
		@RequestMapping("/deletemorenews")
		public JSONObject deletemorenews(Model model,String[] ids) {
			System.out.println("经过deletemorenews");
			System.out.println(ids);
			JSONObject json= new JSONObject();
			int change_row=0;
			try{
				change_row=informationService.deletemanynewsById(ids);
				}catch(Exception e){
				//异常处理
				}
			if(change_row>0) {
				json.put("code", 1);
				json.put("msg", "资讯删除成功！");
			}
			else {
				json.put("msg", "资讯删除失败！");
				json.put("code", 0);
			}	
			return json;
		}
	//根据用户id更新用户状态
	@ResponseBody
	@RequestMapping("/updateuser")
	public JSONObject resetuserhonor(Model model,HttpSession session,int userid,int status) {
		JSONObject json= new JSONObject();
		User user=this.userService.getuserbyid(userid);
		user.setStatus(status);
		int change_row=this.userService.updateuserinfo(user);
		if(change_row>0) {
			json.put("code",1);
			json.put("msg","用户状态更新成功！");
		}
		else {
			json.put("code",0);
			json.put("msg","用户状态更新失败！");
		}
		return json;
	}
	
	//根据用户id删除用户
	@ResponseBody
	@RequestMapping("/deleteuser")
	public JSONObject deleteuser(Model model,HttpSession session,int userid) {
		System.out.println("经过deleteuser");
		JSONObject json= new JSONObject();
		int change_row=this.userService.deleteUserById(userid);
		if(change_row>0) {
			json.put("msg","用户删除成功！");
		}
		else {
			json.put("msg","用户删除失败！");
		}
		return json;
	}
	//新增一条资讯
	@ResponseBody
	@RequestMapping("/newsAdd")
	public JSONObject newsAdd(HttpSession session,String title,String content,String status,String abstracts) {
		System.out.println("经过newsAdd");
		JSONObject json= new JSONObject();
		Information news=new Information();
		news.setTitle(title);
		news.setContent(content);
		news.setUrl(abstracts);
		news.setStatus(status);
		news.setTime(new Date());
		news.setCollectinum(0);
		news.setCompany(50);
		int change_row=informationService.addinformation(news);
		if(change_row>0) {
			json.put("code", 1);
			json.put("msg", "添加资讯成功！");
		}
		else {
			json.put("code", 0);
			json.put("msg", "添加资讯失败！");
		}
		return json;
			
	}
	//新增一个版块主题
	@ResponseBody
	@RequestMapping("/addblock")
	public JSONObject addblock(int id,String title,int masterid,String photo,String abstracts) {
		System.out.println("经过addblock");
		JSONObject json= new JSONObject();
		Block block=new Block();
		block.setName(title);
		block.setAbstracts(abstracts);
		block.setMaster_id(masterid);
		block.setphoto(photo);
		if(id!=0) {
			block.setId(id);
			int change=this.blockService.updateBlock(block);
			if(change>0) {
				if(masterid!=0) {
					User user=this.userService.getuserbyid(masterid);
					user.setHonor(2);
					this.userService.updateuserinfo(user);
				}
				json.put("code", 1);
				json.put("msg", "主题版块信息更新成功！");
			}
			else {
				json.put("code", 0);
				json.put("msg", "主题版块信息更新失败！");
			}
		}
		else {
			int row=this.blockService.addblock(block);
			if(row>0) {
				if(masterid!=0) {
					User user=this.userService.getuserbyid(masterid);
					user.setHonor(2);
					this.userService.updateuserinfo(user);
				}
				json.put("code", 1);
				json.put("msg", "成功添加新的主题版块！");
			}
			else {
				json.put("code", 0);
				json.put("msg", "增加主题版块失败，请重试！");
			}
		}
		return json;
			
	}
		//获得所有默认头像信息
		@ResponseBody
		@RequestMapping("/getalldefaultimages")
		public JSONObject getalldefaultimages(Model model) {
			System.out.println("经过getalldefaultimages");
			List<Defaulthead> defaultheadlist=this.defaultheadService.getallDefaulthead();
			JSONObject jsonobject= new JSONObject();
			jsonobject.put("title","默认头像管理");
			jsonobject.put("id","Images");
			jsonobject.put("start", 0);
			jsonobject.put("data",new Gson().toJson(defaultheadlist));
			System.out.println(jsonobject);
			return jsonobject;
		}
		//根据图片id删除数据库的图片
		@ResponseBody
		@RequestMapping("/deleteimage")
		public JSONObject deletedefaulthead(String imageid) {
			System.out.println("经过deletedefaulthead");
			System.out.println(imageid);
			JSONObject json= new JSONObject();
			int change_row=this.defaultheadService.deleteDefaultheadById(Integer.parseInt(imageid));
			if(change_row>0) {
				json.put("msg", "图片删除成功！");
			}
			else {json.put("msg", "图片删除失败！");}
			return json;
		}
		//上传默认头像图片(多文件)
		@RequestMapping("/uploadimages")
		@ResponseBody
		public JSONObject uploadimages(@RequestParam("file") MultipartFile file, HttpServletRequest request, InputStream stream)
				throws IOException {
			Assert.notNull(file, "上传文件不能为空");
			String src="/resources/img/default/";
			String path = request.getSession().getServletContext().getRealPath("/"+src);
			JSONObject json= new JSONObject();
			Defaulthead image=new Defaulthead();
			//System.currentTimeMillis()根据系统时间产生随机数，保证上传的图片名字不一样
			String name=System.currentTimeMillis()+file.getOriginalFilename();
			File dir = new File(path, name);
			src=src+name;
			if (!dir.exists()) {
				dir.mkdirs();
				json.put("msg","上传成功");
				json.put("code",0);
				json.put("src",src);
				file.transferTo(dir);
				image.setUrl(src);
				image.setStatus("启用");
				this.defaultheadService.addDefaulthead(image);
			} else {
				json.put("msg","上传失败");
				json.put("code",1);
			}
			
			
			System.out.println("经过多图片上传方法");
			System.out.println(dir);
			return json;
		}
		//根据imageid数组实现批量删除图片  
				@ResponseBody
				@RequestMapping("/deletemoreimages")
				public JSONObject deletemoreimages(Model model,String[] ids) {
					System.out.println("经过deletemoreimages");
					System.out.println(ids);
					JSONObject json= new JSONObject();
					int change_row=0;
					try{
						change_row=this.defaultheadService.deletemanyimagesById(ids);
						}catch(Exception e){
						//异常处理
						}
					if(change_row>0) {
						json.put("code", 1);
						json.put("msg", "图片删除成功！");
					}
					else {
						json.put("msg", "图片删除失败！");
						json.put("code", 0);
					}	
					return json;
				}
				//获得所有等级信息
				@ResponseBody
				@RequestMapping("/getusergrade")
				public JSONObject getusergrade(Model model,HttpSession session) {
					System.out.println("经过getusergrade");
					JSONObject json= new JSONObject();
					List<UserGrade> usergradelist=this.usergradeService.getUserGrade();
						json.put("code",0);
						json.put("count",usergradelist.size());
						json.put("msg","");
						json.put("data",new Gson().toJson(usergradelist));
					return json;
				}
				//获得所有等级信息
				@ResponseBody
				@RequestMapping("/updateusergrade")
				public JSONObject updateusergrade(Model model,HttpSession session,int id,String gradeIcon,String gradeName,String gradeValue,String gradeGold,String gradePoint,String gradeStatus) {
					System.out.println("经过updateusergrade");
					JSONObject json= new JSONObject();
					UserGrade usergrade=new UserGrade();
					usergrade.setId(id);
					usergrade.setGradeIcon(gradeIcon);
					usergrade.setGradeName(gradeName);
					usergrade.setGradeStatus(gradeStatus);
					usergrade.setGradeValue(gradeValue);
					int change_row=this.usergradeService.updateUserGrade(usergrade);
					if(change_row>0) {
						json.put("code",1);
						json.put("msg","更新成功！");
					}
					else {
						json.put("code",0);
						json.put("msg","更新失败！");
					}
					return json;
				}
				//新增一个等级
				@ResponseBody
				@RequestMapping("/addUserGrade")
				public JSONObject addUserGrade(Model model,int id,String gradeIcon,String gradeName,String gradeValue,String gradeGold,String gradePoint,String gradeStatus) {
					System.out.println("经过addUserGrade");
					JSONObject json= new JSONObject();
					UserGrade usergrade=new UserGrade();
					usergrade.setId(id);
					usergrade.setGradeIcon(gradeIcon);
					usergrade.setGradeName(gradeName);
					usergrade.setGradeStatus(gradeStatus);
					usergrade.setGradeValue(gradeValue);
					int change_row=this.usergradeService.addUserGrade(usergrade);
					if(change_row>0) {
						json.put("code",1);
						json.put("msg","添加成功！");
					}
					else {
						json.put("code",0);
						json.put("msg","添加失败！");
					}
					return json;
				}
}
