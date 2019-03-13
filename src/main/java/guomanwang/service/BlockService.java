package guomanwang.service;

import java.util.List;

import guomanwang.domain.Block;
import guomanwang.domain.Page;

public interface BlockService {
	List<Block> findblockbyname(String block_name);
	public List<Block> selectAllBlock();
	
	public int updateBlock(Block block);
	
	public int addblock(Block block);
	
	public List<Block> selectHotRanking();
	
	public List<Block> selectNewRanking();
	
	public Block findblockbyid(int id);
	
	public List<Block> getblocklist(Page page);
	
	public int deleteBlockById(int id);
}
