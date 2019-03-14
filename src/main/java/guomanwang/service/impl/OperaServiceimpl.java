package guomanwang.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import guomanwang.domain.Opera;
import guomanwang.domain.OperaExample;
import guomanwang.domain.OpCollected;
import guomanwang.domain.OpCollectedExample;
import guomanwang.mapper.OperaMapper;
import guomanwang.mapper.OpCollectedMapper;
import guomanwang.service.OperaService;
import net.sf.json.JSONObject;

@Service("OperaServiceimpl")
public class OperaServiceimpl implements OperaService {

	@Autowired
	@Qualifier("OperaMapper")
	private OperaMapper operaMapper;
	OperaExample operaExample = new OperaExample();
	OperaExample.Criteria criteria = operaExample.createCriteria();
	
	@Autowired
	@Qualifier("OpCollectedMapper")
	private OpCollectedMapper userOperaMapper;
	
	//通过userid和Operaid找到userOpera
	public OpCollected getOpCollected(int userId, int operaId) {
		OpCollectedExample opcolExample = new OpCollectedExample();
		OpCollectedExample.Criteria opcolcriteria = opcolExample.createCriteria();
		OpCollected opcollecte = new OpCollected();
		
		opcolcriteria.andUserIdEqualTo(userId);
		opcolcriteria.andOperaIdEqualTo(operaId);
		opcollecte = this.userOperaMapper.selectByExample(opcolExample).get(0);
		return opcollecte;
	}
	//查询所有Opera//根据传进来的JSON数据param所包含的信息 page页码 type番剧类型  status完结状态， sort排序类型
	@Override
	public JSONObject selectAllOpera(JSONObject param) {
		JSONObject jsonobject = new JSONObject();
		OperaExample operaExample = new OperaExample();
		OperaExample.Criteria operacriteria = operaExample.createCriteria();
		jsonobject.put("code",0);
		jsonobject.put("msg","无符合条件的结果");
		Integer op_page = new Integer(1);
		
		if( param.has("page")) {
			op_page = param.getInt("page");
		}
		if( param.has("type") && param.get("type") != "") {
			String op_type = param.getString("type");
			op_type = "%" + op_type + "%";
			operacriteria.andOpTypeLike( op_type);
		}
		if( param.has("sort") && param.get("sort") != "") {
			String sortDESC = "  DESC";//降序
			String sortASC = " ASC";//升序
			String op_sort = param.getString("sort");
			operaExample.setOrderByClause(op_sort  + sortDESC);//设置根据这个字段排序升序  DESC为降序
		}
		if( param.has("status")) {
			Integer op_status = param.getInt("status");
			if( op_status == 1 || op_status == 0) {
				operacriteria.andOpStatusEqualTo(op_status);
			}else {
				System.out.println("STATUS状态值错误，其值仅为1或0");
			}
		}
		operaExample.setDistinct(true);//设置去重

		PageHelper.startPage(op_page,20);//每页20条记录
		List<Opera> operas = operaMapper.selectByExample(operaExample);
		PageInfo<Opera> pageInfo = new PageInfo<>(operas);
		//封装返回
		long total = pageInfo.getTotal(); //获得总条数
		int pages = pageInfo.getPages(); //获得总页数
		if( operas.size() > 0) {
			jsonobject.put("data", operas);
			jsonobject.put("code",1);
			jsonobject.put("msg","查询到符合条件的结果");
			jsonobject.put("count", total);
			jsonobject.put("page", pages);
		}	
		
		return jsonobject;
	}
	
	
	//取消收藏番剧opera  如果收藏了就取消收藏，没收藏则收藏
	@Override
	public String nocollectOpera(int operaId, int userId) {
		OpCollected opcollecte = new OpCollected();
		Opera opera = new Opera();
		
		OpCollectedExample opcolExample = new OpCollectedExample();
		OperaExample operaExample = new OperaExample();
		
		OperaExample.Criteria operacriteria = operaExample.createCriteria();
		OpCollectedExample.Criteria opcolcriteria = opcolExample.createCriteria();
		
		opcolcriteria.andOperaIdEqualTo(operaId);
		opcolcriteria.andUserIdEqualTo(userId);
		operacriteria.andOpIdEqualTo(operaId);
		
		opera = this.operaMapper.selectByPrimaryKey(operaId);
		opcollecte = this.userOperaMapper.selectByExample(opcolExample).get(0);
		
		int collectNum = opera.getOpCollectnum();
		if(opcollecte != null) {
			opcolcriteria.andOperaIdEqualTo(operaId);
			opcolcriteria.andUserIdEqualTo(userId);
			if(this.userOperaMapper.deleteByExample(opcolExample) == 1) {
				operacriteria.andOpIdEqualTo(operaId);
				collectNum++;
				operacriteria.andOpCollectnumEqualTo(collectNum);
				if(this.operaMapper.updateByExampleSelective(opera, operaExample) == 1 ) {
					System.out.println("更新Opera表中的collectnum字段成功!");
				}else {
					System.out.println("更新Opera表中的collectnum字段失败!");
				}
				return "取消收藏成功uncollect";
			}else {
				return "取消收藏失败uncollectfailed";
			}
		}else {
			if(this.userOperaMapper.insert(opcollecte) == 1) {
				operacriteria.andOpIdEqualTo(operaId);
				collectNum--;
				operacriteria.andOpCollectnumEqualTo(collectNum);
				if(this.operaMapper.updateByExampleSelective(opera, operaExample) == 1 ) {
					System.out.println("更新Opera表中的collectnum字段成功!");
				}else {
					System.out.println("更新Opera表中的collectnum字段失败!");
				}
				return "收藏成功collected";
			}else {
				return "收藏失败collectfailed";
			}
		}
	}
		
