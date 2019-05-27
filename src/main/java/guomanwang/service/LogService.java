package guomanwang.service;

import guomanwang.domain.Log;

public interface LogService {

	int insertLogSelective(Log log) throws Exception;
}
