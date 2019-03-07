package guomanwang.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import guomanwang.domain.Thread;

@Repository("ThreadMapper")
public interface ThreadMapper {
	public int addthread(Thread thread);
	public List<Thread> getthreadbyuserid(int userid);
	public int deleteThreadById(int id);
	//获取到帖子的总数
	public int getThreadNumber();
	public int updateThread(Thread thread);
	
	public Thread selectThreadById(int id);
	//综合查询版块下所有帖子
	public List<Thread> selectAllThread(int blockId);
	
	public List<Thread> selectThreadByName();
	//blockId板块下有多少thread帖子
	public int selectToutalCountThreadByBlockId(int blockId);
	//未结帖子
	public List<Thread> selectUnfinishThread(int blockId);
    //已结帖子
	public List<Thread> selectFinishedThread(int blockId);
	//热帖
	public List<Thread> selectHotThread(int blockId);
	//精华帖子
	public List<Thread> selectAnimaThread(int blockId);
	//热度排行
	public List<Thread> selectHotRankingThread(int blockId);
	//最新排行
	public List<Thread> selectNewRankingThread(int blockId);
	//所有版块的最新帖子
	public List<Thread> getnewThread();
}