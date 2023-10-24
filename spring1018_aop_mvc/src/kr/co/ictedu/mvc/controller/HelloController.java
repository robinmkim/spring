package kr.co.ictedu.mvc.controller;
//HelloController 는 예전에 모델에 해당된다.
//DispactcherServlet이란 컨트롤 서블릿이 스프링 컨테이너의 으이해서 Model을 주입 받아서 샐행
//HandlerMapping이 요청 사항을 분석해준다.*****
//이와 같이  @Controller 를 선언해줘야 DispatcherServlet에 존재 하는 스프링 컨테이너가 
//참조하는 ict01-servlet.xml 에서 <coontext:Component-scan> 방식으로  bean으로 등록이 가능

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ModelAndView myHello() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("hello");
		mav.addObject("msg", "<h1>안녕하세요. 나의 첫번째 spring MVC 입니다</h1>");
		mav.addObject("today", "<h2>" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "</h2>");
		return mav;
	}
}
