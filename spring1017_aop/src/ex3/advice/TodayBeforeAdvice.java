package ex3.advice;

import org.springframework.beans.factory.annotation.Autowired;

import ex3.exam.MyPublic;

public class TodayBeforeAdvice {
	@Autowired
	private MyPublic date;
	
	public void myBeforeMethod() {
		System.out.println(date.todayMethod());
	}
}
