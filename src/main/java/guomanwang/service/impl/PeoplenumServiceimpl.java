package guomanwang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import guomanwang.domain.Peoplenum;
import guomanwang.mapper.PeoplenumMapper;
import guomanwang.service.PeoplenumService;
@Service("PeoplenumServiceimpl")
public class PeoplenumServiceimpl implements PeoplenumService {

	@Autowired
	@Qualifier("PeoplenumMapper")
	private PeoplenumMapper peoplenumMapper;
	@Override
	public List<Peoplenum> selectPeoplenumOptional(Peoplenum peoplenum) throws Exception {
		/*List<Peoplenum> */
		return null;
	}

	@Override
	public int insertPeoplenum(Peoplenum peoplenum) throws Exception{
		int rs = this.peoplenumMapper.insertSelective(peoplenum);
		return rs;
	}

}
