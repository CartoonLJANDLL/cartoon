package guomanwang.service;

import java.util.List;

import guomanwang.domain.Defaulthead;

public interface DefaultheadService {
	//获得所有默认头像的图片
		public List<Defaulthead> getallDefaulthead();
		//根据照片id删除照片（单张）
		public int deleteDefaultheadById(int id);
		//根据照片id删除照片（多张）
		public int deletemanyimagesById(String[] ids);
		//添加或上传默认头像
		public int addDefaulthead(Defaulthead headimage);
}
