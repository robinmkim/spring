package ex3;
/*�н�����Ʈ
 * lazy-init="true" �Ӽ��� ������ �����̳ʿ��� bean�� �ҷ����� ��������
 * �����Ǵ� ���� �ƴ϶� getBean()�� ����ؼ� ȣ���ϴ� �������� ��ü�� �����ϵ��� ������.
 * ���� ����ϰ� ������ �ʴ� bean�� ��쿡 ������
 * */
public class LazyBean {
	private String msg;

	public LazyBean() {
		System.out.println("LazyBean ���� �Ϸ�!");
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String printMsg() {
		return msg;
	}
	
}
