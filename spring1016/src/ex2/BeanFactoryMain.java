package ex2;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class BeanFactoryMain {
	public static void main(String[] args) {
		//BeanFactory 최상위 인터페이스, 존재의 이유!
		//1.가장 기본적인 스프링 컨테이너이다. getBean 할때 생성이 된다. 원시적인 기능
		BeanFactory ctx = new XmlBeanFactory(new FileSystemResource("src/ex2/life.xml"));
		//2. getBean을 했을 때 비로소 스프링컨테이너에 생성을 한다.
		LifeInter inter = ctx.getBean("life", LifeInter.class);
		String name = inter.method1();
		System.out.println("name => " + name);
	
	}
}
