package guomanwang.service;

import java.util.List;

import guomanwang.domain.Block;

public interface BlockService {
	List<Block> findblockbyname(String block_name);
	public List<Block> selectAllBlock();
	
	public int updateBlock(Block block);
	
	public List<Block> selectHotRanking();
	
	public List<Block> selectNewRanking();
	
	public Block findblockbyid(int id);
}
