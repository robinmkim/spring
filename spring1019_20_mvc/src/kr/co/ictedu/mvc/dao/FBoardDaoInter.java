package kr.co.ictedu.mvc.dao;

import java.util.List;

import kr.co.ictedu.mvc.dto.FboardDTO;

public interface FBoardDaoInter {
	public void addFboard(FboardDTO vo);
	public List<FboardDTO> listFboard();
	public void updateHit(int num);
	public FboardDTO detailFboard(int num);
	public void deleteFboard(int num);
	public void updateFboard(FboardDTO vo);
}