package kr.co.ictedu.mvc.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.ictedu.mvc.dao.MemberDaoInter;
import kr.co.ictedu.mvc.dto.MemberVO;

@Controller
@RequestMapping("/login")
public class LoginCheckController {
	
	@Autowired
	private MemberDaoInter memberDaoInter;
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "member/loginform";
	}
	@PostMapping("/loginProcess")
	public ModelAndView loginProcess(HttpSession session,
			HttpServletRequest request, MemberVO vo, 
			@RequestHeader("User-Agent") String userAgent) {
		ModelAndView mav = new ModelAndView("redirect:/main");
		System.out.println("id: " + vo.getId());
		System.out.println("pwd: " + vo.getPwd());
		MemberVO dto = memberDaoInter.loginCheck(vo);
		if(dto == null) {
			mav.setViewName("error/paramException");
			mav.addObject("emsg","로그인 실패입니다.");
		} else {
			session.setAttribute("sessionName", dto.getName());
			session.setAttribute("sessionID", dto.getId());
		}
		return mav;
	}
	
	@GetMapping("/logout")
	public ModelAndView logoutProcess(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		session.removeAttribute("sessionName");
		session.removeAttribute("sessionID");
		mav.setViewName("redirect:/main");
		System.out.println("로그아웃 실행! 및 세션 삭제");
		return mav;
	}
}
