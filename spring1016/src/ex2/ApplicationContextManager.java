package ex2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class ApplicationContextManager {
	public static void main(String[] args) {
		/*
		 * �⺻ ������ ȣ��====>
		 * setter ȣ��(DI)
		 * init ȣ��***** => di�� �� ������ �ڿ��� �ʱ�ȭ �� �������� ��� �� �� �ִ�.
		 * */
		ApplicationContext ctx = new GenericXmlApplicationContext("ex2/life.xml");
		//2.getBEan�� ���� �� ��μ� �����������̳ʿ� ������ �Ѵ�.
		//LifeInter inter = ctx.getBean("life",LifeInter.class);
		//String name = inter.method1();
		//System.out.println("name ==> " + name);
	}
}
