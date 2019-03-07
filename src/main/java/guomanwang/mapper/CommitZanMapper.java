package guomanwang.mapper;

import org.springframework.stereotype.Repository;

import guomanwang.domain.CommitZan;

@Repository("CommitZanMapper")
public interface CommitZanMapper {
	//用户点赞
	public int dianzan(CommitZan commitzan);
	//用户取消点赞
	public int cancelzan(CommitZan commitzan); 
}
