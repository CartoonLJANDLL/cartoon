package guomanwang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import guomanwang.domain.Access;
import guomanwang.domain.AccessExample;
@Repository("AccessMapper")
public interface AccessMapper {
    int countByExample(AccessExample example);

    int deleteByExample(AccessExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Access record);

    int insertSelective(Access record);

    List<Access> selectByExampleWithBLOBs(AccessExample example);

    List<Access> selectByExample(AccessExample example);

    Access selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Access record, @Param("example") AccessExample example);

    int updateByExampleWithBLOBs(@Param("record") Access record, @Param("example") AccessExample example);

    int updateByExample(@Param("record") Access record, @Param("example") AccessExample example);

    int updateByPrimaryKeySelective(Access record);

    int updateByPrimaryKeyWithBLOBs(Access record);
}