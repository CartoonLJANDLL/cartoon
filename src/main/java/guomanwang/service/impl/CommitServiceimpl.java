package guomanwang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import guomanwang.domain.Commit;
import guomanwang.domain.Thread;
import guomanwang.mapper.CommitMapper;
import guomanwang.mapper.ThreadMapper;
import guomanwang.service.CommitService;
@Service("CommitServiceimpl")
public class CommitServiceimpl implements CommitService {

	@Autowired
	@Qualifier("CommitMapper")
	private CommitMapper commitMapper;
	
	@Autowired
	@Qualifier("ThreadMapper")
	private  ThreadMapper threadMapper;
	//插入一条评论回复
	@Override
	public int insertCommit(Commit commit) {
		return this.commitMapper.insertCommit(commit);
	}
	//用户或管理员删除一条帖子下的回复
	@Override
	public int deleteCommitById(int id) {
		return this.commitMapper.deleteCommitById(id);
	}

	@Override
	public int updateById(Commit commit) {
		if(selectCommitById(commit.getId())!=null) {
			return this.commitMapper.updateById(commit);
		}else {
			return 0;
		}
		
		
	}

	//通过id查评论
	@Override
	public Commit selectCommitById(int id) {
		
		return this.commitMapper.selectCommitById(id);
	}

	//通过parentid和threadID查找评论
	@Override
	public List<Commit> selectCommitByThreadIdAndParentId(Commit commit) {
		
		return this.commitMapper.selectCommitByThreadIdAndParentId(commit);
	}

	//查找该帖子下的所有回复帖子的评论
	@Override
	public List<Commit> selectApplyCommitByThread(int threadId) {
		if(threadId>0) {
			return this.commitMapper.selectApplyCommitByThread(threadId);
		}else {
			return null;
		}
	}

	//查找该帖子下共有多少评论commit
	@Override
	public int selectCountToutalByThreadId(int threadid) {
		if(threadid>0) {
			return this.commitMapper.selectCountToutalByThreadId(threadid);
		}
		return 0;
	}

	@Override
	public List<Commit> selectCommitByuserId(int userid) {
		return this.commitMapper.selectCommitByuserId(userid);
	}

}
