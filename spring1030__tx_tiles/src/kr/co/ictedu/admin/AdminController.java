package kr.co.ictedu.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	@GetMapping(value = {"/", "/welcom"})
	public String adminDefaultPage(Model model) {
		model.addAttribute("title", "welcome");
		model.addAttribute("message", "message");
		
		return "temp/welcompage";
	}
}
