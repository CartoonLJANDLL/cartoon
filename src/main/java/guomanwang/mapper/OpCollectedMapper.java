package guomanwang.mapper;

import guomanwang.domain.OpCollected;
import guomanwang.domain.OpCollectedExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("OpCollectedMapper")
public interface OpCollectedMapper {
	
    int countByExample(OpCollectedExample example);

    int deleteByExample(OpCollectedExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OpCollected record);

    int insertSelective(OpCollected record);

    List<OpCollected> selectByExample(OpCollectedExample example);

    OpCollected selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OpCollected record, @Param("example") OpCollectedExample example);

    int updateByExample(@Param("record") OpCollected record, @Param("example") OpCollectedExample example);

    int updateByPrimaryKeySelective(OpCollected record);

    int updateByPrimaryKey(OpCollected record);
    
}