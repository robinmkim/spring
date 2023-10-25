package ex1;
/*
 * ���յ�(Coupling)�� ���� ���α׷�
 * -���յ��� Ŭ������ �ٸ� Ŭ������ �󸶳� ���� ���� �Ǿ� �ִ����� ��Ÿ���� ǥ���̴�.
 * -���յ��� ���� ���α׷��� ���� ������ ��ư�, ������ ��ƴ�.
 * */

public class LgTV implements TV{
	//property�� ���
	private String msg;
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public void powerOn() {
		System.out.println(msg + " LgTV--���� �Ҵ�");
	}
  
	@Override
	public void powerOff(){
		System.out.println(msg + " LgTV--���� ����");	
	}

	@Override
	public void volumeUp() {
		System.out.println(msg + " LgTV-- ������ �ø���");
	}

	@Override
	public void volumeDown() {
		System.out.println(msg + " LgTV--������ ������");
	}

}
