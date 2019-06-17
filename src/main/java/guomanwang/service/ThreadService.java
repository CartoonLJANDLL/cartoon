package guomanwang.service;
import java.util.List;

import guomanwang.domain.Thread;
import net.sf.json.JSONObject;
public interface ThreadService {
	int addthread(Thread thread);
	List<Thread> getthreadbyuserid(int userid);
	public Thread selectThreadById(int id);
	public int deleteThreadById(int id);
	//综合查询版块下所有帖子
		public List<Thread> selectAllThread(int blockId);
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
		//整个论坛最热的10个帖子
		public JSONObject selectHotestToptenThread();
		//更新帖子
		public int updateThread(Thread thread);
		//最新排行
		public List<Thread> selectNewRankingThread(int blockId);
		//判断该帖子属于哪类  1未结，2已结， 3热帖， 4精选, 帖子下commit数量超过20即为热帖
		public String judgeThread(int status) ;
		//设置结贴，默认为未结
		public String setupFinishedThread(Thread thread);
		//设置为精选帖子
		public String setupAnimaThread(Thread thread);
		//自动判断热帖
		public String autoSetupHotThread(Thread thread);
		//获得帖子总数
		public int getThreadNumber();
		//所有版块的最新帖子
		public List<Thread> getnewThread();
}
