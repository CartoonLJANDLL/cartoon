package guomanwang.mapper;

import org.springframework.stereotype.Repository;

import guomanwang.domain.Sign;

@Repository("SignMapper")
public interface SignMapper {
	//用户签到
	public int Sign(Sign sign);
	//判断用户是否签到
	public Sign isSign(int userid);
	//查询用户连续签到次数
	public int getSignDays(int userid);

}
