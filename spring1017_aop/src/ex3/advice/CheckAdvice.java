package ex3.advice;
//공통관심사항
//어떤 비지니스 로직을 가진 클래스가 소요시간, 지연시산을 측정하고 심픙 때 사용될  Around Advice
//Around Advice - joinpoint 객체가 반드시 메서드의 인자로 선언 해야 한다.
//반드시 ProceedingJoinPoint 객체를 인자값으로 지정해야 한다.

import org.aspectj.lang.ProceedingJoinPoint;

public class CheckAdvice {
	
	public void checkTime(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		pjp.proceed();
		long end = System.currentTimeMillis();
		System.out.println("소요시간 :" + (end - start));
	}
}
