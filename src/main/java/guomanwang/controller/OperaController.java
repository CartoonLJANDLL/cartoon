package guomanwang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import guomanwang.domain.Opera;
import guomanwang.service.OperaService;

@RequestMapping("/opera")
@Controller("OperaController")
public class OperaController {

	@Autowired
	@Qualifier("OperaServiceimpl")
	private OperaService operaService;
	
	//显示所有番剧
	@RequestMapping("/alloperas")
	public String alloperas(Model model) {
		List<Opera> operas = new ArrayList<Opera>();
		operas = operaService.selectAllOpera();
		return "opera";
	}
	
	//收藏番剧或取消收藏番剧
	@RequestMapping("/collectopera")
	public String collectOpera(int operaId,int userId,Model model) {
		String rs = operaService.nocollectOpera(operaId, userId);
		
		return rs;
	}
	
	//模糊查询番剧
	@RequestMapping("/selectoperabyname")
	public String selectOperaByName(String name,Model model) {
		List<Opera> operas = new ArrayList<Opera>();
		operas = operaService.selectOperaByName(name);
		model.addAttribute("operas", operas);
		return "";
	}
}
