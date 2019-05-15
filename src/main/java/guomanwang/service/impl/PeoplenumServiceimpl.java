package guomanwang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import guomanwang.domain.Peoplenum;
import guomanwang.domain.PeoplenumExample;
import guomanwang.mapper.PeoplenumMapper;
import guomanwang.service.PeoplenumService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
	public JSONArray selectuseractive(PeoplenumExample example) throws Exception {
		JSONArray jsonarray=new JSONArray();
		List<Peoplenum> peoplenum=this.peoplenumMapper.getuseractive();
		for(Peoplenum people:peoplenum) {
			JSONArray one=new JSONArray();
			one.add(people.getLefttime().getTime());
			one.add(people.getHoldtime());
			jsonarray.add(one);
			
			System.out.println(people.getLefttime());
			System.out.println(people.getHoldtime());
		}
		return jsonarray;
	}
	@Override
	public int insertPeoplenum(Peoplenum peoplenum) throws Exception{
		int rs = this.peoplenumMapper.insertSelective(peoplenum);
		return rs;
	}

}
