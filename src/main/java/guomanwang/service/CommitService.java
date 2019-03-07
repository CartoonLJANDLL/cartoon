package guomanwang.service;

import java.util.List;

import guomanwang.domain.Commit;

public interface CommitService {

	//插入数据
    public int insertCommit(Commit commit);
    //通过id删除评论
  	public int deleteCommitById(int id);
    //通过id更新评论信息
	public int updateById(Commit commit);
	
	
	//通过id查询评论
	public Commit selectCommitById(int id);
    //查询该thread帖子下的所有commit评论经由parentid 和 threadid  这里可以循环的到好多层
	public List<Commit> selectCommitByThreadIdAndParentId(Commit commit);
	//查询该thread帖子下的所有第一个回复评论  commit的第一层
	public List<Commit> selectApplyCommitByThread(int threadId);
	//查询该thread帖子下有多少commit评论
	public int selectCountToutalByThreadId(int threadid);
	//通过用户id查询出回复过的帖子
	public List<Commit> selectCommitByuserId(int userid);

}
