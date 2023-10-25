package ex1;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Ex1_Resource {
	//@Autowired
	//java ���� �������ִ� �⺻ ���̺귯��
	//name alias�� ����ؼ� �ҷ��ͼ� �ڵ� �� ���⸦ �� *****
	//@Resource, @Qualifier("bb") ������ byType�� ��� ���ľ ������.
	//�׷����� @Resource(name="resn1"),@Qualifier("bb")�� ��쿡�� Alias�� ������.
	//������ �̷����� ���� @Resource(name="resn1") �����ε� ����ϱ�  ������ �̷��� ����ϴ� ���� �Ϲ����̴�.
	@Resource
	@Qualifier("bb")
	private Ex1_MyResource res;
	//ex1_res.xml
	public Ex1_MyResource getRes() {
		return res;
	}

//	public void setRes(Ex1_MyResource res) {
//		this.res = res;
//	}
}
