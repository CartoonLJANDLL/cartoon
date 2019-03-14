package guomanwang.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import guomanwang.domain.OpCollected;
import guomanwang.domain.Opera;
import guomanwang.domain.OperaExample;
import net.sf.json.JSONObject;

public interface OperaService {
    //获得番剧数量
	public int getOperaNum();
    //通过番剧id找到相关资讯  
    public Opera getOperaById(int id);
    //通过userid和Operaid找到userOpera
    public OpCollected getUserOpera(int userId,int operaId);
    //显示所有番剧
    JSONObject selectAllOpera(JSONObject param);
    //收藏番剧
    //取消收藏番剧
    public String nocollectOpera(int operaId,int userId);
    //分享番剧
    int shareOpera(int operaid,int userid);
    //根据主键删除
    int deleteByPrimaryKey(Integer id);
    //根据番剧名模糊查询番剧
    JSONObject selectOperaByName(JSONObject param);
    //添加番剧
    public int insertOpera(Opera opera);
    //根据自定义条件选择性更新
    int updateByExampleSelective(@Param("record") Opera record, @Param("example") OperaExample example);
  //通过主键选择性更新 即传入多少值就更新多少值，并且where id =#{id}
    int updateByPrimaryKeySelective(Opera record);
    
    
    
}
