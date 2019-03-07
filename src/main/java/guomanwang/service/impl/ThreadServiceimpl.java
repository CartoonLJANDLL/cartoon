package guomanwang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import guomanwang.mapper.ThreadMapper;
import guomanwang.service.ThreadService;
import guomanwang.domain.Thread;

@Service("ThreadServiceimpl")
public class ThreadServiceimpl implements ThreadService {
	@Autowired
	@Qualifier("ThreadMapper")
	private ThreadMapper threadMapper;
	
	@Override
	public int addthread(Thread thread) {
		return this.threadMapper.addthread(thread);
	}
	@Override
	public List<Thread> getthreadbyuserid(int userid) {
		return this.threadMapper.getthreadbyuserid(userid);
	}
	@Override
	public Thread selectThreadById(int id) {
		
		return this.threadMapper.selectThreadById(id);
	}
	@Override
	public int deleteThreadById(int id) {
		
		return this.threadMapper.deleteThreadById(id);
	}
    //查找所有帖子，该板块下
	@Override
	public List<Thread> selectAllThread(int blockId) {
		
		return this.threadMapper.selectAllThread(blockId);
	}
    //该板块下未结帖子
	@Override
	public List<Thread> selectUnfinishThread(int blockId) {
		
		return this.threadMapper.selectUnfinishThread(blockId);
	}
    //该板块下已结帖子
	@Override
	public List<Thread> selectFinishedThread(int blockId) {
		// TODO Auto-generated method stub
		return this.threadMapper.selectFinishedThread(blockId);
	}
    //该板块下热议帖子
	@Override
	public List<Thread> selectHotThread(int blockId) {
		// TODO Auto-generated method stub
		return this.threadMapper.selectHotThread(blockId);
	}
    //该板块下精华帖子
	@Override
	public List<Thread> selectAnimaThread(int blockId) {
		// TODO Auto-generated method stub
		return this.threadMapper.selectAnimaThread(blockId);
	}
    //该板块下热度排行
	@Override
	public List<Thread> selectHotRankingThread(int blockId) {
		// TODO Auto-generated method stub
		return this.threadMapper.selectHotRankingThread(blockId);
	}
    //该板块下新度排行
	@Override
	public List<Thread> selectNewRankingThread(int blockId) {
		// TODO Auto-generated method stub
		return this.selectNewRankingThread(blockId);
	}
	//判断该帖子属于哪类  1未结，2已结， 3热帖， 4精选, 帖子下commit数量超过20即为热帖
		@Override
		public String judgeThread(int status) {
	        switch(status) {
	        case 1:
	        	return "未结";
	        case 2:
	        	return "已结";
	        case 3:
	        	return "热帖";
	        case 4:
	        	return "精华";
	        case 13:
	        	return "未结热帖";
	        case 14:
	        	return "未结精华";
	        case 23:
	        	return "已结热帖";
	        case 24:
	        	return "已结精华";
	        			
	        	default: return "未结";
	        }
		}
		//设置结贴，默认为未结
		@Override
		public String setupFinishedThread(Thread thread) {
			switch(thread.getStatus()) {
			case 1:
				thread.setStatus(2);
				break;
			case 3:
				thread.setStatus(23);
				break;
			case 4:
				thread.setStatus(24);
				break;
			case 13:
				thread.setStatus(23);
				break;
			case 14:
				thread.setStatus(24);
				break;
				default: break;
			}
			
			int rs = threadMapper.updateThread(thread);
			if(rs == 1) {
				System.out.println("设置成功");
				return "successfully finished";
			}else {
				System.out.println("设置失败");
				return "failed finished";
			}
		}
		//设置为精选帖子
		@Override
		public String setupAnimaThread(Thread thread) {
			
			int rs = threadMapper.updateThread(thread);
			if(rs == 1) {
				System.out.println("设置成功");
				return "successfully anima";
			}else {
				System.out.println("设置失败");
				return "failed anima";
			}
		}
		//自动判断热帖 commit数量超过20
		@Override
		public String autoSetupHotThread(Thread thread) {
			if(thread.getCommitNumber()>=20) {
				thread.setStatus(3);
				int rs = threadMapper.updateThread(thread);
				if(rs == 1) {
					System.out.println("设置成功");
					return "successfully hotThread";
				}else {
					System.out.println("设置失败");
					return "failed hotThread";
				}
			}else {
				return "default";
			}
		}
		//获得帖子数量；
		@Override
		public int getThreadNumber() {
			return this.threadMapper.getThreadNumber();
		}
		@Override
		public List<Thread> getnewThread() {
			return this.threadMapper.getnewThread();
		}
		//更新帖子
		@Override
		public int updateThread(Thread thread) {
			return this.threadMapper.updateThread(thread);
		}

	}
