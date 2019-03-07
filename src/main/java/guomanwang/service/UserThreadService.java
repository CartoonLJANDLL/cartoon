package guomanwang.service;

import java.util.List;

import guomanwang.domain.UserThread;

public interface UserThreadService {

//	我回复的帖子
	public List<UserThread> selectMyCommitThread(int userId);
    //使用userthread继承自thread的方法
	public List<UserThread> selectUserThreadsByUserIdBlockId(int blockId);
	//查询该板块下所有公告
	public List<UserThread> selectAllNoticesByBlockId(int blockId);
//查询一个帖子
	public UserThread selectUserThreadByThreadId(int id);
//		通过帖子名查找帖子
	public List<UserThread> selectThreadByName(String title);
	
//		查找该板块下未结帖子
	public List<UserThread> selectUnfinishThread(int blockId);
//		查找该板块下已结帖子
	public List<UserThread> selectFinishedThread(int blockId);
	
//		查找该板块下热帖 
	public List<UserThread> selectHotThread(int blockId);
	
//		查找该板块下精贴 
	public List<UserThread> selectAnimaThread(int blockId);
	
//		按热度排行
	public List<UserThread> selectHotRankingThread(int blockId);
	
//		按新度排行
	public List<UserThread> selectNewRankingThread(int blockId);
//		我发表的帖子
	public List<UserThread> selectMyPushedThread(int userId);

//通过关键字和板块id查询到符合条件的帖子
	public List<UserThread> selectUserThreadsBykey(UserThread userthread);
}
