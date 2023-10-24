package kr.co.ictedu.mvc.dao;

import java.util.List;
import java.util.Map;

import kr.co.ictedu.mvc.dto.BoardVO;

public interface BoardDaoInter {
	public void upboardAdd(BoardVO vo);
	public List<BoardVO> upboardList(Map<String, String> map);
	public int getTotal(Map<String, String> map);
	public BoardVO getItem(int num);
	public void updateItem(BoardVO vo);
	public void deletUpBoard(int num);
}
