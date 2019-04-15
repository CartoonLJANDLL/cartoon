package guomanwang.exceptionHandle;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import guomanwang.exception.BusinessException;
import guomanwang.exception.ErrorType;
import net.sf.json.JSONObject;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Object exceptionHandler(HttpServletRequest request,HttpServletResponse response,Exception e) {
		Map<String, String> dataObject = new HashMap<>();
		CommonReturnType commonReturnType = new CommonReturnType("0", dataObject);
		if( e instanceof BusinessException) {
			BusinessException exception = (BusinessException) e;
			dataObject.put("errCode", exception.getErrCode());
			dataObject.put("errMsg", exception.getErrMsg());
		}else {
			dataObject.put("errCode", ErrorType.UNKNOW_EXCEPTION.getErrCode());
			dataObject.put("errMsg", ErrorType.UNKNOW_EXCEPTION.getErrMsg());
		}
		JSONObject jsonObject = changeToJson(commonReturnType);
		return jsonObject;
	}
	
	public JSONObject changeToJson( CommonReturnType commonReturnType) {
		JSONObject jsonObject = new JSONObject();
		Map<String, String> dataObject = (Map<String, String>)commonReturnType.getData();
		jsonObject.put("code", 0);
		jsonObject.put("errCode", dataObject.get("errCode"));
		jsonObject.put("errMsg", dataObject.get("errMsg"));
		/*jsonObject.put("msg", "错误代码" + dataObject.get("errCode") + "错误信息" + dataObject.get("errMsg"));*/
		return jsonObject;
	}
}
