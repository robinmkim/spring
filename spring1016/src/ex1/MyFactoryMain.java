package ex1;
//�������谡 ������ 2��ü ����
//A���� B�� �ּҸ� ������ ��, ��ǥ �޼��� A�� printUseb() -B�� �޼��带 ������- ȣ���ؼ�
//�Ϸ�
public class MyFactoryMain {
	public static void main(String[] args) {
		MyTestA refa = new MyTestA();
		//ResourceB refb = new ResourceB();
		//���� ���� ����
		refa.setB(new ResourceB());
		System.out.println(refa.printUseb());;
	}
}
