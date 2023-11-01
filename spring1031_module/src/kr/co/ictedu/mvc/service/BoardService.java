package kr.co.ictedu.mvc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.ictedu.mvc.dao.BoardDaoInter;
import kr.co.ictedu.mvc.dto.BoardImageVO;
import kr.co.ictedu.mvc.dto.BoardVO;
import kr.co.ictedu.mvc.dto.BoardVideoVO;

@Service
public class BoardService {
	@Autowired
	private BoardDaoInter dao;
	
	@Transactional
	public void addBoard(BoardVO vo, List<BoardImageVO> list, BoardVideoVO bvvo) {
		dao.boardAdd(vo);
		dao.boardAddImg(list);
		dao.boardAddVideo(bvvo);
	}
	
	public List<BoardVO> boardList(Map<String, String> map){
		return dao.boardList(map);
	}
	
	public int getTotal(Map<String, String> map) {
		return dao.getTotal(map);
	}
	
	public BoardVO boardDetail(int num) {
		BoardVO vo = dao.boardDetail(num);
		List<String> list = vo.getImgName();
		System.out.println(list);
		return vo;
	}
	
	public void boardDelete(int num) {
		dao.boardDelete(num);
	}
	
	public void imgDelete(BoardImageVO bivo) {
		dao.imgDelete(bivo);
	}
	
	public void boardUpdate(BoardVO bvo, BoardImageVO bivo, BoardVideoVO bvvo, List<BoardImageVO> list) {
		
	}
	
}
