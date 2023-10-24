package kr.co.ictedu.mvc.controller.teampro;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import dto.MemberDTO;

@Controller
public class TeamProfileController {
	@GetMapping(value = "/teampro")
	public String memberIn(Model m) {
		String m1 = "±ËπŒº∑";
		String m2 = "Ω≈√§ø¨";
		String m3 = "±ËΩ≈»´";
		String m4 = "¿Ã√¢º¯";
		String m5 = "¿ÃªÛπŒ";
		String m6 = "¿Ã±‚øµ";
		m.addAttribute("m1", m1);
		m.addAttribute("m2", m2);
		m.addAttribute("m3", m3);
		m.addAttribute("m4", m4);
		m.addAttribute("m5", m5);
		m.addAttribute("m6", m6);
		
		return "team/teampro";
	}
}
