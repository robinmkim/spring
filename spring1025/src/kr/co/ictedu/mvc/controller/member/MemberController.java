package kr.co.ictedu.mvc.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.ictedu.mvc.dao.MemberDaoInter;
import kr.co.ictedu.mvc.dto.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberDaoInter memberDaoInter;
	
	@GetMapping("/memForm")
	public String memForm() {
		return "member/memberForm";
	}
	
	@GetMapping("/idcheck")
	@ResponseBody
	public String idCheck(@RequestParam(required = true) String id) {
		int result = memberDaoInter.idCheck(id);
		
		return String.valueOf(result);
	}
	
	@PostMapping(value = "/memberIn")
	public String memberIn(Model m, MemberVO vo, HttpServletRequest request) {
		//int result = memberDaoInter.idCheck(vo.getId());
		vo.setReip(request.getRemoteAddr());
		memberDaoInter.add(vo);
		m.addAttribute("vo", vo);
		return "member/mysuccess";
	}
	
	@GetMapping("/mypage")
	public String myPage(HttpSession session) {
		if(session.getAttribute("sessionID") != null) {
			return "member/mypage";			
		}
		return "redirect:/main";
	}
}
