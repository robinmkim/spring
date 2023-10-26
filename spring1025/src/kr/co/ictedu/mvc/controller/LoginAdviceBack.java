package kr.co.ictedu.mvc.controller;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import kr.co.ictedu.mvc.dto.MemberVO;

//@Component
//@Aspect
public class LoginAdviceBack {
	@Around("execution(* kr.co.ictedu.mvc.controller.member.LoginCheckControllerBack.logf*(..))")
	public ModelAndView loginLoggerBack(ProceedingJoinPoint jp) {
		System.out.println("=============전처리=============");
		//전처리
		//1. 로그인, 로그아웃의 메서드가 호출되고 난 반환값을 받기 위해서 선언
		ModelAndView rpath = null;
		
		//2.메서드의 이름을 가져와서 로그인과 로그아웃을 구별한다.
		String methodName = jp.getSignature().getName();
		System.out.println("method name: "+methodName);
		
		//3. target method의  파라미터 값 받아오기
		Object [] fd = jp.getArgs();
//		for(Object e: fd) {
//			System.out.println(e);
//		}
		HttpSession session = (HttpSession) fd[0];
		
		//4.
		if(methodName == "logfinProcess") {
			MemberVO vo = (MemberVO) fd[2];
			System.out.println("AOP id: " + vo.getId());
			System.out.println("AOP pwd: " + vo.getPwd() );
		}
		System.out.println("=============비지니스 로직=============");
		
		//비지니스 모델의 메서드
		try {
			rpath = (ModelAndView) jp.proceed();//login*()
		} catch (Throwable e) {
			e.printStackTrace();
		}

		//rpath.setViewName("redirect:/upboard/upList");
		//후처리
		System.out.println("=============후처리=============");
		if(session != null) {
			System.out.println(session.getAttribute("sessionName"));
			System.out.println(session.getAttribute("sessionID"));
		}
		return rpath;
	}
}
