package kr.co.ictedu.mvc.controller;
//HelloController �� ������ �𵨿� �ش�ȴ�.
//DispactcherServlet�̶� ��Ʈ�� ������ ������ �����̳��� �����ؼ� Model�� ���� �޾Ƽ� ����
//HandlerMapping�� ��û ������ �м����ش�.*****
//�̿� ����  @Controller �� ��������� DispatcherServlet�� ���� �ϴ� ������ �����̳ʰ� 
//�����ϴ� ict01-servlet.xml ���� <coontext:Component-scan> �������  bean���� ����� ����

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
		mav.addObject("msg", "<h1>�ȳ��ϼ���. ���� ù��° spring MVC �Դϴ�</h1>");
		mav.addObject("today", "<h2>" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "</h2>");
		return mav;
	}
}
