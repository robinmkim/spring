package ex3.advice;

import org.aspectj.lang.JoinPoint;

public class NameReturnAdvice {
	//���� �� �������� ������ �����ؾ� ��!,  Around �� �ƴ� JoinPoint�� ������ �����ϴ�.
	// Object ret ��ȯ�Ǵ� Ÿ�԰� ������, Ư�� �������� ������ ���ƾ� ��*****
	public void myReturnMethod(JoinPoint jp, Object ret) {
		String namev = jp.getSignature().getName();
		
		System.out.println("Name: " + namev);
		System.out.println("��ȯ��: "+ ret);
		System.out.println("==========================");
	}
}
