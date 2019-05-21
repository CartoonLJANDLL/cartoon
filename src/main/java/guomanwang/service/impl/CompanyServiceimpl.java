package guomanwang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import guomanwang.domain.Company;
import guomanwang.mapper.CompanyMapper;
import guomanwang.service.CompanyService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("CompanyServiceimpl")
public class CompanyServiceimpl implements CompanyService{
	@Autowired
	@Qualifier("CompanyMapper")
	private CompanyMapper companyMapper;
	
	@Override
	public List<Company> getallcompany() {
		return this.companyMapper.getallcompany();
	}

	@Override
	public Company getcompanybyid(int id) {
		return this.companyMapper.getcompanybyid(id);
	}
	@Override
	public JSONArray getcompanycount() {
		JSONArray jsonarray= new JSONArray();
		List<Company> companys=this.companyMapper.getcompanycount();
		for(Company company:companys) {
			JSONObject one= new JSONObject();
			one.put("name", company.getName());
			one.put("y", company.getId());
			jsonarray.add(one);
		}
		return jsonarray;
	}
	
}
