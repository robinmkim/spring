package ex1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {

	public static void main(String[] args) {
		//spring container�� ��ϵ� bean�� getBean()�ؼ� ������ ������ ���� ��.
		ApplicationContext ctx = new GenericXmlApplicationContext("ex1/tv.xml");
		TV tvl = ctx.getBean("ltv", TV.class);
		tvl.powerOn();
		tvl.powerOff();
		tvl.volumeUp();
		tvl.volumeDown();
		
		
		TV tvs = ctx.getBean("stv", TV.class);
		tvs.powerOn();
		tvs.powerOff();
		tvs.volumeDown();
		tvs.volumeUp();
	}
	
}
