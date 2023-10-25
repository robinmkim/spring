package ex2;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class Ex1_MessageAdvice implements MethodInterceptor {
	//MethodInvocation invocation Ÿ���� �޼����� ȣ�� ������ ������ �ִ� ��ü�� ���ڷ� ���� ����
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("�ȳ��ϼ���"); //����
		Object ref = invocation.proceed();
		System.out.println("�ȳ����輼��"); //��
		return ref;
	}
	
}
