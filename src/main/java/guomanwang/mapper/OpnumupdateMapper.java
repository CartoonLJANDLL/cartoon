package guomanwang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import guomanwang.domain.Opnumupdate;
import guomanwang.domain.OpnumupdateExample;
@Repository("OpnumupdateMapper")
public interface OpnumupdateMapper {
    int countByExample(OpnumupdateExample example);

    int deleteByExample(OpnumupdateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Opnumupdate record);

    int insertSelective(Opnumupdate record);

    List<Opnumupdate> selectByExample(OpnumupdateExample example);

    Opnumupdate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Opnumupdate record, @Param("example") OpnumupdateExample example);

    int updateByExample(@Param("record") Opnumupdate record, @Param("example") OpnumupdateExample example);

    int updateByPrimaryKeySelective(Opnumupdate record);

    int updateByPrimaryKey(Opnumupdate record);
}