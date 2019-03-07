package guomanwang.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TimerTask {
	public static void autoscarynews(String file) throws IOException, InterruptedException {
		System.out.println("开始执行动漫资讯抓取任务");
		Process proc;
		try {
			proc = Runtime.getRuntime().exec(file);// 执行py文件
			//用输入输出流来截取结果
			BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
			in.close();
			proc.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	  }
}
