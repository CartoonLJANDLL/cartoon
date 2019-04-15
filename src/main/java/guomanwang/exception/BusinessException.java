package guomanwang.exception;

public class BusinessException extends Exception implements CommonError{
	
	/**
	 * 
	 */
	private CommonError commonError;
	
	public BusinessException(CommonError commonError) {
		super();
		this.commonError = commonError;
	}
	
	public BusinessException(CommonError commonError,String errMsg) {
		super();
		this.commonError = commonError;
		this.commonError.setErrMsg(errMsg);
	}

	@Override
	public String getErrCode() {
		// TODO Auto-generated method stub
		return commonError.getErrCode();
	}

	@Override
	public String getErrMsg() {
		// TODO Auto-generated method stub
		return commonError.getErrMsg();
	}

	@Override
	public CommonError setErrMsg(String errMsg) {
		// TODO Auto-generated method stub
		return commonError.setErrMsg(errMsg);
	}

}
