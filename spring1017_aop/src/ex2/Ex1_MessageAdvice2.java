package ex2;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class Ex1_MessageAdvice2 implements MethodInterceptor {
	//MethodInvocation invocation Ÿ���� �޼����� ȣ�� ������ ������ �ִ� ��ü�� ���ڷ� ���� ����
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		long start = System.currentTimeMillis();
		Object ref = invocation.proceed();
		long end = System.currentTimeMillis();
		System.out.println("�ҿ�ð� :" + (end - start));
		return ref;
	}
	
}
