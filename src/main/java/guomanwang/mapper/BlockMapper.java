package guomanwang.mapper;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;

import guomanwang.domain.Block;

@Repository("BlockMapper")
public interface BlockMapper {
	
	public int deleteBlockById(int id);
	
	public int insertBlock(Block block);
	public int addblock(Block block);
	
	public int updateBlock(Block block);
	
	public Block selectBlockById(int id);
	
	public List<Block> selectAllBlock();
	
	public List<Block> selectHotRanking();
	
	public List<Block> findblockbyname(String name);
	
	public List<Block> selectNewRanking();
	
	public Block findblockbyid(int id);
}
