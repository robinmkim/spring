package kr.co.ictedu.mvc.advice;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

@Component
@Aspect
public class TodayAdvice {

	@Before("execution(* kr.co.ictedu.mvc.controller.main.TodayMy*.today*(..))")
	public void todayBeforeMethod() {
		System.out.println("¿À´ÃÀÇ ³¯Â¦");
	}
	
	
	//¿¬½À¹®Á¦: ±è¹Î¼·
	@AfterReturning(pointcut = "execution(* kr.co.ictedu.mvc.controller.main.TodayMy*.today*(..))", returning = "ret")
	public void todayAfterReturnMethod(JoinPoint jp, ModelAndView ret) {
		ServletRequestAttributes sr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = sr.getRequest();
		ret.addObject("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		ret.addObject("reip", request.getRemoteAddr());
	}
}
