package ex1;
/*
 * 결합도(Coupling)가 높은 프로그램
 * -결합도란 클래스가 다른 클래스와 얼마나 많이 연결 되어 있는지를 나타내는 표현이다.
 * -결합도가 높은 프로그램은 유지 보수가 어렵고, 관리도 어렵다.
 * */

public class LgTV implements TV{
	//property를 등록
	private String msg;
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public void powerOn() {
		System.out.println(msg + " LgTV--전원 켠다");
	}
  
	@Override
	public void powerOff(){
		System.out.println(msg + " LgTV--전원 끈다");	
	}

	@Override
	public void volumeUp() {
		System.out.println(msg + " LgTV-- 볼륨을 올린다");
	}

	@Override
	public void volumeDown() {
		System.out.println(msg + " LgTV--볼륨을 내리다");
	}

}
