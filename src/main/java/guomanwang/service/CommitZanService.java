package guomanwang.service;

import guomanwang.domain.CommitZan;

public interface CommitZanService {
	//用户点赞
	public int dianzan(CommitZan commitzan);
	//用户取消点赞
	public int cancelzan(CommitZan commitzan);
}
