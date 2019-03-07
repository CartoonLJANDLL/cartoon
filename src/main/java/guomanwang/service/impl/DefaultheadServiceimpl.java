package guomanwang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import guomanwang.domain.Defaulthead;
import guomanwang.mapper.DefaultheadMapper;
import guomanwang.service.DefaultheadService;

@Service("DefaultheadServiceimpl")
public class DefaultheadServiceimpl implements DefaultheadService{
	@Autowired
	@Qualifier("DefaultheadMapper")
	private DefaultheadMapper defaultheadMapper;
	
	@Override
	public List<Defaulthead> getallDefaulthead() {
		return defaultheadMapper.getallDefaulthead();
	}

	@Override
	public int deleteDefaultheadById(int id) {
		return defaultheadMapper.deleteDefaultheadById(id);
	}

	@Override
	public int addDefaulthead(Defaulthead headimage) {
		return defaultheadMapper.addDefaulthead(headimage);
	}

	@Override
	public int deletemanyimagesById(String[] ids) {
		return defaultheadMapper.deletemanyimagesById(ids);
	}

}
