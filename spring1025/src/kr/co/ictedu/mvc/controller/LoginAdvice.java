package kr.co.ictedu.mvc.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import kr.co.ictedu.mvc.dao.MemberDaoInter;
import kr.co.ictedu.mvc.dto.MemberVO;
import kr.co.ictedu.mvc.dto.MyLoginLoggerVO;

@Component
@Aspect
public class LoginAdvice {
	@Autowired
	private MemberDaoInter memberDaoInter;
	
	private String userAgent, resAgent;
	
	@Around("execution(* kr.co.ictedu.mvc.controller.member.LoginCheckController.logf*(..))")
	public ModelAndView loginLogger(ProceedingJoinPoint jp) {
		System.out.println("=============전처리=============");
		//전처리
		//1. 로그인, 로그아웃의 메서드가 호출되고 난 반환값을 받기 위해서 선언
		ModelAndView rpath = null;
		
		//2.메서드의 이름을 가져와서 로그인과 로그아웃을 구별한다.
		String methodName = jp.getSignature().getName();
		System.out.println("method name: " + methodName);
		
		//3. target method의  파라미터 값 받아오기
		Object [] fd = jp.getArgs();
		
		System.out.println("=============비지니스 로직=============");
		if(methodName.equals("logfinProcess")) {
			//비지니스 모델의 메서드
			try {
				rpath = (ModelAndView) jp.proceed();//login*()
			} catch (Throwable e) {
				e.printStackTrace();
			}
			System.out.println("후처리 로깅 작업");
			HttpSession session = (HttpSession) fd[0];
			String uid = (String) session.getAttribute("sessionID");
			String reip = ((HttpServletRequest) fd[1]).getRemoteAddr();
			userAgent = (String) fd[3];
			resAgent = patternUserAgent(userAgent);
			System.out.println("uid: " + uid);
			System.out.println("ip: " + reip);
			System.out.println("userAgent: "+userAgent);
			
			if(uid != null) {
				MyLoginLoggerVO lvo = new MyLoginLoggerVO();
				lvo.setIdn(uid);
				lvo.setStatus("login");
				lvo.setReip(reip);
				lvo.setUagent(resAgent);
				memberDaoInter.addLoginLog(lvo);
			} else {
				System.out.println("후처리 실행 안함!");
			}
			
		} else if(methodName.equals("logfoutProcess")) {
			HttpSession session = (HttpSession) fd[0];
			String uid = (String) session.getAttribute("sessionID");
			String reip = ((HttpServletRequest) fd[1]).getRemoteAddr();
			
			System.out.println("uid: " + uid);
			System.out.println("ip: " + reip);
			System.out.println("userAgent: "+userAgent);
			if(uid != null) {
				MyLoginLoggerVO lvo = new MyLoginLoggerVO();
				lvo.setIdn(uid);
				lvo.setStatus("logout");
				lvo.setReip(reip);
				lvo.setUagent(resAgent);
				memberDaoInter.addLoginLog(lvo);
			} else {
				System.out.println("후처리 실행 안함!");
			}
			try {
				rpath = (ModelAndView) jp.proceed();//login*()
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}

		//rpath.setViewName("redirect:/upboard/upList");
		//후처리
		System.out.println("=============후처리=============");

		return rpath;
	}
	
	public String patternUserAgent(String userAgent) {		
		Pattern mp = Pattern.compile("(Mobile|Android|iPhone|iPod|Macintosh)");
		Matcher mc = mp.matcher(userAgent);
		boolean res = mc.find();
		System.out.println(res);
		StringBuilder sb = new StringBuilder();
		if(res) {
			System.out.println("모바일에서 접속");
			sb.append("Mobile").append("/");
		} else {
			System.out.println("PC에서 접속");
			sb.append("PC").append("/");
		}
		Pattern mp1 = Pattern.compile("(Windows NT [\\d.]+|Android [\\d.]+|iPhone)");
		Matcher mc1 = mp1.matcher(userAgent);
		if(mc1.find()) {
			String device = mc1.group();
			System.out.println(device);
			sb.append(device);
		}
		return sb.toString();
	}
}
