package guomanwang.exception;

public enum ErrorType implements CommonError{
	//10000开始通用异常
	UNKNOW_EXCEPTION("10001","未知错误"),
	PARAMETER_VALUE_ERROR("10002","参数错误"),
	
	//20000开始是用户类异常
	NOT_EXIEST_USER("20001","用户不存在"),
	
	//30000开始是数据库操作异常
	DATABASE_INSERT_ERROR("30001", "数据库插入错误"),
	DATABASE_UPDATE_ERROR("30002", "数据库更新错误"),
	;
	private String errCode;
	private String errMsg;
	
	
	private ErrorType(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	@Override
	public String getErrCode() {
		// TODO Auto-generated method stub
		return this.errCode;
	}

	@Override
	public String getErrMsg() {
		// TODO Auto-generated method stub
		return this.errMsg;
	}

	@Override
	public CommonError setErrMsg(String errMsg) {
		this.errMsg = errMsg;
		return this;
	}

}
