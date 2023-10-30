package kr.co.ictedu.mvc.dao;

import java.util.List;
import java.util.Map;

import kr.co.ictedu.mvc.dto.BoardCommDTO;
import kr.co.ictedu.mvc.dto.BoardVO;

public interface BoardDaoInter {
	public void upboardAdd(BoardVO vo);
	public List<BoardVO> upboardList(Map<String, String> map);
	public int getTotal(Map<String, String> map);
	public BoardVO getItem(int num);
	public void updateItem(BoardVO vo);
	public void updateHit(int num);
	public void deletUpBoard(int num);
	//´ñ±Û
	public void commAdd(BoardCommDTO vo);
	public List<BoardCommDTO> upcommList(int ucode);
	public List<BoardCommDTO> listCommBoard(Map<String, String> map);
	public int getTotalComm(Map<String, String> map);
}
