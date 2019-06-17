package guomanwang.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import guomanwang.domain.Opnumupdate;
import guomanwang.domain.OpnumupdateExample;
import guomanwang.mapper.OpnumupdateMapper;
import guomanwang.service.OpnumupdateService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service("OpnumupdateServiceimpl")
public class OpnumupdateServiceimpl implements OpnumupdateService {

	@Autowired
	@Qualifier("OpnumupdateMapper")
	private OpnumupdateMapper opnumupdateMapper;
	/*
	 * 前端传了JSONObject如果包含starttime和endtime就可以按时间查询前十个，否则，直接获取表的前十个
	 * (non-Javadoc)
	 * @see guomanwang.service.OpnumupdateService#selectOpnumupdateTopten(net.sf.json.JSONObject)
	 */
	
	@Override
	public JSONObject selectOpnumupdateTopten(JSONObject param) throws Exception {
		OpnumupdateExample  opnumupdateExample = new OpnumupdateExample();
		OpnumupdateExample.Criteria opnumCriteria = opnumupdateExample.createCriteria();
		opnumupdateExample.setDistinct(false);
		
		JSONObject returnObject = new JSONObject();
		JSONArray jsonX = new JSONArray();
		JSONArray jsonY = new JSONArray();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		List<Opnumupdate> opnumupdate = new ArrayList<Opnumupdate>();
		opnumCriteria.andUpdatetimeBetween( formatter.parse( param.getString("startdate")), formatter.parse( param.getString("enddate")));
		System.out.println("");
		
		opnumupdate = this.opnumupdateMapper.selectByExample(opnumupdateExample);
		for( int i = 0; i < opnumupdate.size(); i++) {
			jsonX.add( formatter.format(opnumupdate.get(i).getUpdatetime()));
			jsonY.add( opnumupdate.get(i).getOpnum());
			System.out.println( "+++++++++" + opnumupdate.get(i).getUpdatetime() + "=========" + opnumupdate.get(i).getOpnum());;
		}
		returnObject.put("x", jsonX);
		returnObject.put("y", jsonY);
		return returnObject;
	}

	@Override
	public void insertOpnumupdateInfo(Opnumupdate param) throws Exception {
		if( param.getUpdatetime() == null)
			param.setUpdatetime( new Date());
		this.opnumupdateMapper.insert(param);
	}

	@Override
	public String selectDatetime() throws Exception {
		OpnumupdateExample example = new OpnumupdateExample();
		OpnumupdateExample.Criteria opCriteria = example.createCriteria();
		example.setOrderByClause( "updatetime DESC");
		opCriteria.andIdIsNotNull();
		List<Opnumupdate> opnumupdates = this.opnumupdateMapper.selectByExample(example);
		System.out.println("+++++++++++++" + opnumupdates.get(0).getUpdatetime().toString());
		Date latestDate =  opnumupdates.get(0).getUpdatetime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String ladate = formatter.format(latestDate);
		return ladate;
	}

}
