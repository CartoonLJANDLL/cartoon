package guomanwang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import guomanwang.domain.Page;

import guomanwang.domain.Block;
import guomanwang.mapper.BlockMapper;
import guomanwang.service.BlockService;

@Service("BlockServiceimpl")
public class BlockServiceimpl implements BlockService{
	@Autowired
	@Qualifier("BlockMapper")
	private BlockMapper blockMapper;
	@Override
	public List<Block> findblockbyname(String block_name){
		return this.blockMapper.findblockbyname(block_name);
	}
	@Override
	public List<Block> selectAllBlock() {
		return blockMapper.selectAllBlock();
	}
	
	//热度排行的
	@Override
	public List<Block> selectHotRanking() {
		return blockMapper.selectHotRanking();
	}
	@Override
	public int updateBlock(Block blockid) {
			return blockMapper.updateBlock(blockid);	
	}
	@Override
	public List<Block> selectNewRanking() {
		List<Block> blocks = blockMapper.selectNewRanking();
		System.out.println("servicimpl"+blocks.get(0).getName());
		System.out.println("serviceimpl"+blockMapper.selectBlockById(1).getName());
		for(Block block:blocks) {
			int rs = updateBlock(block);
			System.out.println(rs+"update result serviceimpl");
			
		}
		return blockMapper.selectNewRanking();
	}
	@Override
	public Block findblockbyid(int id) {
		return blockMapper.findblockbyid(id);
	}
	@Override
	public List<Block> getblocklist(Page page) {
		return blockMapper.getblocklist(page);
	}
	@Override
	public int addblock(Block block) {
		return blockMapper.addblock(block);
	}
	@Override
	public int deleteBlockById(int id) {
		return blockMapper.deleteBlockById(id);
	}
}
