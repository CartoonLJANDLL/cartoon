package guomanwang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import guomanwang.domain.Log;
import guomanwang.mapper.LogMapper;
import guomanwang.service.LogService;
@Service("LogServiceimpl")
public class LogServiceimpl implements LogService {

	@Autowired
	@Qualifier("LogMapper")
	private LogMapper logMapper;
	
	@Override
	public int insertLogSelective(Log log) throws Exception{
		int rs = this.logMapper.insertSelective(log);
		return rs;
	}

}
