package ex5_collection;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex1_ListDemo {
	private List<String> stlist;

	public List<String> getStlist() {
		return stlist;
	}

	public void setStlist(List<String> stlist) {
		this.stlist = stlist;
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new GenericXmlApplicationContext("ex5_collection/list.xml");
		Ex1_ListDemo ref = ctx.getBean("list", Ex1_ListDemo.class);
		List<String> list = ref.getStlist();
		System.out.println(list);
	}
}
