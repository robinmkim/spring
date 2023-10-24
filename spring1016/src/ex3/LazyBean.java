package ex3;
/*학습포인트
 * lazy-init="true" 속성을 스프링 컨테이너에서 bean을 불러오는 시점에서
 * 생성되는 것잉 아니라 getBean()을 사용해서 호출하는 시점에서 객체를 생성하도록 설정함.
 * 자중 비번하게 사용되지 않는 bean일 경우에 적용함
 * */
public class LazyBean {
	private String msg;

	public LazyBean() {
		System.out.println("LazyBean 생성 완료!");
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String printMsg() {
		return msg;
	}
	
}
