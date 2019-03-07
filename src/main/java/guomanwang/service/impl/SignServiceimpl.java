package guomanwang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import guomanwang.service.SignService;
import guomanwang.domain.Sign;
import guomanwang.mapper.SignMapper;

@Service("SignServiceimpl")
public class SignServiceimpl implements SignService{
	@Autowired
	@Qualifier("SignMapper")
	private SignMapper signmapper;
	//用户签到
	@Override
	public int Sign(Sign sign) {
		return this.signmapper.Sign(sign);
	}
	//判断用户是否签到
	@Override
	public Sign isSign(int userid) {
		return this.signmapper.isSign(userid);
	}
	//获得用户连续登录天数
	@Override
	public int getSignDays(int userid) {
		return this.signmapper.getSignDays(userid);
	}
	
}
