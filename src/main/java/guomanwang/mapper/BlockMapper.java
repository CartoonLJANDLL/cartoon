package guomanwang.mapper;
import java.util.List;

import org.springframework.stereotype.Repository;

import guomanwang.domain.Page;

import guomanwang.domain.Block;

@Repository("BlockMapper")
public interface BlockMapper {
	
	public int deleteBlockById(int id);
	
	public int addblock(Block block);
	
	public int updateBlock(Block block);
	
	public Block selectBlockById(int id);
	
	public List<Block> selectAllBlock();
	
	public List<Block> getblocklist(Page page);
	
	public List<Block> selectHotRanking();
	
	public List<Block> findblockbyname(String name);
	
	public List<Block> selectNewRanking();
	
	public Block findblockbyid(int id);
}
