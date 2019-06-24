package guomanwang.service;

import java.util.List;

import guomanwang.domain.Peoplenum;
import guomanwang.domain.PeoplenumExample;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface PeoplenumService {

	public int insertPeoplenum( Peoplenum peoplenum) throws Exception;
	
	public List<Peoplenum> selectPeoplenumOptional( Peoplenum peoplenum) throws Exception;

	public JSONObject selectuseractive(PeoplenumExample example) throws Exception;
	
	
}
