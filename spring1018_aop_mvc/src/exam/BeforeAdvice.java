package exam;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class BeforeAdvice {
	@Autowired
	LoginTest test; 
	
	@Before("execution(* exam.SecurityImple.*(..))")
	public void myBefore() {
		if(test.isRes()) {
			System.out.println("���� �Ϸ�!");
		} else {
			System.out.println("�α��� ����");
			System.exit(0);
		}
	}
	
//	@Around("execution(* exam.SecurityImple.*(..))")
//	public void myBefore(ProceedingJoinPoint pjp) throws Throwable {
//		if(test.isRes()) {
//			System.out.println("���� �Ϸ�!");
//			pjp.proceed();
//		} else {
//			System.out.println("�α��� ����");
//			return;
//		}
//	}
}
