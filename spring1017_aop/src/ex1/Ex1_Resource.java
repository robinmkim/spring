package ex1;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Ex1_Resource {
	//@Autowired
	//java 에서 제공해주는 기본 라이브러리
	//name alias를 사용해서 불러와서 자동 빈 묶기를 함 *****
	//@Resource, @Qualifier("bb") 적용한 byType일 경우 수식어를 따른다.
	//그렇지만 @Resource(name="resn1"),@Qualifier("bb")일 경우에는 Alias를 따른다.
	//원리는 이렇지만 보통 @Resource(name="resn1") 만으로도 충분하기  때문에 이렇게 사용하는 것이 일반적이다.
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
