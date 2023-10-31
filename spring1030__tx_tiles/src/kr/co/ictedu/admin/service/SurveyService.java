package kr.co.ictedu.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.ictedu.admin.dao.SurveyDao;
import kr.co.ictedu.admin.dao.SurveyDaoInter;
import kr.co.ictedu.mvc.dto.SurveyContentVO;
import kr.co.ictedu.mvc.dto.SurveyVO;

@Service
public class SurveyService {
	//Dao�� ���� ó���ϱ� ���� ����
	@Autowired
	private SurveyDaoInter surveyDao;
	//AOP - ����, �α�, Ʈ�����
	//Transaction ó���� �ϱ� ���ؼ�
	//�󿡤��� ���� <tx:annotaion-driven>, DataSourceTransactionManager�� ���ؼ� Aop ����
	@Transactional
	public void addSruvey(SurveyVO vo, List<SurveyContentVO> list) {
		surveyDao.addSurvey(vo);
		surveyDao.addSurveyContent(list);
	}
	
	public List<SurveyVO> surveylist() {
		return surveyDao.listSurvey();
	}
	
	public SurveyVO adminDetail(int num) {
		return surveyDao.adminDetail(num);
	}
}
