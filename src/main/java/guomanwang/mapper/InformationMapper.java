package guomanwang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import guomanwang.domain.Information;
import guomanwang.domain.Page;

@Repository("InformationMapper")
public interface InformationMapper {
	//获得资讯的数量
	public int getinformationnum();
	//获得指定公司或来源的资讯的数量
	public int getnumberbycompany(int companyid);
	//获得20条最近的资讯
	public List<Information> getnewinformation();
	//获得资讯分页
		public List<Information> getinformation(Page page);
	//通过资讯id更新资讯
	public int update(Information information);
	//通过资讯id找到相关资讯
	public Information getinformationbyid(int id);
	//通过资讯的发布者即所属公司找到相关资讯
	public List<Information> selectinformationbycompanyid(Page page);
	//通过所有资讯的标题
	public List<Information> getinformationtitle();
	//通过资讯id删除相关资讯
	public int deleteInformationById(int id);
	//通过资讯id数组实现批量删除资讯
	public int deletemanynewsById(String[] ids);
	//新增一条资讯
	public int addinformation(Information information);
	//模糊查询资讯
	public List<Information> searchinformationbytitle(String key);
	//获得资讯点击量的前几名
	public List<Information> gettopviewnews(@Param("limitsize")int limitsize);
}
