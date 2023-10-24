package ex1;

public class SamsungTV implements TV{
	private String msg;
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public void powerOn() {
		System.out.println(msg + " SumsungTV--전원 켠다");
	}

	@Override
	public void powerOff(){
		System.out.println(msg + " SumsungTV--전원 끈다");	
	}

	@Override
	public void volumeUp() {
		System.out.println(msg + " SumsungTV-- 볼륨을 올린다");
	}

	@Override
	public void volumeDown() {
		System.out.println(msg + " SumsungTV--볼륨을 내리다");
	}
}
