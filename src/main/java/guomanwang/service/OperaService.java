package guomanwang.service;

import java.util.List;

import guomanwang.domain.OpCollected;
import guomanwang.domain.Opera;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface OperaService {
	//根据自定义条件选择性排序查询，取前十
	public JSONObject operaSortSelective( JSONObject sort) throws Exception;
	//获得几种种类型番剧的数量占比
	public JSONArray operaTypeNumRate( JSONObject param) throws Exception;
    //获得番剧数量
	public int getOperaNum();
	//获得pageSize值
	public int getPageSize(JSONObject param);
    //通过番剧id找到相关资讯  
    public Opera getOperaById(int id);
    //通过userid和Operaid找到userOpera
    public OpCollected getUserOpera(int userId,int operaId);
    //显示所有番剧
    JSONObject selectAllOpera (JSONObject param) throws Exception;
    //收藏番剧
    //取消收藏番剧
    public JSONObject nocollectOpera(JSONObject param);
    //分享番剧
    int shareOpera(int operaid,int userid);
    //根据主键删除
    JSONObject deleteOperaSelective(JSONObject param);
    //根据番剧名模糊查询番剧
    JSONObject selectOperaByName(JSONObject param);
    //添加番剧
    public JSONObject insertOpera(JSONObject param);
    //根据自定义条件选择性更新
    JSONObject updateByExampleSelective(JSONObject param);
  //通过主键选择性更新 即传入多少值就更新多少值，并且where id =#{id}
    int updateByPrimaryKeySelective(Opera record) throws Exception;
    //获得个人收藏中的所有番剧
    public List<OpCollected> getAllOpCollectedOpera(int userId);
    //个人收藏中的所有番剧(分页）
    public JSONObject getAllCollectedOpera( int userId, int op_page, int pageSize);
    public JSONObject getAllCollectedOpera( int userId);
    
    
    
}
