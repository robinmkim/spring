package ex3.advice;
//������ɻ���
//� �����Ͻ� ������ ���� Ŭ������ �ҿ�ð�, �����û��� �����ϰ� ���V �� ����  Around Advice
//Around Advice - joinpoint ��ü�� �ݵ�� �޼����� ���ڷ� ���� �ؾ� �Ѵ�.
//�ݵ�� ProceedingJoinPoint ��ü�� ���ڰ����� �����ؾ� �Ѵ�.

import org.aspectj.lang.ProceedingJoinPoint;

public class CheckAdvice {
	
	public void checkTime(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		pjp.proceed();
		long end = System.currentTimeMillis();
		System.out.println("�ҿ�ð� :" + (end - start));
	}
}
