package kr.co.ictedu.mvc.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public int getTotal() {
		return ss.selectOne("upboard.totalCount");
	}

	@Override
	public BoardVO getItem(int num) {
		return ss.selectOne("upboard.getItem", num);
	}

	@Override
	public void updateItem(BoardVO vo) {
		System.out.println("제목: " + vo.getTitle());
		System.out.println("컨텐츠: " + vo.getContent());
		System.out.println("이미지: " + vo.getImgn());
		System.out.println("작성자: " + vo.getWriter());
		System.out.println("아이피: " + vo.getReip());
		System.out.println("================");
		ss.selectOne("upboard.updateItem", vo);
	}

	@Override
	public void deletUpBoard(int num) {
		ss.delete("upboard.del", num);
	}
	
}
