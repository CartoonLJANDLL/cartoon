package guomanwang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import guomanwang.domain.UserGrade;
import guomanwang.mapper.UserGradeMapper;
import guomanwang.service.UserGradeService;
@Service("UserGradeServiceimpl")
public class UserGradeServiceimpl implements UserGradeService{
	@Autowired
	@Qualifier("UserGradeMapper")
	private UserGradeMapper usergradeMapper;
	
	@Override
	public int updateUserGrade(UserGrade usergrade) {
		return this.usergradeMapper.updateUserGrade(usergrade);
	}

	@Override
	public int deleteUserGradeById(int id) {
		return this.usergradeMapper.deleteUserGradeById(id);
	}

	@Override
	public int addUserGrade(UserGrade usergrade) {
		return this.usergradeMapper.addUserGrade(usergrade);
	}

	@Override
	public List<UserGrade> getUserGrade() {
		return this.usergradeMapper.getUserGrade();
	}

}
