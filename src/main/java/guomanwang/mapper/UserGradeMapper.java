package guomanwang.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import guomanwang.domain.UserGrade;

@Repository("UserGradeMapper")
public interface UserGradeMapper {
	//根据id更新等级信息
	public int updateUserGrade(UserGrade usergrade);
	//根据id删除等级信息
	public int deleteUserGradeById(int id);
	//新增等级
	public int addUserGrade(UserGrade usergrade);
	//获得所有等级信息
	public List<UserGrade> getUserGrade();
}
