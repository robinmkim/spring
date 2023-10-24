package ex5_collection;

import java.util.Map;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex3_Properties {
	private Properties prop;

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new GenericXmlApplicationContext("ex5_collection/prop.xml");
		Ex3_Properties ref = ctx.getBean("prop", Ex3_Properties.class);
		Properties pr = ref.getProp();
		for(Map.Entry e : pr.entrySet()) {
			System.out.println(e.getKey()+":"+e.getValue());
		}
	}
}
