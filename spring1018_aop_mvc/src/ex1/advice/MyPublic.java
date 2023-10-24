package ex1.advice;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyPublic {
	public String todayMethod() {
		SimpleDateFormat f= new SimpleDateFormat("yyyy-MM-dd");
		return f.format(new Date());
	}
}
