package guomanwang.service;

import java.util.List;

import guomanwang.domain.Company;

public interface CompanyService {
	//获得所有动画公司的信息
	public List<Company> getallcompany();
	//通过id找到对应的动画公司或数据来源信息
	public Company getcompanybyid(int id);
}
