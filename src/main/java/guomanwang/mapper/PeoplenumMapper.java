package guomanwang.mapper;

import guomanwang.domain.Peoplenum;
import guomanwang.domain.PeoplenumExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("PeoplenumMapper")
public interface PeoplenumMapper {
    int countByExample(PeoplenumExample example);

    int deleteByExample(PeoplenumExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Peoplenum record);

    int insertSelective(Peoplenum record);

    List<Peoplenum> selectByExample(PeoplenumExample example);
    
    List<Peoplenum> getuseractive();
    
    Peoplenum selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Peoplenum record, @Param("example") PeoplenumExample example);

    int updateByExample(@Param("record") Peoplenum record, @Param("example") PeoplenumExample example);

    int updateByPrimaryKeySelective(Peoplenum record);

    int updateByPrimaryKey(Peoplenum record);
}