package guomanwang.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import guomanwang.domain.Company;

@Repository("CompanyMapper")
public interface CompanyMapper {
	public List<Company> getallcompany();
	//通过id找到对应的动画公司或数据来源信息
	public Company getcompanybyid(int id);
}
