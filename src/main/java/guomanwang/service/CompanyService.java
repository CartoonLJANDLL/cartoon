package guomanwang.service;

import java.util.List;

import guomanwang.domain.Company;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface CompanyService {
	//获得所有动画公司的信息
	public List<Company> getallcompany();
	//通过id找到对应的动画公司或数据来源信息
	public Company getcompanybyid(int id);
	//根据公司或来源网站分组获得资讯数量
	public JSONArray getcompanycount();
}
