package guomanwang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import guomanwang.domain.Information;
import guomanwang.domain.Opera;
import guomanwang.interceptor.Operation;
import guomanwang.service.OperaService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RequestMapping("/opera")
@Controller("OperaController")
public class OperaController {

	@Autowired
	@Qualifier("OperaServiceimpl")
	private OperaService operaService;
	
	//收藏番剧或取消收藏番剧,json数据类型param 包含userId 和 operaId两个键
	/*@Operation(name="collectopera")*/
	@ResponseBody()
	@RequestMapping("/collectopera")
	public JSONObject collectOpera(String param) {
		/*JSONObject param = new JSONObject();
		Integer operaId = 1;
		Integer userId = 1;
		param.put("operaId", operaId);
		param.put("userId", userId);
		System.out.println("opera/collectopera");*/
	    JSONObject json = JSONObject.fromObject(param); 
		JSONObject jsonobject = operaService.nocollectOpera(json/*param*/);
		
		System.out.println(jsonobject.toString() + " RS");
		return jsonobject;
	}
		
	//根据传进来的JSON数据param所包含的信息 page页码 type番剧类型  status完结状态， sort排序类型
	//传出去的数据都封装在operas中， operas.data是番剧有关信息，若有用户id传入，该番剧被收藏的话，operas.get("data")底下的get(i).getcollecte可获得
	//collecte的值，值为1则已收藏，为0未收藏，模糊查询的同理
	/*@Operation(name="alloperas")*/
	@ResponseBody()
	@RequestMapping("/alloperas")
	public JSONObject alloperas(String param) throws IOException {
		/*JSONObject param = new JSONObject(); 
		Integer page = 1;
		Integer status = null;
		String type  = "";
        param.put("page", page);
		//param.put("status", 1);
		param.put("type", type);
		param.put("sort", "op_time");
		System.out.println(param.toString() + "OOOOO"); 
		param.put("userId", 1);*/
		JSONObject json = JSONObject.fromObject(param);   
		JSONObject operas = operaService.selectAllOpera(json/*param*/);
		//System.out.println("opera/alloperas");
		System.out.println("HHHHH" + operas.get("data").toString());
		
		return operas;
	}
	
	//模糊查询番剧  param可包含 name模糊查询的值，page 页码     看第二页时页码为2 name值也要有  name为空时显示所有的番剧
	@ResponseBody()
	@RequestMapping("/selectoperabyname")
	public JSONObject selectOperaByName(String param) {
		//JSONObject param = new JSONObject();
        //param.put("name", "小");
		JSONObject json = JSONObject.fromObject(param);
		JSONObject jsonobject= new JSONObject();
		
		jsonobject = operaService.selectOperaByName(json/*param*/);
		
		return jsonobject;
	}
	
	//增删改查操作，从管理员的角度思考需要哪些操作，垃圾管理员就是想要更好地管理该网站上的所有资源
	//增加一部番剧
	//传入参数json类型的   包含键 name番剧名字   url番剧地址    desc番剧的一句话描述   photourl番剧的图片地址  updateto更新至  type 类型iframeurl分享地址
	//name url photourl一定要有不然没啥看头，其他的可有可无
	@ResponseBody()
	@RequestMapping("/insertopera")
	public JSONObject insertOpera( String param) {
		JSONObject jsonobject = new JSONObject();
		/*JSONObject param = new JSONObject();
		param.put("name", "小小");
		param.put("url", "www.baidu.com");
		param.put("photourl", "http://jxnu.edu.cn");
		param.put("updateto", "32ji");*/
		JSONObject json = JSONObject.fromObject(param);
		jsonobject = this.operaService.insertOpera(json/*param*/);
		System.out.println("JSONObject" + jsonobject.toString());
		return jsonobject; 
	}
	
	//删除一个番剧，删掉番剧的记录，删掉op_collected表中的记录,根据番剧名name 或者番剧id operaId 番剧url删除
	@ResponseBody()
	@RequestMapping("/deleteopera")
	public JSONObject deleteOpera( String param) {
		/*JSONObject param = new JSONObject();
		param.put("name", "小小");*/
		//int[] operaId = {1,2,3,4};
		
		/*String[] operaId = {"1","2","3"};*/
		//String operaId = "1";
		/*param.put("operaId", operaId);*/
		JSONObject json = JSONObject.fromObject(param);
        JSONObject jsonobject = this.operaService.deleteOperaSelective(json/*param*/);
       /* String op = param.getString("operaId");
        System.out.println("PARA" + param.getString("operaId").toString());
        System.out.println("XIAOXIAO" + jsonobject.toString() + "HHHH" + op.toString());*/
		
		return jsonobject; 
	}
	
	//update一个opera 可更新的字段主要是url 番剧名字  一句话描述  所有字段，只要有值就可以更新
	@ResponseBody()
	@RequestMapping("/updateopera")
	public JSONObject updateOpera( String param) {
		/*JSONObject param = new JSONObject();
		Integer opId = 5;
		param.put("name", "小da");
		param.put("opId", opId);*/
		JSONObject json = JSONObject.fromObject(param);
		System.out.println(json.get("name"));
        JSONObject jsonobject = this.operaService.updateByExampleSelective(/*param*/json);
        System.out.println("XIAOXIAO" + jsonobject.toString());
		
		return jsonobject; 
		
	}
	//查找一个人的所有收藏 参数为userId返回值为operas和code msg
	@ResponseBody()
	@RequestMapping("/personcollectedopera")
	public JSONObject personcollectedopera( String json) {
		/*JSONObject param = new JSONObject();
		param.put("userId",1);
		param.put("limit", 20);*/
		JSONObject param = JSONObject.fromObject(json);
		JSONObject jsonobject = new JSONObject();
		int code = 0;
		String msg = "查找异常！";
		int op_page = 1;
		if( param.has( "op_page")) {
			op_page = param.getInt( "op_page");
		}
		if( param.has("userId") ) {
			int pageSize = this.operaService.getPageSize(param);
			jsonobject = this.operaService.getAllCollectedOpera( param.getInt( "userId"), op_page, pageSize);
		}else {
			jsonobject.put("code", code);
			jsonobject.put("msg", msg);
		}
		System.out.println();
		System.out.println("HHH" + jsonobject.toString());
		return jsonobject;
		
	}
	
}
