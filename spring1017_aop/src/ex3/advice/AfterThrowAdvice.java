package ex3.advice;

public class AfterThrowAdvice {
	public void commThrow(Exception ew) {
		System.out.println("���� �޽���:" + ew.getMessage());
	}
}
