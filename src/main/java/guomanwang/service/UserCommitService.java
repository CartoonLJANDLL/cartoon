package guomanwang.service;

import java.util.List;

import guomanwang.domain.Commit;
import guomanwang.domain.UserCommit;

public interface UserCommitService {
		//查询回复直接回复帖子的评论
		public List<UserCommit> selectOneCommit(int threadId);
		//查询该帖子下的所有评论
		public List<UserCommit> selectAllCommitsByThreadId(Commit commit);
		//根据parentid和replyID还有threadid查询
		public List<UserCommit> selectCommitByReplyParentId(Commit commit);
		//查询我的回帖
		public List<UserCommit> selectmyCommitsByuserid(int userid);
		//用户回帖周榜
		public List<UserCommit> selectTopUserByWeek();
}
