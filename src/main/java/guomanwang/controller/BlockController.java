package guomanwang.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import guomanwang.domain.Block;
import guomanwang.domain.User;
import guomanwang.interceptor.Operation;
import guomanwang.service.BlockService;


@RequestMapping("/block")
@Controller("BlockController")
public class BlockController {

	@Autowired
	@Qualifier("BlockServiceimpl")
	private BlockService blockService;
	
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
