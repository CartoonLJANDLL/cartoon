package guomanwang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import guomanwang.domain.Information;
import guomanwang.domain.Opera;
import guomanwang.service.OperaService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RequestMapping("/opera")
@Controller("OperaController")
public class OperaController {

	@Autowired
	@Qualifier("OperaServiceimpl")
	private OperaService operaService;
	
	//根据传进来的JSON数据param所包含的信息 page页码 type番剧类型  status完结状态， sort排序类型
	@RequestMapping("/alloperas")
	public String alloperas(JSONObject param, Model model) throws IOException {
		/*测试用JSONObject param = new JSONObject(); 
		Integer page = 1;
		/*Integer status = null;
		String type  = "";*/
//		param.put("page", page);
		/*param.put("status", 1);
		param.put("type", type);
		param.put("sort", "op_time");*/
		System.out.println(param.toString() + "OOOOO"); 
		
		JSONObject operas = operaService.selectAllOpera(param);
		//System.out.println("opera/alloperas");
		//System.out.println("HHHHH" + operas.get("data").toString());
		model.addAttribute(operas);
		return "opera";
	}
	
	//收藏番剧或取消收藏番剧
	@RequestMapping("/collectopera")
	public String collectOpera(int operaId,int userId,Model model) {
		System.out.println("opera/collectopera");
		String rs = operaService.nocollectOpera(operaId, userId);
		System.out.println(rs + " RS");
		return rs;
	}
	
	//模糊查询番剧  param可包含 name模糊查询的值，page 页码     看第二页时页码为2name值也要有  name为空时显示所有的番剧
	@RequestMapping("/selectoperabyname")
	public String selectOperaByName(JSONObject param, Model model) {
		/*JSONObject param = new JSONObject();
        param.put("name", "小");*/
		JSONObject jsonobject= new JSONObject();
		jsonobject = operaService.selectOperaByName(param);
		
		System.out.println("/opera/selectoperabyname");
		System.out.println(jsonobject.get("data").toString() + "HHHHH");
		model.addAttribute(jsonobject);
		return "jsonobject";
	}
	
}
