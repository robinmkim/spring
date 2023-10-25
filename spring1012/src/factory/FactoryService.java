package factory;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class FactoryService {

	// SqlSessionFactory�� ����ؼ� MyBatis�� ȯ�漳��(config.xml)�� �о�ͼ�
	// SqlSession�� ���ؼ� mapper.xml�� ������ SQL���� ���� ���� �����ϰų� �ޱ� ���� ����
	private static SqlSessionFactory factory;

	static {
		try (Reader reader = Resources.getResourceAsReader("config/config.xml");) {
			factory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getFactory() {
		return factory;
	}

}