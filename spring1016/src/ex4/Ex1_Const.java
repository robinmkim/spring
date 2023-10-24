package ex4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex1_Const {
	private int num;
	private String msg;
	private String code;
	
	//생성자 오버로딩
	public Ex1_Const(int num) {
		this.num = num;
		System.out.println("int형 호출");
	}
	public Ex1_Const(String msg) {
		this.msg = msg;
		System.out.println("String형 호출");
	}
	public Ex1_Const(int num, String code) {
		this.num = num;
		this.code = code;
		System.out.println("int, String형 호출");
	}
	//비지니스 로직
	public String printConstRes() {
		StringBuffer sb = new StringBuffer();
		sb.append("num: " + num);
		sb.append("msg: " + msg);
		sb.append("code: " + code);
		return sb.toString();
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new GenericXmlApplicationContext("ex4/ex1_const.xml"); 
	}
}
