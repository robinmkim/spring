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
		System.out.println("=============��ó��=============");
		//��ó��
		//1. �α���, �α׾ƿ��� �޼��尡 ȣ��ǰ� �� ��ȯ���� �ޱ� ���ؼ� ����
		ModelAndView rpath = null;
		
		//2.�޼����� �̸��� �����ͼ� �α��ΰ� �α׾ƿ��� �����Ѵ�.
		String methodName = jp.getSignature().getName();
		System.out.println("method name: "+methodName);
		
		//3. target method��  �Ķ���� �� �޾ƿ���
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
		System.out.println("=============�����Ͻ� ����=============");
		
		//�����Ͻ� ���� �޼���
		try {
			rpath = (ModelAndView) jp.proceed();//login*()
		} catch (Throwable e) {
			e.printStackTrace();
		}

		//rpath.setViewName("redirect:/upboard/upList");
		//��ó��
		System.out.println("=============��ó��=============");
		if(session != null) {
			System.out.println(session.getAttribute("sessionName"));
			System.out.println(session.getAttribute("sessionID"));
		}
		return rpath;
	}
}
