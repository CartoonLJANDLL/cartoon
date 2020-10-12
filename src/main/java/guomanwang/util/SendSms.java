package guomanwang.util;

import java.util.Random;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class SendSms {
	public static final String AccessKeyID="";
	
	public static final String 	AccessKeySecret="";
	
	public static final String 	SignName="纵横国漫";
	
	public static final String 	TemplateCode="SMS_189825678";

	
	/**
	 * 生成指定位数随机数
	 * @return 
	 */
	static Random random = new Random();
	public static String randNum(int number){
		StringBuilder result = new StringBuilder();
		for(int i=0;i<number;i++){
			result.append(random.nextInt(10));
		}
		return result.toString();
	}
	/**
	 * 发送6位数短信验证码
	 */
	public static String sendsms(String phone,int length){
		System.out.println("phone:"+phone);
		String num = randNum(length);
		return send(phone,num)?num:"0";
	}
	/**
	 * 发送6位数短信验证码
	 */
	public static String sendsms(String phone){
		System.out.println("phone:"+phone);
		return sendsms(phone,6);
	}
	public static boolean send(String phone,String num) {
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",AccessKeyID, AccessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", SignName);
        request.putQueryParameter("TemplateCode", TemplateCode);
        request.putQueryParameter("TemplateParam", "{\"code\":\""+num+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return true;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
		return false;
		
	}
}
