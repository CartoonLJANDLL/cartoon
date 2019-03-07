package guomanwang.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import guomanwang.domain.Cartoonfile;
import guomanwang.domain.CartoonfileExample;
@Repository("CartoonfileMapper")
public interface CartoonfileMapper {
	//自定义计数条件
    int countByExample(CartoonfileExample example);
    //根据自定义条件删除
    int deleteByExample(CartoonfileExample example);

    //根据主键删除文件
    int deleteByPrimaryKey(Integer id);
    //插入文件 所有值都要有，除了time自动生成
    int insert(Cartoonfile record);
    //选择性插入，根据传入的值进行插入,传多少值，插入多少
    int insertSelective(Cartoonfile record);
    //根据自定义条件查询
    List<Cartoonfile> selectByExample(CartoonfileExample example);
    //通过主键查询即id
    Cartoonfile selectByPrimaryKey(Integer id);
    //根据自定义条件选择性更新
    int updateByExampleSelective(@Param("record") Cartoonfile record, @Param("example") CartoonfileExample example);
    //根据自定义条件更新所有
    int updateByExample(@Param("record") Cartoonfile record, @Param("example") CartoonfileExample example);
    //通过主键选择性更新 即传入多少值就更新多少值，并且where id =#{id}
    int updateByPrimaryKeySelective(Cartoonfile record);
    //通过主键更新,所有的六项都要更新
    int updateByPrimaryKey(Cartoonfile record);
}