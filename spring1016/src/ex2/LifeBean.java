package ex2;
//life.xml -> <bean id="life" ...
public class LifeBean implements LifeInter{
	private String name;

	public LifeBean() {
		System.out.println("������ ȣ��=====>");
	}
	
	public LifeBean(String name) {
		this.name = name;
		System.out.println("������ ȣ��====>" + name);
	}

	public void setName(String name) {
		this.name = name;
		System.out.println("setter ȣ��");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		System.out.println("init ȣ��");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destroy ȣ��");
	}

	@Override
	public String method1() {
		// TODO Auto-generated method stub
		return name;
	}

}
