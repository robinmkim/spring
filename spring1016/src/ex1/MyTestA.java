package ex1;

public class MyTestA {
	//����ʵ�� ���� ��ü�� ����!
	private ResourceB b;
	public MyTestA() {
		// has a ����, ���յ��� �ʹ� ����
		System.out.println("MyTestA ����");
	}
	// �ܺμ��� ������ �Ŀ� �� �ּҸ� ���� �޴´�.
	public void setB(ResourceB b) {
		this.b = b;
	}
	
	public String printUseb() {
		StringBuilder sb = new StringBuilder();
		sb.append("b�� ��ȯ ���� ��: " + (b.res() * 2)).append("<br>");
		sb.append("����� �̸�: ").append(b.getUname());
		return sb.toString();
	}
}
