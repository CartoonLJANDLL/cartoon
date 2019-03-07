package guomanwang.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import guomanwang.domain.FriendRelation;
import guomanwang.domain.User;
@Repository("FriendRelationMapper")
public interface FriendRelationMapper {
	public int requests(FriendRelation friend);
	public int update(FriendRelation friend);
	public List<User> getrequestsbyuserid(int userid);
	public List<User> getfriendsbyuserid(int userid);
	public List<User> getasksbyuserid(int userid);
	public int isfriend(FriendRelation friend);
	public int deleteFriend(FriendRelation friend);
}
