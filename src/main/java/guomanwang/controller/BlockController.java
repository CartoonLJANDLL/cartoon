package guomanwang.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import guomanwang.domain.Block;
import guomanwang.interceptor.Operation;
import guomanwang.service.BlockService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@RequestMapping("/block")
@Controller("BlockController")
public class BlockController {

	@Autowired
	@Qualifier("BlockServiceimpl")
	private BlockService blockService;
	@ResponseBody()
	@RequestMapping("/threadnumranking")
	public JSONObject dataSeeBlockThreadnum(/*JSONObject param*/) {
		List<Block> blocks = new ArrayList<Block>();
		JSONObject returnJSON = new JSONObject();
		JSONArray xTitle = new JSONArray();
		JSONArray yNum = new JSONArray();
		blocks=blockService.selectHotRanking();
		int n = blocks.size();
		/*if( param.has("blocknum")) {
			n= param.getInt( "blocknum");
		}*/
		for( int i = 0; i < n; i++) {
			xTitle.add( blocks.get(i).getName());
			yNum.add( blocks.get(i).getThreadnum());
		}
		returnJSON.put("name", xTitle);
		returnJSON.put("y", yNum);

		System.out.println(blocks.getClass().getName()+"controller");
		return returnJSON;
	}
	
	@Operation(name="查询所有版块")
	@RequestMapping("/queryAll")
	public String viewAllBlock(Model model) {		
		List<Block> blocks = new ArrayList<Block>();	
		blocks=blockService.selectAllBlock();
		/*System.out.println(blockService.findblockid("天行九歌"));*/
		model.addAttribute("blocks", blocks);
		System.out.println(blocks.getClass().getName()+"controller");
		return "redirect:/common/luntan";
	}
	
	//热度排行，按帖子数量
	@RequestMapping("/hotranking")
	public String selectHotRanking(Model model) {
		List<Block> blocks = new ArrayList<Block>();
		blocks=blockService.selectHotRanking();
		/*System.out.println(blockService.findblockid("天行九歌"));*/
		model.addAttribute("blocks", blocks);

		System.out.println(blocks.getClass().getName()+"controller");
		return "luntan";
	}
	//按最新
	@RequestMapping("/newranking")
	public String selectNewRanking(Model model) {
		List<Block> blocks = new ArrayList<Block>();	
		blocks=blockService.selectNewRanking();
		/*System.out.println(blockService.findblockid("天行九歌"));*/
		model.addAttribute("blocks", blocks);

		System.out.println(blocks.getClass().getName()+"controller");
		return "luntan";
		
	}
}
