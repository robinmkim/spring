package ex2;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class BeanFactoryMain {
	public static void main(String[] args) {
		//BeanFactory �ֻ��� �������̽�, ������ ����!
		//1.���� �⺻���� ������ �����̳��̴�. getBean �Ҷ� ������ �ȴ�. �������� ���
		BeanFactory ctx = new XmlBeanFactory(new FileSystemResource("src/ex2/life.xml"));
		//2. getBean�� ���� �� ��μ� �����������̳ʿ� ������ �Ѵ�.
		LifeInter inter = ctx.getBean("life", LifeInter.class);
		String name = inter.method1();
		System.out.println("name => " + name);
	
	}
}
