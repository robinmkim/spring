package ex2;

import java.util.LinkedList;

public class MessageImple2 implements Message{
	private String message;
	
	public MessageImple2() {
		message = "안녕하세요, 첫 AOP 입니다.";
	}

	@Override
	public void print() {
		LinkedList<String> ar2 = new LinkedList();
		for(int i=0; i< 100000; i++) {
			if (i%5 == 0) {
				ar2.add("ict" + i);
			} else {
				ar2.add("spring aop" + i);
			}
		}
		System.out.println("Linked List 에 들어간 size: " + ar2.size());
		for(String e: ar2) {
			String msg= e;
			if(e.equals("ict")) {
				System.out.println(msg);
			}
		}
	}

	@Override
	public String printTest() {
		for(int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();;
			}
		}
		return "hello";
	}

	@Override
	public String message() {
		// TODO Auto-generated method stub
		return message;
	}

	@Override
	public void test() {
		System.out.println("TEST~~~~~~~~~~~~~~~business logic");
		
	}

	@Override
	public void test2() {
		System.out.println("TEST2~~~~~~~~~~~~~~~business logic");
		
	}

	@Override
	public void test3(String msg) {
		System.out.println("TEST3~~~~~~~~~~~~~~~business logic");
		
	}

}
