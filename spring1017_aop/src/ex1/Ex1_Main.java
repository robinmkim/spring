package ex1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex1_Main {
	public static void main(String[] args) {
		ApplicationContext ctx = new GenericXmlApplicationContext("ex1/ex1_res.xml");
		Ex1_Resource ref = ctx.getBean("ex1", Ex1_Resource.class);
		System.out.println(ref.getRes().resource1());
	}
}
