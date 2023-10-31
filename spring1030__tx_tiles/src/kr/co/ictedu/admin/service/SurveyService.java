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
	//Dao를 단위 처리하기 위한 서비스
	@Autowired
	private SurveyDaoInter surveyDao;
	//AOP - 보안, 로깅, 트랙잭션
	//Transaction 처리를 하기 위해서
	//빈에ㅐ서 설정 <tx:annotaion-driven>, DataSourceTransactionManager에 의해서 Aop 적용
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
