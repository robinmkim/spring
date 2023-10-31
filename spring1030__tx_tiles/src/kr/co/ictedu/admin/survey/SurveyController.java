package kr.co.ictedu.admin.survey;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.ictedu.admin.dao.SurveyDaoInter;
import kr.co.ictedu.admin.service.SurveyService;
import kr.co.ictedu.mvc.dto.SurveyContentVO;
import kr.co.ictedu.mvc.dto.SurveyVO;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	@Autowired
	private SurveyService surveyService;
	

	
	@RequestMapping(value = "/surveyForm")
	public String surveyForm() {

		return "/survey/surveyform";
	}
	
	@PostMapping(value = "/addsurvey")
	public ModelAndView addSurvey(SurveyVO vo, HttpServletRequest request) {
		//���� �̸����� �Ѿ���� �Ķ���ʹ� �迭�� ���� �� �ִ�. 2~5
		String[] surveytitle = request.getParameterValues("surveytitle");
		
		//DB�� �����ϱ� ���� List �����
		List<SurveyContentVO> list = new ArrayList<>();
		char stype = 'A';
		for(String e : surveytitle) {
			SurveyContentVO sv = new SurveyContentVO();
			sv.setSurveytitle(e);
			sv.setSurveycnt(0);
			sv.setSubtype(String.valueOf(stype));
			System.out.println("surveytitle => " + e);
			list.add(sv);
			stype++;//���ĺ� ����
		}
		System.out.println("Sub => " + vo.getSub());
		//SurveyVO �� ���� Ÿ��Ʋ�� ������ List<SurveyContentVO> ���ڷ� ����
		vo.setSurvey(list);
		//Service�� ���� �����Ѵ�
		surveyService.addSruvey(vo, list);
		
		ModelAndView mav = new ModelAndView("redirect:surveylist");
		return mav;
	}
	
	
	@RequestMapping(value = "/surveylist")
	public ModelAndView surveylist() {
		ModelAndView mav = new ModelAndView("survey/surveylist");
		mav.addObject("list", surveyService.surveylist());
	
		return mav;
	}
	
	@RequestMapping(value = "/surveyAdminDetail")
	public ModelAndView surveydetail(int num) {
		ModelAndView mav = new ModelAndView("survey/surveydetail");
		SurveyVO vo = surveyService.adminDetail(num);
		mav.addObject("vo", vo);
		
		return mav;
	}
	
}



