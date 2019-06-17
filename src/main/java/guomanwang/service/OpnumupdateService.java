package guomanwang.service;

import guomanwang.domain.Opnumupdate;
import net.sf.json.JSONObject;

public interface OpnumupdateService {

	public JSONObject selectOpnumupdateTopten( JSONObject param) throws Exception;
	public void insertOpnumupdateInfo( Opnumupdate param) throws Exception;
	public String selectDatetime() throws Exception;
	
}
