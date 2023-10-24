package kr.co.ictedu.mvc.controller.main;
// Advice가 적용 될 데모 객체
// 공통관심사항 -> 오늘 날짜를 메서드 실행전에 로그로 출력

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TodayMyAopDemoController {
	
	@GetMapping(value = "/myprofile")
	public ModelAndView todayProfile() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("profile","나는 스프리의 김길동이다.");
		mav.setViewName("today/todayProfile");
		return mav;
	}
	
	@GetMapping(value = "/todaywk")
	public ModelAndView todayWork() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("work","엄청 쉬운 작업입니다. 날짜를 적용하세요.");
		mav.setViewName("today/todayWork");
		return mav;
	}
}
