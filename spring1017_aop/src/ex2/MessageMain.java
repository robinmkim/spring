package ex2;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class MessageMain {
	
	public static void main(String[] args) {
		usePointCut();
	}
	
	private static void usePointCut() {
		//AOP�� Advice�� Target�� ���踦 �������ִ� ��ü�̴�.
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
		//Target ��ü�� ������  ProxyFactoryBean ����
		pfBean.setTarget(new MessageImple());
//		pfBean.addAdvice(new Ex1_MessageAdvice());
//		Message prMessage = (Message) pfBean.getObject();
//		prMessage.PrintText();
		//PointCut �̶� ���� ǥ���� ���� ����ؼ�, Advice�� ������ ����� 
		//�����ϰ��� �� �� �����.
		//PointCut�� ����ؼ� test�� �����ϴ� �޼��常 JP�� �����Ѵ�.
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		//target�� ������ �ִ� �޼��� �̸��� ���� �� �� �ִ�.
		pointcut.setMappedName("test");
		//Advice�� pointcut�� ���� => Advisors
		pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new Ex1_MessageAdvice()));
		Message prMessage = (Message) pfBean.getObject();
		prMessage.printTest();
		
	}
		
	private static void allJoinpoint() {
		//AOP�� Advice�� Target�� ���踦 �������ִ� ��ü�̴�.
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
		//Target ��ü�� ������  ProxyFactoryBean ����
		pfBean.setTarget(new MessageImple());
		//Advice ����
		pfBean.addAdvice(new Ex1_MessageAdvice());
		//��û�غ���
		Message prMessage = (Message) pfBean.getObject();
		prMessage.printTest();
	}
}
