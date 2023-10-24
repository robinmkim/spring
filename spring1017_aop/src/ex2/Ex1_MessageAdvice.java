package ex2;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class Ex1_MessageAdvice implements MethodInterceptor {
	//MethodInvocation invocation 타겟의 메서드의 호출 정보를 가지고 있는 객체를 인자로 주입 받음
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("안녕하세요"); //선행
		Object ref = invocation.proceed();
		System.out.println("안녕히계세요"); //후
		return ref;
	}
	
}
