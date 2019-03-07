package guomanwang.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import guomanwang.domain.Block;
import guomanwang.domain.Defaulthead;
import guomanwang.domain.Information;
import guomanwang.domain.Page;
import guomanwang.domain.Thread;
import guomanwang.domain.TimeTransformUtil;
import guomanwang.domain.User;
import guomanwang.domain.UserGrade;
import guomanwang.service.BlockService;
import guomanwang.service.DefaultheadService;
import guomanwang.service.InformationService;
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
	@Qualifier("InformationServiceimpl")
	private InformationService informationService;
	@Autowired
	@Qualifier("DefaultheadServiceimpl")
	private DefaultheadService defaultheadService;
	@Autowired
	@Qualifier("UserGradeServiceimpl")
	private UserGradeService usergradeService;
	
	@RequestMapping("/main")
	public String main(Model model) {
		System.out.println("经过main");
		int threadnumber=this.threadService.getThreadNumber();
		int usernumber=this.userService.getusernumber();
		int newsnumber=this.informationService.getinformationnum();
		model.addAttribute("threadnumber",threadnumber);
		model.addAttribute("usernumber",usernumber);
		model.addAttribute("newsnumber",newsnumber);
		return "main";
	}
	//打印资讯列表
	@RequestMapping("/newslist")
	public String newslist(Model model) {
		System.out.println("经过newslist");
		return "newsList";
	}
	//打印用户列表
	@RequestMapping("/userList")
	public String userList(Model model) {
		System.out.println("经过userList");
		return "userList";
	}
	//管理员设置主页
	@RequestMapping("/adminList")
	public String adminList(Model model) {
		System.out.println("经过adminlist");
		return "adminlist";
	}
	//添加资讯
	@RequestMapping("/addnews")
	public String addnews(Model model) {
		System.out.println("经过addnews");
		return "addnews";
	}
	//用户权限和版主设置
	@RequestMapping("/setUser")
	public String setUser(Model model) {
		List<Block> blocks=this.blockService.selectAllBlock();
		model.addAttribute("blocks",blocks);
		System.out.println("经过setUser");
		return "setUser";
	}
	//等级相关信息设置
	@RequestMapping("/userGrade")
	public String userGrade(Model model) {
		System.out.println("经过userGrade");
		return "userGrade";
	}
	//用户默认头像管理
		@RequestMapping("/images")
		public String userImages(Model model) {
			List<Defaulthead> defaultheadlist=this.defaultheadService.getallDefaulthead();
			System.out.println("经过images");
			model.addAttribute("defaultheadlist",defaultheadlist);
			return "images";
		}
	//更新设置用户权限
	@ResponseBody
	@RequestMapping("/resetUser")
	public JSONObject resetUser(Model model,String userPhone,int gradeValue,int userStatus,int blockId) {
		System.out.println("经过resetUser");
		System.out.println("电话："+userPhone+"等级值："+gradeValue+"状态、身份："+userStatus+"板块编号："+blockId);
		List<User> userlist=this.userService.selectuserinfo(userPhone);
		Block block=this.blockService.findblockbyid(blockId);
		JSONObject jsonobject=new JSONObject();
		int userid=0;
		int threadnum=0;
		for(User user:userlist) {
			 userid=user.getUserid();
		}
			threadnum=block.getThreadnum();
			User userinfo=new User();
			Block block1=new Block();
			userinfo.setGradeValue(gradeValue);
			userinfo.setHonor(userStatus);
			userinfo.setUserid(userid);
			int change_row1=this.userService.updateuserinfo(userinfo);
			if(userStatus==1) {userid=0;}
			block1.setId(blockId);
		    block1.setMaster_id(userid);
		    block1.setThreadnum(threadnum);
		    int change_row=this.blockService.updateBlock(block1);
			if((change_row>0&&change_row1>0)) {
			jsonobject.put("code",1);
			jsonobject.put("msg","用户设置更新成功！");
		}else {
			jsonobject.put("code",0);
			jsonobject.put("msg","用户设置更新失败！");
		}
		return jsonobject;
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
		System.out.println("经过searchnews");
		List<Information> newslist=informationService.searchinformationbytitle(key);
		JSONObject jsonobject= new JSONObject();
		if(newslist!=null) {
			jsonobject.put("code",0);
			jsonobject.put("msg","查询成功！");
			jsonobject.put("count", newslist.size());
			jsonobject.put("data", new Gson().toJson(newslist));
		}
		else jsonobject.put("msg","没有查到结果！");
		System.out.println(jsonobject);
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
				json.put("userName",user.getUsername());
				json.put("userPhone",user.getPhone());
				json.put("userSex",user.getSex());
				json.put("userStatus",user.getHonor());
				json.put("userGrade",user.getGrade());
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
	//根据用户id更新用户权限
	@ResponseBody
	@RequestMapping("/resetuserhonor")
	public JSONObject resetuserhonor(Model model,HttpSession session,int userid) {
		System.out.println("经过resetuserhonor");
		JSONObject json= new JSONObject();
		User userinfo=new User();
		userinfo.setUserid(userid);
		userinfo.setHonor(4);
		int change_row=this.userService.updateuserinfo(userinfo);
		if(change_row>0) {
			json.put("msg","权限更新成功！");
		}
		else {
			json.put("msg","权限更新失败！");
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
			news.setCompanyid(50);
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