    //分享番剧
	@Override
	public int shareOpera(int operaid, int userid) {
		OpCollected opcollecte = new OpCollected();
		opcollecte.setOperaId(operaid);
		opcollecte.setUserId(userid);
		int rs = userOperaMapper.insertSelective(opcollecte);
		return rs;
	}
    //根据id删除番剧
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return operaMapper.deleteByPrimaryKey(id);
	}
    //通过番剧名模糊查询
	@Override
	public JSONObject selectOperaByName(JSONObject param) {
		
        Integer op_page = new Integer(1);
		if( param.has("page")) {
			op_page = param.getInt("page");
		}
		if( param.has("name")) {
			String name = param.getString("name");
			name = "%" + name + "%";
			criteria.andOpNameLike(name);
		}
		JSONObject jsonobject= new JSONObject();
		jsonobject.put("code",0);
		jsonobject.put("msg","无符合条件的结果");
		
		PageHelper.startPage(op_page,20);
		List<Opera> operas = this.operaMapper.selectByExample(operaExample);
		PageInfo<Opera> pageInfo = new PageInfo<>(operas);
		long total = pageInfo.getTotal(); //获得总条数
		int pages = pageInfo.getPages(); //获得总页数
		if( operas.size() > 0) {
			jsonobject.put("data", operas);
			jsonobject.put("code",1);
			jsonobject.put("msg","查询到符合条件的结果");
			jsonobject.put("count", total);
			jsonobject.put("page", pages);
		}	
		return jsonobject;
	}
		
    //根据自定义条件选择性更新
	@Override
	public int updateByExampleSelective(Opera record, OperaExample example) {
		
		return this.operaMapper.updateByExampleSelective(record, example);
	}
	//获得Opera数量
	@Override
	public int getOperaNum() {
		OpCollectedExample opcolExample = new OpCollectedExample();
		OpCollectedExample.Criteria opcolcriteria = opcolExample.createCriteria();
		return this.userOperaMapper.countByExample(opcolExample);
	}
	//根据id选择性更新opera，即有的值就更新
	@Override
	public int updateByPrimaryKeySelective(Opera record) {
		// TODO Auto-generated method stub
		return this.operaMapper.updateByPrimaryKeySelective(record);
	}
	//根据id获得Opera
	@Override
	public Opera getOperaById(int id) {
		// TODO Auto-generated method stub
		return this.operaMapper.selectByPrimaryKey(id);
	}
	//插入一部番剧，选择性新增
	@Override
	public int insertOpera(Opera opera) {
		// TODO Auto-generated method stub
		return this.operaMapper.insertSelective(opera);
	}
	

	@Override
	public OpCollected getUserOpera(int userId, int operaId) {
		// TODO Auto-generated method stub
		return null;
	}

}
