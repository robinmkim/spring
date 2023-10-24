package ex2;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class MessageMain {
	
	public static void main(String[] args) {
		usePointCut();
	}
	
	private static void usePointCut() {
		//AOP의 Advice와 Target의 관계를 관리해주는 객체이다.
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
		//Target 객체의 정보를  ProxyFactoryBean 정자
		pfBean.setTarget(new MessageImple());
//		pfBean.addAdvice(new Ex1_MessageAdvice());
//		Message prMessage = (Message) pfBean.getObject();
//		prMessage.PrintText();
		//PointCut 이란 정규 표현식 등을 사용해서, Advice를 적용할 대상을 
		//선택하고자 할 때 사용함.
		//PointCut을 사용해서 test로 시작하는 메서드만 JP로 설정한다.
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		//target이 가지고 있는 메서드 이름을 선별 할 수 있다.
		pointcut.setMappedName("test");
		//Advice와 pointcut의 결합 => Advisors
		pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new Ex1_MessageAdvice()));
		Message prMessage = (Message) pfBean.getObject();
		prMessage.printTest();
		
	}
		
	private static void allJoinpoint() {
		//AOP의 Advice와 Target의 관계를 관리해주는 객체이다.
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
		//Target 객체의 정보를  ProxyFactoryBean 정자
		pfBean.setTarget(new MessageImple());
		//Advice 적용
		pfBean.addAdvice(new Ex1_MessageAdvice());
		//요청해보기
		Message prMessage = (Message) pfBean.getObject();
		prMessage.printTest();
	}
}
