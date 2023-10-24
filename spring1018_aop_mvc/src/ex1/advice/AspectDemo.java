package ex1.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class AspectDemo {
	@Autowired
	private MyPublic mypublic;

	@Before("execution(* ex1.DaoImple.*(..))")
	public void myBefore() {
		System.out.println("Today: " + mypublic.todayMethod());
	}

	@AfterReturning(pointcut = "execution(* ex1.DaoImple.second(..))", returning = "ret")
	public void myReturnMethod(JoinPoint jp, Object ret) {
		String namev = jp.getSignature().getName();

		System.out.println("Name: " + namev);
		System.out.println("반환값: " + ret);
		System.out.println("==========================");
	}
	
	@Around("execution(* ex1.DaoImple.first*(..))")
	public void checkTime(ProceedingJoinPoint pjp) throws Throwable{
		long start = System.currentTimeMillis();
		pjp.proceed();
		long end = System.currentTimeMillis();
		System.out.println("소요시간 :" + (end - start));
	}
}
