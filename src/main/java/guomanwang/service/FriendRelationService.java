package guomanwang.service;

import java.util.List;

import guomanwang.domain.FriendRelation;
import guomanwang.domain.User;

public interface FriendRelationService {
	public int requests(FriendRelation friend);
	public int update(FriendRelation friend);
	public List<User> getrequestsbyuserid(int userid);
	public List<User> getfriendsbyuserid(int userid);
	public List<User> getasksbyuserid(int userid);
	public int isfriend(FriendRelation friend);
	public int deleteFriend(FriendRelation friend);
}
