package guomanwang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import guomanwang.domain.Information;
import guomanwang.domain.Page;
import guomanwang.mapper.InformationMapper;
import guomanwang.service.InformationService;

@Service("InformationServiceimpl")
public class InformationServiceimpl implements InformationService{

	@Autowired
	@Qualifier("InformationMapper")
	private InformationMapper informationMapper;
	//获得资讯总数量
	@Override
	public int getinformationnum() {
		return this.informationMapper.getinformationnum();
	}
	//获得20条最近的资讯
	@Override
	public List<Information> getnewinformation() {
		return this.informationMapper.getnewinformation();
	}
	@Override
	public int update(Information information){
		return this.informationMapper.update(information);
	}
	//通过资讯id找到相关资讯
	@Override
	public Information getinformationbyid(int id) {
		return this.informationMapper.getinformationbyid(id);
	}
	//通过资讯id删除相关资讯
	@Override
	public int deleteInformationById(int id){
		return this.informationMapper.deleteInformationById(id);
	};
	//通过资讯id数组实现批量删除资讯
	@Override
	public int deletemanynewsById(String[] ids) {
		return this.informationMapper.deletemanynewsById(ids);
	}
	//新增一条资讯
	@Override
	public int addinformation(Information information){
		return this.informationMapper.addinformation(information);
	};
	//模糊查询资讯
	@Override
	public List<Information> searchinformationbytitle(String key){
		return this.informationMapper.searchinformationbytitle(key);
	}
	//通过资讯的发布者即所属公司找到相关资讯
	@Override
	public List<Information> selectinformationbycompany(Page page) {
		return this.informationMapper.selectinformationbycompanyid(page);
	}
	@Override
	public List<Information> getinformation(Page page) {
		return this.informationMapper.getinformation(page);
	}
	@Override
	public int getnumberbycompany(int companyid) {
		return this.informationMapper.getnumberbycompany(companyid);
	}
	
}
