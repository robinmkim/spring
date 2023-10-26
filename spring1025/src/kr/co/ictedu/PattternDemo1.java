package kr.co.ictedu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PattternDemo1 {
	public static void main(String[] args) {
		String userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 17_0_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.0.1 Mobile/15E148 Safari/604.1";
		
		Pattern mp = Pattern.compile("(Mobile|Android|iPhone|iPod|Macintosh)");
		Matcher mc = mp.matcher(userAgent);
		boolean res = mc.find();
		System.out.println(res);
		
		if(res) {
			System.out.println("모바일에서 접속");
		} else {
			System.out.println("PC에서 접속");
		}
		Pattern mp1 = Pattern.compile("(Windows NT [\\d.]+|Android [\\d.]+|iPhone)");
		Matcher mc1 = mp1.matcher(userAgent);
		if(res) {
			String device = mc.group();
			System.out.println(device);
		}
	}
}
