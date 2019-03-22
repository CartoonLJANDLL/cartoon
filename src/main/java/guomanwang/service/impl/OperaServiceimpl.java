package guomanwang.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import guomanwang.domain.Opera;
import guomanwang.domain.OperaExample;
import guomanwang.domain.OpCollected;
import guomanwang.domain.OpCollectedExample;
import guomanwang.mapper.OperaMapper;
import guomanwang.mapper.OpCollectedMapper;
import guomanwang.service.OperaService;
import net.sf.json.JSONArray;
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
	int pageSize = 18;
	//页面大小limit
	public int getPageSize( JSONObject param) {
		if( param.has( "limit"))
			pageSize = param.getInt( "limit");
		return pageSize;
	}
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
	public List<OpCollected> getAllOpCollectedOpera(int userId) {
		OpCollectedExample opcolExample = new OpCollectedExample();
		OpCollectedExample.Criteria opcolcriteria = opcolExample.createCriteria();
		opcolcriteria.andUserIdEqualTo(userId);
		List<OpCollected> opcollecte = this.userOperaMapper.selectByExample(opcolExample);
		return opcollecte;
		
	}
	//通过Opera的id找到Opera
	public JSONObject getAllCollectedOpera(int userId, int op_page, int pageSize){
		
		List<Opera> operas = new ArrayList<Opera>();
		PageHelper.startPage(op_page,pageSize);
		operas = this.operaMapper.selectCollectOpera(userId);
		PageInfo<Opera> pageInfo = new PageInfo<>(operas);
		JSONObject jsonobject = new JSONObject();
		jsonobject.put("code", 0);
		jsonobject.put("msg", "未查询到收藏结果!");
		
		//封装返回
		long count = pageInfo.getTotal(); //获得总条数
		int pages = pageInfo.getPages(); //获得总页数
		if( operas.size() > 0) {
			jsonobject.put("data", operas);
			jsonobject.put("code",1);
			jsonobject.put("msg","查询到收藏");
			jsonobject.put("count", count);
			jsonobject.put("page", pages);
		}	
		return jsonobject;
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
			String sort1 = "最新";
			String sort2 = "最热";
			String op_sort1 = "op_time";
			String op_sort2 = "op_collectnum";
			
			if( sort1.equals( param.getString("sort"))) {
				operaExample.setOrderByClause(op_sort1  + sortDESC);
			}
			if( sort2.equals( param.getString( "sort"))) {
				operaExample.setOrderByClause( op_sort2 + sortDESC);
			}
			 
			//设置根据这个字段排序升序  DESC为降序
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

		PageHelper.startPage(op_page,getPageSize(param));//每页18条记录
		List<Opera> operas = operaMapper.selectByExample(operaExample);
		PageInfo<Opera> pageInfo = new PageInfo<>(operas);
		
		if( param.has("userId")) {
			int userId = param.getInt("userId");
			List<OpCollected> opcollected = getAllOpCollectedOpera(userId);
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
	
	//通过番剧名模糊查询
	@Override
	public JSONObject selectOperaByName(JSONObject param) {
		OperaExample operaExample = new OperaExample();
		OperaExample.Criteria operacriteria = operaExample.createCriteria();
        Integer op_page = new Integer(1);
		if( param.has("page")) {
			op_page = param.getInt("page");
		}
		if( param.has("name")) {
			String name = param.getString("name");
			name = "%" + name + "%";
			System.out.println("NAME" + name);
			operacriteria.andOpNameLike(name);
		}
		JSONObject jsonobject= new JSONObject();
		jsonobject.put("code",0);
		jsonobject.put("msg","无符合条件的结果");
		
		PageHelper.startPage(op_page,getPageSize(param));
		List<Opera> operas = this.operaMapper.selectByExample(operaExample);
		PageInfo<Opera> pageInfo = new PageInfo<>(operas);
		long total = pageInfo.getTotal(); //获得总条数
		int pages = pageInfo.getPages(); //获得总页数
		
		//判断是否收藏
		if( param.has("userId")) {
			int userId = param.getInt("userId");
			List<OpCollected> opcollected = getAllOpCollectedOpera(userId);
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
			
	//取消收藏番剧opera  如果收藏了就取消收藏，没收藏则收藏
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
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
			if( opera == null) {
				System.out.println("不存在该番剧！");
				msg = "该番剧不存在!";
			}else {
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
	//解析传过来的字符串为int数组
	public List<Integer> getStringToInt( String a) {
		List<Integer> b = new ArrayList<Integer>();
		String[] op = a.split( "\\D");
	    for( int i = 0; i < op.length; i++) {
	    	if( !"".equals( op[i])) {
	    		b.add( Integer.parseInt( op[i].trim()));
	    	}
	    }
	    
		return b;
	}
    //根据传入的选择性传入的参数删除 name operaId 返回参数code 为0 操作异常回滚  为1Opera表删除成功  为2 opera和op_collected表删除成功
	//msg为String  解释
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public JSONObject deleteOperaSelective(JSONObject param) {
		JSONObject jsonobject= new JSONObject();
		OpCollectedExample opcolExample = new OpCollectedExample();
		OpCollectedExample.Criteria opcolCriteria = opcolExample.createCriteria();
		
		int code = 0;
		String msg = "操作失败，未改变数据库";
		
		//传入参数是operId
		if( param.has("operaId")) {
			String operaId = param.getString("operaId");
			List<Integer> opIds = getStringToInt( operaId);
			for( int i = 0; i < opIds.size(); i++) {
				int opId = opIds.get(i);
				Opera opera = this.operaMapper.selectByPrimaryKey( opId);
				int rs = this.operaMapper.deleteByPrimaryKey( opId);
				if( rs > 0 && opera != null) {
					msg = "删除opera表中数据成功！";
					code = 1;
					jsonobject.put("operaResultSet", rs);
					opcolCriteria.andOperaIdEqualTo( opId);
					List<OpCollected> opcol = this.userOperaMapper.selectByExample(opcolExample);
					int opcolrs = this.userOperaMapper.deleteByExample( opcolExample); //op_collected表中删除掉的数据条数 
					if( opcolrs > 0) {
						code = 2;
						jsonobject.put("opcollectedRS", opcolrs);
						msg = msg + "删除op_collected表中数据成功！";
					}else {
						if( opcol.size() > 0)
							msg = msg + "删除op_collected表中数据失败!回滚操作，未改变数据库！";
					}
				}else {
					if( opera == null) {
						msg =msg + "数据库中无此条记录" + i;
					}else {
						msg = "删除opera表中数据失败！回滚操作，未改变数据库！";
						jsonobject.put("code", code);
						jsonobject.put("msg", msg);
						return jsonobject;
					}
				}
			}
		}
		//传入参数是name
		if( param.has("name")) {
			OperaExample operaExample = new OperaExample();
			OperaExample.Criteria operaCriteria = operaExample.createCriteria();
			operaCriteria.andOpNameEqualTo( param.getString("name"));
			if( this.operaMapper.selectByExample(operaExample).isEmpty()) {
				msg =msg + "数据库中无此条记录" + param.getString("name");
				code = 1;
			}else {
				Opera opera = this.operaMapper.selectByExample(operaExample).get(0);
				int rs = this.operaMapper.deleteByExample(operaExample);
				if( rs > 0 ) {
					jsonobject.put("operaResultSet", rs);
					msg = "删除opera表中数据成功！";
					code = 1;
					opcolCriteria.andOperaIdEqualTo( opera.getOpId());
					List<OpCollected> opcol = this.userOperaMapper.selectByExample(opcolExample);
					int opcolrs = this.userOperaMapper.deleteByExample( opcolExample); //op_collected表中删除掉的数据条数 
					if( opcolrs > 0) {
						code = 2;
						jsonobject.put("opcollectedRS", opcolrs);
						msg = msg + "删除op_collected表中数据成功！";
					}else {
						if( opcol.size() > 0)
							msg = msg + "删除op_collected表中数据失败!回滚操作，未改变数据库！";
					}
				}else {
					msg = "删除opera表中数据失败！回滚操作，未改变数据库！";
					jsonobject.put("code", code);
					jsonobject.put("msg", msg);
					return jsonobject;
				}
			}
			
		}
		jsonobject.put("code", code);
		jsonobject.put("msg", msg);
		return jsonobject;
	}
    

	
	//获得Opera数量
	@Override
	public int getOperaNum() {
		OpCollectedExample opcolExample = new OpCollectedExample();
		OpCollectedExample.Criteria opcolcriteria = opcolExample.createCriteria();
		return this.userOperaMapper.countByExample(opcolExample);
	}
	//根据id选择性更新opera，即有的值就更新
	
	//根据id获得Opera
	@Override
	public Opera getOperaById(int id) {
		// TODO Auto-generated method stub
		return this.operaMapper.selectByPrimaryKey(id);
	}
	//插入一部番剧，选择性新增,code=0失败  code = 1 成功 msg返回插入成功与否信息
	//传入参数json类型的   包含键 name番剧名字   url番剧地址    desc番剧的一句话描述   photourl番剧的图片地址  updateto更新至  type 类型iframeurl分享地址
	//name url photourl一定要有不然没啥看头，其他的可有可无
	@Override
	public JSONObject insertOpera(JSONObject param) {
		JSONObject jsonobject= new JSONObject();
		Opera opera = new Opera();
		OperaExample operaExample = new OperaExample();
		OperaExample.Criteria operaCriteria = operaExample.createCriteria();
		jsonobject.put("code",0);
		jsonobject.put("msg","添加番剧失败！");
		//封装成opera传值过来
		if( param.has("opera")) {
			opera = (Opera) param.get("opera");
			operaCriteria.andOpNameEqualTo( opera.getOpName());
			if( this.operaMapper.selectByExample(operaExample).size() == 0) {
				int rs = this.operaMapper.insertSelective(opera);
				if( rs == 1) {
					jsonobject.put("code", 1);
					jsonobject.put("msg", "添加番剧成功！");
					return jsonobject;
				}
			}else {
				jsonobject.put("msg", "番剧已存在，添加失败!");
			}
			
		}
		//未封装成opera传过来
		if( param.has( "name")) {
			opera.setOpName( param.getString("name"));
			operaCriteria.andOpNameEqualTo( opera.getOpName());
			if( this.operaMapper.selectByExample(operaExample).size() == 0) {
				if( param.has( "url")) {
					opera.setOpUrl( param.getString("url"));
					if( param.has( "photourl")) {
						opera.setOpPhotourl(param.getString( "photourl"));
						if( param.has("desc")) {
							opera.setOpDesc( param.getString( "desc"));
						}
						if( param.has("type")) {
							opera.setOpType( param.getString( "type"));
						}
						if( param.has("iframeurl")) {
							opera.setOpIframeurl( param.getString( "iframeurl"));
						}
						if( param.has( "updateto")) {
							String updateto = param.getString( "updateto");
							if( updateto.contains("全")) {
								opera.setOpStatus(1);
							}else {
								opera.setOpStatus(0);
							}
							opera.setOpUpdateto( updateto);
						}
						Date date = new Date();
						opera.setOpTime(date);
						int rs = this.operaMapper.insertSelective(opera);
						if( rs == 1) {
							jsonobject.put("code", 1);
							jsonobject.put("msg", "添加番剧成功！");
							return jsonobject;
						}
					}
				}
			}
			
		}
		return jsonobject;
	}
	
	@Override
	public OpCollected getUserOpera(int userId, int operaId) {
		// TODO Auto-generated method stub
		return null;
	}
    //根据自定义条件选择性更新 封装好的opera 或者单独的各个参数
	@Override
	public JSONObject updateByExampleSelective(JSONObject param) {
		JSONObject jsonobject = new JSONObject();
		OperaExample operaExample = new OperaExample();
		OperaExample.Criteria operacriteria = operaExample.createCriteria();
		Opera opera = new Opera();
		jsonobject.put("code",0);
		jsonobject.put("msg","更新失败!");
		System.out.println(param.toString() + "OOOOOOOOOOOOOOO");
		if( param.has("opId")) {
			int opId = param.getInt("opId");
			opera.setOpId(opId);
			operacriteria.andOpIdEqualTo(opId);
			if( param.has("type") && param.get("type") != "") {
				String op_type = param.getString("type");
				opera.setOpType(op_type);
			}
			if( param.has( "name") && param.get("name") != "") {
				String op_name = param.getString( "name");
				opera.setOpName(op_name);
			}
			if( param.has( "url") && param.get("url") != "") {
				String op_url = param.getString( "url");
				opera.setOpName(op_url);
			}
			if( param.has( "desc") && param.get("desc") != "") {
				String op_desc = param.getString( "desc");
				opera.setOpName(op_desc);
			}
			if( param.has( "photourl") && param.get("photourl") != "") {
				String op_photourl = param.getString( "photourl");
				opera.setOpName(op_photourl);
			}
			if( param.has( "updateto") && param.get("updateto") != "") {
				String op_updateto = param.getString( "updateto");
				opera.setOpName(op_updateto);
			}
			if( param.has( "iframeurl") && param.get("iframeurl") != "") {
				String op_iframeurl = param.getString( "iframeurl");
				opera.setOpName(op_iframeurl);
			}
			if( param.has( "time") && param.get("time") != "") {
				Date op_time = (Date)param.get( "time");
				opera.setOpTime(op_time);
			}
			if( param.has( "status") && param.get("status") != "") {
				int op_status = param.getInt( "status");
				opera.setOpStatus(op_status);
			}
			if( param.has( "collectnum") && param.get("collectnum") != "") {
				int op_collectnum = param.getInt( "collectnum");
				opera.setOpStatus(op_collectnum);
			}
			if( param.has( "sharenum") && param.get("sharenum") != "") {
				int op_sharenum = param.getInt( "sharenum");
				opera.setOpStatus(op_sharenum);
			}
			if( this.operaMapper.updateByExampleSelective(opera, operaExample) > 0) {
				jsonobject.put("code", 1);
				jsonobject.put("msg", "更新成功!");
				System.out.println("更新成功" + opera.getOpName());
			}
		}else {
			System.out.println("未传id值过来，操作异常!");
		}
		
		
		return jsonobject;
	}

	@Override
	public int updateByPrimaryKeySelective(Opera record) {
		// TODO Auto-generated method stub
		return 0;
	}
}
