package guomanwang.service;

import java.util.List;

import guomanwang.domain.UserGrade;

public interface UserGradeService {
	//根据id更新等级信息
		public int updateUserGrade(UserGrade usergrade);
		//根据id删除等级信息
		public int deleteUserGradeById(int id);
		//新增等级
		public int addUserGrade(UserGrade usergrade);
		//获得所有等级信息
		public List<UserGrade> getUserGrade();
}
