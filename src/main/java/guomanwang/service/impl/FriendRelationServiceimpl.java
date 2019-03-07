package guomanwang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import guomanwang.domain.FriendRelation;
import guomanwang.domain.User;
import guomanwang.mapper.FriendRelationMapper;
import guomanwang.service.FriendRelationService;
@Service("FriendRelationServiceimpl")
public class FriendRelationServiceimpl implements FriendRelationService{

	@Autowired
	@Qualifier("FriendRelationMapper")
	private FriendRelationMapper friendrelationmapper;
	
	@Override
	public int requests(FriendRelation friend) {
		return this.friendrelationmapper.requests(friend);
	}

	@Override
	public int update(FriendRelation friend) {
		return this.friendrelationmapper.update(friend);
	}

	@Override
	public List<User> getrequestsbyuserid(int userid) {
		return this.friendrelationmapper.getrequestsbyuserid(userid);
	}

	@Override
	public List<User> getfriendsbyuserid(int userid) {
		return this.friendrelationmapper.getfriendsbyuserid(userid);
	}

	@Override
	public int isfriend(FriendRelation friend) {
		return this.friendrelationmapper.isfriend(friend);
	}

	@Override
	public List<User> getasksbyuserid(int userid) {
		return this.friendrelationmapper.getasksbyuserid(userid);
	}

	@Override
	public int deleteFriend(FriendRelation friend) {
		return this.friendrelationmapper.deleteFriend(friend);
	}

}
