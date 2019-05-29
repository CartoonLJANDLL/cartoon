package guomanwang.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

public class ValiMsgUtils {

	//账号
	public static final String ACCOUNT_SID = "6b9648edeb984bdb9ec9c9825a1e01eb";

	//TOKEN
	public static final String AUTH_TOKEN = "c1f8b8de15184a9d93841bbc4bc5fc3b";
	
	//签名
	public static final String SIGNATURE = "纵横国漫";
	
	//模板
	public static final String Content = "您的验证码为：$，请于3分钟内正确输入，如非本人操作，请忽略此短信。";
	
	//可以不修改
	public static final String RESP_DATA_TYPE = "json";	//JSON或XML
	public static final String operation = "/industrySMS/sendSMS";
	public static final String BASE_URL = "https://api.miaodiyun.com/20150822";
	
	private ValiMsgUtils(){}
	
	/**
	 * 生成指定位数随机数
	 * @return 
	 */
	static Random random = new Random();
	public static String randNum(int number){
		String result ="";
		for(int i=0;i<number;i++){
			result += random.nextInt(10);
		}
		return result;
	}
	
	/**
	 * 发送指定位数短信验证码
	 */
	public static String send(String phone,int length){
		String num = randNum(length);
		return execute(phone,num)? num : "0";
	}
	
	/**
	 * 发送6位数短信验证码
	 */
	public static String send(String phone){
		System.out.println("phone:"+phone);
		return send(phone,6);
	}
	
	/**
	 * 发送验证码
	 */
	public static boolean execute(String phone,String num){
		String tmpSmsContent = null;
		String smsContent = "【" + SIGNATURE + "】" + Content.replace("$", num);
	    try{
	      tmpSmsContent = URLEncoder.encode(smsContent, "UTF-8");
	    }catch(Exception e){
	    	
	    }
	    String url = BASE_URL + operation;
	    String body = "accountSid=" + ACCOUNT_SID + "&to=" + phone + "&smsContent=" + tmpSmsContent + createCommonParam();
	    
	    // 提交请求
	    String result = post(url, body);
	    //System.out.println("result:" + System.lineSeparator() + result);
	    return result.indexOf("请求成功")!=-1 ? true : false;
	}
	
	/**
	 * 构造通用参数timestamp、sig和respDataType
	 * 
	 * @return
	 */
	public static String createCommonParam(){
		// 时间戳
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timestamp = sdf.format(new Date());

		// 签名
		String sig = DigestUtils.md5Hex(ACCOUNT_SID + AUTH_TOKEN + timestamp);
		
		return "&timestamp=" + timestamp + "&sig=" + sig + "&respDataType=" + RESP_DATA_TYPE;
	}

	/**
	 * post请求
	 * 
	 * @param url
	 *            功能和操作
	 * @param body
	 *            要post的数据
	 * @return
	 * @throws IOException
	 */
	public static String post(String url, String body){
		//System.out.println("url:" + System.lineSeparator() + url);
		//System.out.println("body:" + System.lineSeparator() + body);

		String result = "";
		try{
			OutputStreamWriter out = null;
			BufferedReader in = null;
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();

			// 设置连接参数
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(20000);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// 提交数据
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(body);
			out.flush();

			// 读取返回数据
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line = "";
			boolean firstLine = true; // 读第一行不加换行符
			while ((line = in.readLine()) != null){
				if (firstLine){
					firstLine = false;
				} else{
					result += System.lineSeparator();
				}
				result += line;
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 回调测试工具方法
	 * 
	 * @param url
	 * @param reqStr
	 * @return
	 */
	public static String postHuiDiao(String url, String body){
		String result = "";
		try{
			OutputStreamWriter out = null;
			BufferedReader in = null;
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();

			// 设置连接参数
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(20000);

			// 提交数据
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(body);
			out.flush();

			// 读取返回数据
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line = "";
			boolean firstLine = true; // 读第一行不加换行符
			while ((line = in.readLine()) != null){
				if (firstLine){
					firstLine = false;
				} else{
					result += System.lineSeparator();
				}
				result += line;
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
}
