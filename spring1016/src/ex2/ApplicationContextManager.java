package ex2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class ApplicationContextManager {
	public static void main(String[] args) {
		/*
		 * 기본 생성자 호출====>
		 * setter 호출(DI)
		 * init 호출***** => di가 된 이후의 자원을 초기화 할 목적으로 사용 될 수 있다.
		 * */
		ApplicationContext ctx = new GenericXmlApplicationContext("ex2/life.xml");
		//2.getBEan을 했을 때 비로소 스프링컨테이너에 생성을 한다.
		//LifeInter inter = ctx.getBean("life",LifeInter.class);
		//String name = inter.method1();
		//System.out.println("name ==> " + name);
	}
}
