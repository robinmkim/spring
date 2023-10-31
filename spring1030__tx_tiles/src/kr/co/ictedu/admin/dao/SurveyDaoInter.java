package kr.co.ictedu.admin.dao;

import java.util.List;

import kr.co.ictedu.mvc.dto.SurveyContentVO;
import kr.co.ictedu.mvc.dto.SurveyVO;

public interface SurveyDaoInter {
	public void addSurvey(SurveyVO vo);
	public void addSurveyContent(List<SurveyContentVO> list);
	public List<SurveyVO> listSurvey();
	public SurveyVO adminDetail(int num);
}
