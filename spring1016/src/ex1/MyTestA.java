package ex1;

public class MyTestA {
	//멤버필드로 받을 객체를 선언!
	private ResourceB b;
	public MyTestA() {
		// has a 관계, 결합도가 너무 높다
		System.out.println("MyTestA 생성");
	}
	// 외부세어 생성한 후에 그 주소를 주입 받는다.
	public void setB(ResourceB b) {
		this.b = b;
	}
	
	public String printUseb() {
		StringBuilder sb = new StringBuilder();
		sb.append("b를 반환 받은 값: " + (b.res() * 2)).append("<br>");
		sb.append("사용자 이름: ").append(b.getUname());
		return sb.toString();
	}
}
