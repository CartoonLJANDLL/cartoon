package guomanwang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import guomanwang.domain.Opera;
import guomanwang.domain.OperaExample;
import guomanwang.domain.UserOpera;
import guomanwang.domain.UserOperaExample;
import guomanwang.mapper.OperaMapper;
import guomanwang.mapper.UserOperaMapper;
import guomanwang.service.OperaService;

@Service("OperaServiceimpl")
public class OperaServiceimpl implements OperaService {

	@Autowired
	@Qualifier("OperaMapper")
	private OperaMapper operaMapper;
	OperaExample operaExample = new OperaExample();
	OperaExample.Criteria criteria = operaExample.createCriteria();
	
	@Autowired
	@Qualifier("UserOperaMapper")
	private UserOperaMapper userOperaMapper;
	
	//查询所有Opera
	@Override
	public List<Opera> selectAllOpera() {
		UserOperaExample userOperaExample = new UserOperaExample();
		UserOperaExample.Criteria usercriteria = userOperaExample.createCriteria();
		List<Opera> operas = operaMapper.selectByExample(operaExample);
		
		return operas;
	}
    //收藏番剧opera
	@Override
	public String collectOpera(int operaid, int userid) {
		UserOpera useropera = new UserOpera();
		useropera.setOperaId(operaid);
		useropera.setUserId(userid);
		int rs = this.userOperaMapper.insertSelective(useropera);
		return "别用这个";
	}
	
    //分享番剧
	@Override
	public int shareOpera(int operaid, int userid) {
		UserOpera useropera = new UserOpera();
		useropera.setOperaId(operaid);
		useropera.setUserId(userid);
		int rs = userOperaMapper.insertSelective(useropera);
		return rs;
	}
    //根据id删除番剧
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return operaMapper.deleteByPrimaryKey(id);
	}
    //通过番剧名模糊查询
	@Override
	public List<Opera> selectOperaByName(String name) {
		criteria.andNameLike(name);
		
		return this.operaMapper.selectByExample(operaExample);
	}
    //根据自定义条件选择性更新
	@Override
	public int updateByExampleSelective(Opera record, OperaExample example) {
		
		return this.operaMapper.updateByExampleSelective(record, example);
	}
	//获得Opera数量
	@Override
	public int getOperaNum() {
		UserOperaExample userOperaExample = new UserOperaExample();
		UserOperaExample.Criteria usercriteria = userOperaExample.createCriteria();
		return this.userOperaMapper.countByExample(userOperaExample);
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
	//取消收藏番剧opera  如果收藏了就取消收藏，没收藏则收藏
	@Override
	public String nocollectOpera(int operaId, int userId) {
		UserOperaExample userOperaExample = new UserOperaExample();
		UserOperaExample.Criteria usercriteria = userOperaExample.createCriteria();
		UserOpera useropera = new UserOpera();
		useropera = getUserOpera(userId,operaId);
		usercriteria.andOperaIdEqualTo(operaId);
		usercriteria.andUserIdEqualTo(userId);
		if(useropera.getCollection() == 1) {
			useropera.setCollection(0);
			if(this.userOperaMapper.updateByExampleSelective(useropera, userOperaExample) == 1) {
				return "取消收藏成功uncollect";
			}else {
				return "取消收藏失败uncollectfailed";
			}
		}else if(useropera.getCollection() == 0){
			useropera.setCollection(1);
			if(this.userOperaMapper.updateByExampleSelective(useropera, userOperaExample) == 1) {
				return "收藏成功collected";
			}else {
				return "收藏失败collectfailed";
			}
		}else {
			return "operaServiceimpl ERROR";
		}
		
	}
	//通过userid和Operaid找到userOpera
	@Override
	public UserOpera getUserOpera(int userId, int operaId) {
		UserOperaExample userOperaExample = new UserOperaExample();
		UserOperaExample.Criteria usercriteria = userOperaExample.createCriteria();
		UserOpera useropera = new UserOpera();
		usercriteria.andUserIdEqualTo(userId);
		usercriteria.andOperaIdEqualTo(operaId);
		useropera = this.userOperaMapper.selectByExample(userOperaExample).get(0);
		return useropera;
	}

}
