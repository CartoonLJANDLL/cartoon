package guomanwang.service;

public interface AccessService {
    //根据id即权限等级获得无资格访问的网址后缀
	public String selectAccessById(int accessId) throws Exception;
}
