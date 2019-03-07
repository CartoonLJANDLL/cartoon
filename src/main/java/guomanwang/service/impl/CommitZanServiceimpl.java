package guomanwang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import guomanwang.domain.CommitZan;
import guomanwang.mapper.CommitZanMapper;
import guomanwang.service.CommitZanService;

@Service("CommitZanServiceimpl")
public class CommitZanServiceimpl implements CommitZanService{
	@Autowired
	@Qualifier("CommitZanMapper")
	private CommitZanMapper commitzanMapper;
	
	@Override
	public int dianzan(CommitZan commitzan) {
		return this.commitzanMapper.dianzan(commitzan);
	}

	@Override
	public int cancelzan(CommitZan commitzan) {
		return this.commitzanMapper.cancelzan(commitzan);
	}

}
