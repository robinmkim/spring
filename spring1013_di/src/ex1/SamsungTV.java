package ex1;

public class SamsungTV implements TV{
	private String msg;
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public void powerOn() {
		System.out.println(msg + " SumsungTV--���� �Ҵ�");
	}

	@Override
	public void powerOff(){
		System.out.println(msg + " SumsungTV--���� ����");	
	}

	@Override
	public void volumeUp() {
		System.out.println(msg + " SumsungTV-- ������ �ø���");
	}

	@Override
	public void volumeDown() {
		System.out.println(msg + " SumsungTV--������ ������");
	}
}
