package kr.co.ictedu.mvc.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ictedu.mvc.dto.BoardCommDTO;
import kr.co.ictedu.mvc.dto.BoardVO;

@Repository
public class BoardDao implements BoardDaoInter{

	@Autowired
	private SqlSessionTemplate ss;
	
	@Override
	public void upboardAdd(BoardVO vo) {
		System.out.println(vo.getTitle());
		System.out.println(vo.getContent());
		System.out.println(vo.getImgn());
		System.out.println(vo.getWriter());
		System.out.println(vo.getReip());
		System.out.println("================");
		ss.insert("upboard.add", vo);
	}

	@Override
	public List<BoardVO> upboardList(Map<String, String> map) {
		System.out.println("map.begin"+map.get("begin"));
		System.out.println("map.end" + map.get("end"));
		
		List<BoardVO> list = ss.selectList("upboard.list", map);
		return list;
	}

	@Override
	public int getTotal(Map<String, String> map) {
		return ss.selectOne("upboard.totalCount", map);
	}

	@Override
	public BoardVO getItem(int num) {
		return ss.selectOne("upboard.getItem", num);
	}

	@Override
	public void updateItem(BoardVO vo) {
		ss.selectOne("upboard.updateItem", vo);
	}

	@Override
	public void deletUpBoard(int num) {
		ss.delete("upboard.del", num);
	}

	@Override
	public void updateHit(int num) {
		ss.update("upboard.hit", num);
		
	}

	@Override
	public void commAdd(BoardCommDTO vo) {
		ss.insert("upboard.commAdd", vo);
	}

	@Override
	public List<BoardCommDTO> upcommList(int ucode) {
		return ss.selectList("upboard.commList", ucode);
	}
	
	@Override
	public List<BoardCommDTO> listCommBoard(Map<String , String> map) {
		return ss.selectList("upboard.listComm", map);
	}

	@Override
	public int getTotalComm(Map<String, String> map) {
		
		return ss.selectOne("upboard.totalCountComm", map);
	}
	
}
