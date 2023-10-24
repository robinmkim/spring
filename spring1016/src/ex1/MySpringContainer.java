package ex1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MySpringContainer {
	public static void main(String[] args) {
		//의존관계가 형성된 2객체가 미리 생성이 된다. (A, b는 생성되고 setter 호출되어서 DI 됨)
		ApplicationContext ctx = new GenericXmlApplicationContext("ex1/ex1_ref.xml");
		
		// A를 찾아와서 B의 자원을 활용하는 개념이다.
		MyTestA ref = ctx.getBean("testA", MyTestA.class);
		System.out.println(ref.printUseb());
	}
}
