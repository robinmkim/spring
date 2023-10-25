package ex1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MySpringContainer {
	public static void main(String[] args) {
		//�������谡 ������ 2��ü�� �̸� ������ �ȴ�. (A, b�� �����ǰ� setter ȣ��Ǿ DI ��)
		ApplicationContext ctx = new GenericXmlApplicationContext("ex1/ex1_ref.xml");
		
		// A�� ã�ƿͼ� B�� �ڿ��� Ȱ���ϴ� �����̴�.
		MyTestA ref = ctx.getBean("testA", MyTestA.class);
		System.out.println(ref.printUseb());
	}
}
