package guomanwang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import guomanwang.domain.UserThread;
import guomanwang.mapper.UserThreadMapper;
import guomanwang.service.UserThreadService;
@Service("UserThreadServiceimpl")
public class UserThreadServiceimpl implements UserThreadService {

	@Autowired
	@Qualifier("UserThreadMapper")
	private UserThreadMapper userThreadMapper;
	
	@Override
	public List<UserThread> selectUserThreadsByUserIdBlockId(int blockId) {
		// TODO Auto-generated method stub
		return this.userThreadMapper.selectUserThreadsByUserIdBlockId(blockId);
	}

	@Override
	public UserThread selectUserThreadByThreadId(int id) {
		// TODO Auto-generated method stub
		return this.userThreadMapper.selectUserThreadByThreadId(id);
	}

	@Override
	public List<UserThread> selectThreadByName(String title) {
		// TODO Auto-generated method stub
		return this.userThreadMapper.selectThreadByName(title);
	}

	@Override
	public List<UserThread> selectUnfinishThread(int blockId) {
		// TODO Auto-generated method stub
		return this.userThreadMapper.selectUnfinishThread(blockId);
	}

	@Override
	public List<UserThread> selectFinishedThread(int blockId) {
		// TODO Auto-generated method stub
		return this.userThreadMapper.selectFinishedThread(blockId);
	}

	@Override
	public List<UserThread> selectHotThread(int blockId) {
		// TODO Auto-generated method stub
		return this.userThreadMapper.selectHotThread(blockId);
	}

	@Override
	public List<UserThread> selectAnimaThread(int blockId) {
		// TODO Auto-generated method stub
		return this.userThreadMapper.selectAnimaThread(blockId);
	}

	@Override
	public List<UserThread> selectHotRankingThread(int blockId) {
		// TODO Auto-generated method stub
		return this.userThreadMapper.selectHotRankingThread(blockId);
	}

	@Override
	public List<UserThread> selectNewRankingThread(int blockId) {
		// TODO Auto-generated method stub
		return this.userThreadMapper.selectNewRankingThread(blockId);
	}

	@Override
	public List<UserThread> selectMyPushedThread(int userId) {
		// TODO Auto-generated method stub
		return this.userThreadMapper.selectMyPushedThread(userId);
	}

	@Override
	public List<UserThread> selectUserThreadsBykey(UserThread userthread) {
		return this.userThreadMapper.selectUserThreadsBykey(userthread);
	}

	@Override
	public List<UserThread> selectAllNoticesByBlockId(int blockId) {
		return this.userThreadMapper.selectAllNoticesByBlockId(blockId);
	}
//	我回复的帖子
	@Override
	public List<UserThread> selectMyCommitThread(int userId){
		return this.userThreadMapper.selectMyCommitThread(userId);
	}
	

}
