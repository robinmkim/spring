package ex1;

public class ResourceB {
	private String uname;
	public ResourceB() {
		System.out.println("Resource가 생성됨");
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int res() { //특정 수를 돌려주는 메서드
		return 100000;
	}
}

 