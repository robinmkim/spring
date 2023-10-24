package ex3.advice;

public class AfterThrowAdvice {
	public void commThrow(Exception ew) {
		System.out.println("예외 메시지:" + ew.getMessage());
	}
}
