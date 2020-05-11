package guomanwang.service.impl;

import java.util.List;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import guomanwang.domain.Information;
import guomanwang.domain.Page;
import guomanwang.mapper.InformationMapper;
import guomanwang.service.InformationService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("InformationServiceimpl")
public class InformationServiceimpl implements InformationService{

	@Autowired
	@Qualifier("InformationMapper")
	private InformationMapper informationMapper;
	//获得资讯总数量
	@Override
	public int getinformationnum() {
		return this.informationMapper.getinformationnum();
	}
	//获得20条最近的资讯
	@Override
	public List<Information> getnewinformation() {
		return this.informationMapper.getnewinformation();
	}
	@Override
	public int update(Information information){
		return this.informationMapper.update(information);
	}
	//通过资讯id找到相关资讯
	@Override
	public Information getinformationbyid(int id) {
		return this.informationMapper.getinformationbyid(id);
	}
	//通过资讯id删除相关资讯
	@Override
	public int deleteInformationById(int id){
		return this.informationMapper.deleteInformationById(id);
	};
	//通过资讯id数组实现批量删除资讯
	@Override
	public int deletemanynewsById(String[] ids) {
		return this.informationMapper.deletemanynewsById(ids);
	}
	//新增一条资讯
	@Override
	public int addinformation(Information information){
		return this.informationMapper.addinformation(information);
	};
	//模糊查询资讯
	@Override
	public JSONObject searchinformationbytitle(String key){
		List<Information> newslist=this.informationMapper.searchinformationbytitle(key);
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonobject= new JSONObject();
		jsonobject.put("code",0);
		jsonobject.put("msg","未查询到符合条件的结果");
		if(newslist.size()>0) {
			for(Information information:newslist) {
				JSONObject json= new JSONObject();
				json.put("id",information.getId());
				json.put("title",information.getTitle());
				json.put("time",information.getTime());
				json.put("url",information.getUrl());
				json.put("company",information.getCompanyname());
				jsonArray.add(json);
			}
			jsonobject.put("code",1);
			jsonobject.put("msg","查询到符合条件的结果");
			jsonobject.put("count", newslist.size());
			jsonobject.put("data", jsonArray);
		}
		return jsonobject;
	}
	//通过资讯的发布者即所属公司找到相关资讯
	@Override
	public List<Information> selectinformationbycompany(Page page) {
		return this.informationMapper.selectinformationbycompanyid(page);
	}
	@Override
	public List<Information> getinformation(Page page) {
		return this.informationMapper.getinformation(page);
	}
	@Override
	public int getnumberbycompany(int companyid) {
		return this.informationMapper.getnumberbycompany(companyid);
	}
	@Override
	public JSONObject getinformationtitle() {
		String text="";
		String word="";
		JSONObject json=new JSONObject();
		List<Information> titles=this.informationMapper.getinformationtitle();
		for(Information title:titles) {
			text+=title.getTitle();
		}
		Result parse = ToAnalysis.parse(text);

        List<Term> terms = parse.getTerms(); //拿到terms
        for(int i=0; i<terms.size(); i++) {
        	 word= word+","+terms.get(i).getName(); //拿到词
        }
        System.out.println(word);
		json.put("data",word);
		return json;
	}
	@Override
	public JSONObject gettopviewnews(int limitsize){
		JSONArray jsonarray=new JSONArray();
		JSONArray jsonarray1=new JSONArray();
		JSONObject json=new JSONObject();
		List<Information> topnews=this.informationMapper.gettopviewnews(limitsize);
		for(Information news:topnews) {
			jsonarray.add(news.getTitle());
			jsonarray1.add(news.getViewcount());
		}
		json.put("x",jsonarray);
		json.put("y",jsonarray1);
		return json;
	}
}
