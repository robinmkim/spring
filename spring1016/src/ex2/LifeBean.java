package ex2;
//life.xml -> <bean id="life" ...
public class LifeBean implements LifeInter{
	private String name;

	public LifeBean() {
		System.out.println("생성자 호출=====>");
	}
	
	public LifeBean(String name) {
		this.name = name;
		System.out.println("생성자 호출====>" + name);
	}

	public void setName(String name) {
		this.name = name;
		System.out.println("setter 호출");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		System.out.println("init 호출");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destroy 호출");
	}

	@Override
	public String method1() {
		// TODO Auto-generated method stub
		return name;
	}

}
