package ex5_collection;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex2_Map {
	private Map<String, String> map;

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public static void main(String[] args) {
		ApplicationContext ctx = new GenericXmlApplicationContext("ex5_collection/map.xml");
		Ex2_Map ref = ctx.getBean("map", Ex2_Map.class);
		Map<String, String> maps = ref.getMap();
		System.out.println(maps);
	}
	
	
}
