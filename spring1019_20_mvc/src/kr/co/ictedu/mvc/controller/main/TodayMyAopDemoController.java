package kr.co.ictedu.mvc.controller.main;
// Advice�� ���� �� ���� ��ü
// ������ɻ��� -> ���� ��¥�� �޼��� �������� �α׷� ���

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TodayMyAopDemoController {
	
	@GetMapping(value = "/myprofile")
	public ModelAndView todayProfile() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("profile","���� �������� ��浿�̴�.");
		mav.setViewName("today/todayProfile");
		return mav;
	}
	
	@GetMapping(value = "/todaywk")
	public ModelAndView todayWork() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("work","��û ���� �۾��Դϴ�. ��¥�� �����ϼ���.");
		mav.setViewName("today/todayWork");
		return mav;
	}
}
