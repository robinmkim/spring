package kr.co.ictedu.mvc.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ictedu.mvc.dto.BoardCommVO;
import kr.co.ictedu.mvc.dto.BoardImageVO;
import kr.co.ictedu.mvc.dto.BoardVO;
import kr.co.ictedu.mvc.dto.BoardVideoVO;
@Repository
public class BoardDao implements BoardDaoInter{
	
	@Autowired
	private SqlSessionTemplate ss;
	
	@Override
	public void boardAdd(BoardVO bvo) {
		ss.insert("board.add", bvo);
		
	}

	@Override
	public void boardAddImg(List<BoardImageVO> list) {
		ss.insert("board.addimg", list);
	}

	@Override
	public void boardAddVideo(BoardVideoVO bvvo) {
		ss.insert("board.addvideo", bvvo);
	}

	@Override
	public List<BoardVO> boardList(Map<String, String> map) {
		
		return ss.selectList("board.list", map);
	}

	@Override
	public int getTotal(Map<String, String> map) {
		// TODO Auto-generated method stub
		return ss.selectOne("board.totalCount", map);
	}

	
	
}
