package guomanwang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import guomanwang.domain.Company;
import guomanwang.mapper.CompanyMapper;
import guomanwang.service.CompanyService;

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
	
}
