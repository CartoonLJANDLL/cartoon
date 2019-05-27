package guomanwang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import guomanwang.mapper.AccessMapper;
import guomanwang.service.AccessService;
@Service("AccessServiceimpl")
public class AccessServiceimpl implements AccessService {

	@Autowired
	@Qualifier("AccessMapper")
	private AccessMapper accessMapper;
	@Override
	public String selectAccessById(int accessId) throws Exception {
		String access = this.accessMapper.selectByPrimaryKey( accessId).getAccesspwd();
		System.out.println("access" + access);
		return access;
	}

}
