package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.FboardDTO;
import factory.FactoryService;

public class FboardDao {
	private static FboardDao dao;
	private FboardDao() {};
	public synchronized static FboardDao getDao() {
		if(dao == null) {
			dao = new FboardDao();
		}
		return dao;
	}
	
	//insert
	public void addFboard(FboardDTO vo) {
		SqlSession ss = FactoryService.getFactory().openSession(true);
		ss.insert("fb.add", vo);
		ss.close();
	}
	
	public List<FboardDTO> listFboard(){
		SqlSession ss = FactoryService.getFactory().openSession();
		List<FboardDTO> list = ss.selectList("fb.list");
		ss.close();
		return list;
	}

}