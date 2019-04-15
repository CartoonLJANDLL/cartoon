package guomanwang.service;

import java.util.List;

import guomanwang.domain.Peoplenum;

public interface PeoplenumService {

	public int insertPeoplenum( Peoplenum peoplenum) throws Exception;
	
	public List<Peoplenum> selectPeoplenumOptional( Peoplenum peoplenum) throws Exception;
	
	
}
