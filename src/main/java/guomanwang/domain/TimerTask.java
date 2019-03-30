package guomanwang.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//执行Python程序的方法
public class TimerTask {
	public static int autoscarynews(String file) throws IOException, InterruptedException {
		Process proc;
		try {
			proc = Runtime.getRuntime().exec(file);// 执行py文件
			//用输入输出流来截取结果
			BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
				return  Integer.valueOf(line);
			}
			in.close();
			proc.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 0;
	  }
}
