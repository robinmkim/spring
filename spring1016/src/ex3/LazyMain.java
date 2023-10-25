package ex3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class LazyMain {
	public static void main(String[] args) {
		ApplicationContext ctx = new GenericXmlApplicationContext("ex3/lazy.xml");
		LazyBean lb1 = ctx.getBean("lazy", LazyBean.class);
		LazyBean lb2 = ctx.getBean("lazy", LazyBean.class);
		//scope="prototype" �Ӽ��� ����ϸ� �̱��濡�� ����
		System.out.println("is it singleton? " + (lb1==lb2));
	}
}
