package ex2;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class Ex1_MessageAdvice2 implements MethodInterceptor {
	//MethodInvocation invocation 타겟의 메서드의 호출 정보를 가지고 있는 객체를 인자로 주입 받음
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		long start = System.currentTimeMillis();
		Object ref = invocation.proceed();
		long end = System.currentTimeMillis();
		System.out.println("소요시간 :" + (end - start));
		return ref;
	}
	
}
