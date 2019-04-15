package guomanwang.exceptionHandle;

public class CommonReturnType {

	private String status;
	private Object data;
	
	public CommonReturnType(Object data) {
		super();
		this.status = "success";
		this.data = data;
	}
	
	public CommonReturnType(String status, Object data) {
		super();
		this.status = status;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
