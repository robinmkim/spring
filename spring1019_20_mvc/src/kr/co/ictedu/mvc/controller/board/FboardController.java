package kr.co.ictedu.mvc.controller.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import kr.co.ictedu.mvc.dao.FBoardDaoInter;
import kr.co.ictedu.mvc.dto.FboardDTO;

@Controller
public class FboardController {
	@Autowired
	private FBoardDaoInter fBoardDaoInter;
	
	//form
	@GetMapping(value = "/fboard")
	public String tBoardForm() {
		return "/fboard/write";
	}
	
	//
	@PostMapping(value = "/fboardInsert")
	public String tboardInsert(Model m, FboardDTO vo) {
		
		System.out.println(vo.getContent());
		fBoardDaoInter.addFboard(vo);
		return "redirect:fboardList";
	}
	
	@GetMapping("/fboardList")
	public String tboardList(Model m) {
		List<FboardDTO> list = fBoardDaoInter.listFboard();
		m.addAttribute("list", list);
		return "/fboard/list";
	}
	
	@GetMapping("fboardHit")
	public String fboardHit(int num) {
		fBoardDaoInter.updateHit(num);
		return "redirect:fboardDetail?num=" + num;
	}
	
	@GetMapping("/fboardDetail")
	public String fboardDetail(int num, Model model) {
		FboardDTO v = fBoardDaoInter.detailFboard(num);
		model.addAttribute("v", v);
		return "/fboard/info";
	}
	
	@GetMapping("/fboardModify")
	public String fboardModify(Model m, int num) {
		FboardDTO v = fBoardDaoInter.detailFboard(num);
		m.addAttribute("v", v);
		return "/fboard/modify";
	}
	
	@PostMapping("/fboardUpdate")
	public String fboardUpdate(Model m, FboardDTO vo) {
		fBoardDaoInter.updateFboard(vo);
		return "redirect:fboardDetail?num=" + vo.getNum();
	}
	
	@PostMapping("/fboardDelete")
	public String fboardDelete(int num) {
		fBoardDaoInter.deleteFboard(num);
		return "redirect:fboardList";
	}

}
