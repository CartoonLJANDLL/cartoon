package guomanwang.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	
	//通过userId找到该人收藏的所有op_collected
	public List<OpCollected> getAllCollectedOpera(int userId) {
		OpCollectedExample opcolExample = new OpCollectedExample();
		OpCollectedExample.Criteria opcolcriteria = opcolExample.createCriteria();
		opcolcriteria.andUserIdEqualTo(userId);
		List<OpCollected> opcollecte = this.userOperaMapper.selectByExample(opcolExample);
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
		
		if( param.has("userId")) {
			int userId = param.getInt("userId");
			List<OpCollected> opcollected = getAllCollectedOpera(userId);
			for( int j = 0; j < operas.size(); j++) {
				for ( int i = 0; i < opcollected.size(); i++) {
					if( operas.get(j).getOpId() == opcollected.get(i).getOperaId()) {
						operas.get(j).setCollecte(1);//默认为0，未收藏，标记为1，已收藏
					}
				}
			}
			
		}
		
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
	public JSONObject nocollectOpera(JSONObject param) {
		OpCollected opcollecte = new OpCollected();
		Opera opera = new Opera();
		OpCollectedExample opcolExample = new OpCollectedExample();
		OpCollectedExample.Criteria opcolcriteria = opcolExample.createCriteria();
		JSONObject jsonobject = new JSONObject();
		int code = 0;
		String msg = null;
		System.out.println("PARAM" + param.toString());
		if( param.has("userId") && param.has("operaId")) {
			Integer operaId = param.getInt("operaId");
			Integer userId = param.getInt("userId");
			
			opcolcriteria.andOperaIdEqualTo(operaId);
			opcolcriteria.andUserIdEqualTo(userId);
			
			opera = this.operaMapper.selectByPrimaryKey(operaId);
			
			int collectNum = opera.getOpCollectnum();
			
			if( this.userOperaMapper.selectByExample(opcolExample).size() == 1) {//已收藏
				opcolcriteria.andOperaIdEqualTo(operaId);
				opcolcriteria.andUserIdEqualTo(userId);
				if(this.userOperaMapper.deleteByExample(opcolExample) == 1) {
					//删除成功即取消收藏成功，收藏量减一
					code = 1;
					msg = "取消收藏成功!";
					collectNum--;
					opera.setOpCollectnum(collectNum);
					if(this.operaMapper.updateByPrimaryKeySelective(opera) == 1 ) {//更新收藏量字段成功
						System.out.println("更新Opera表中的collectnum字段成功!");
					}else {
						System.out.println("更新Opera表中的collectnum字段失败!");
					}
					
				}else {//删除失败，即取消收藏失败
					msg = "操作异常!";
				}
			}else {//表中无数据即没有收藏，则插入
				opcollecte.setOperaId(operaId);
				opcollecte.setUserId(userId);
				Date date = new Date();
				/*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String time = dateFormat.format(date);*/
				opcollecte.setCollectedTime(date);
				if(this.userOperaMapper.insert(opcollecte) == 1) {//插入数据 进行收藏成功
					code = 1;
					msg = "收藏成功！";
					collectNum++;
					opera.setOpCollectnum(collectNum);
					if(this.operaMapper.updateByPrimaryKeySelective(opera) == 1 ) {//更新collectnum字段
						System.out.println("更新Opera表中的collectnum字段成功!");
					}else {
						System.out.println("更新Opera表中的collectnum字段失败!");
					}
					
				}else {
					System.out.println("插入数据进行收藏操作失败");
					msg = "操作异常!";
				}
			}
		}else {
			System.out.println("无userid或Operaid");
		}
		jsonobject.put("code", code);
		jsonobject.put("msg", msg);
		return jsonobject;
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
		
		//判断是否收藏
		if( param.has("userId")) {
			int userId = param.getInt("userId");
			List<OpCollected> opcollected = getAllCollectedOpera(userId);
			for( int j = 0; j < operas.size(); j++) {
				for ( int i = 0; i < opcollected.size(); i++) {
					if( operas.get(j).getOpId() == opcollected.get(i).getOperaId()) {
						operas.get(j).setCollecte(1);//默认为0，未收藏，标记为1，已收藏
					}
				}
			}
			
		}
		
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
