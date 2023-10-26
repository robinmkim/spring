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
		System.out.println("=============��ó��=============");
		//��ó��
		//1. �α���, �α׾ƿ��� �޼��尡 ȣ��ǰ� �� ��ȯ���� �ޱ� ���ؼ� ����
		ModelAndView rpath = null;
		
		//2.�޼����� �̸��� �����ͼ� �α��ΰ� �α׾ƿ��� �����Ѵ�.
		String methodName = jp.getSignature().getName();
		System.out.println("method name: " + methodName);
		
		//3. target method��  �Ķ���� �� �޾ƿ���
		Object [] fd = jp.getArgs();
		
		System.out.println("=============�����Ͻ� ����=============");
		if(methodName.equals("logfinProcess")) {
			//�����Ͻ� ���� �޼���
			try {
				rpath = (ModelAndView) jp.proceed();//login*()
			} catch (Throwable e) {
				e.printStackTrace();
			}
			System.out.println("��ó�� �α� �۾�");
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
				System.out.println("��ó�� ���� ����!");
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
				System.out.println("��ó�� ���� ����!");
			}
			try {
				rpath = (ModelAndView) jp.proceed();//login*()
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}

		//rpath.setViewName("redirect:/upboard/upList");
		//��ó��
		System.out.println("=============��ó��=============");

		return rpath;
	}
	
	public String patternUserAgent(String userAgent) {		
		Pattern mp = Pattern.compile("(Mobile|Android|iPhone|iPod|Macintosh)");
		Matcher mc = mp.matcher(userAgent);
		boolean res = mc.find();
		System.out.println(res);
		StringBuilder sb = new StringBuilder();
		if(res) {
			System.out.println("����Ͽ��� ����");
			sb.append("Mobile").append("/");
		} else {
			System.out.println("PC���� ����");
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
