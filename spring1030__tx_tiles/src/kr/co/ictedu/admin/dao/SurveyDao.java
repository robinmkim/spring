package kr.co.ictedu.admin.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ictedu.mvc.dto.SurveyContentVO;
import kr.co.ictedu.mvc.dto.SurveyVO;

@Repository
public class SurveyDao implements SurveyDaoInter {

	@Autowired
	private SqlSessionTemplate ss;

	@Override
	public void addSurvey(SurveyVO vo) {
		ss.insert("survey.add", vo);
	}

	@Override
	public void addSurveyContent(List<SurveyContentVO> list) {
		ss.insert("survey.addcontent", list);
	}

	@Override
	public List<SurveyVO> listSurvey() {
		return ss.selectList("survey.surveylist");
	}

	@Override
	public SurveyVO adminDetail(int num) {
		SurveyVO vo = ss.selectOne("survey.admindetail", num);
		// °Ë¼ö
		List<SurveyContentVO> list = vo.getSurvey();
		System.out.println("SurveyContentVO size: " + list.size());

		return vo;
	}

	@Override
	public void deletes(int num) {
		ss.delete("board.delete", num);

	}

}
