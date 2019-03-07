package guomanwang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import guomanwang.domain.Commit;
import guomanwang.domain.UserCommit;
import guomanwang.mapper.UserCommitMapper;
import guomanwang.service.UserCommitService;
@Service("UserCommitServiceimpl")
public class UserCommitServiceimpl implements UserCommitService {

	@Autowired
	@Qualifier("UserCommitMapper")
	private UserCommitMapper userCommitMapper;

	@Override
	public List<UserCommit> selectOneCommit(int threadId) {
		return this.userCommitMapper.selectOneCommit(threadId);
	}

	@Override
	public List<UserCommit> selectAllCommitsByThreadId(Commit commit) {
		return this.userCommitMapper.selectAllCommitsByThreadId(commit);
	}

	@Override
	public List<UserCommit> selectCommitByReplyParentId(Commit commit) {
		return this.selectCommitByReplyParentId(commit);
	}

	@Override
	public List<UserCommit> selectmyCommitsByuserid(int userid) {
		return this.userCommitMapper.selectmyCommitsByuserid(userid);
	}

	@Override
	public List<UserCommit> selectTopUserByWeek() {
		return this.userCommitMapper.selectTopUserByWeek();
	}

}
