package kr.co.ictedu.mvc.controller.ex1;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dto.MemberDTO;

//ModelAndView : ���� �����ϰ� �� �� ��Ʈ�Ѽ������� View �̸� ��(forward)
//View �̸�, �������� ���� ���� �޶��� ���� ���� �� ��� ����

@Controller
public class Ex1Controller {
	
	@GetMapping("/memForm")
	public ModelAndView memForm(){
		ModelAndView mav = new ModelAndView("member/memberForm");
		return mav;
	}
	
//	//�ܼ��ϰ� �丸 ��ȯ
//	@GetMapping("/memForm")
//	public String memForm() {
//		return "member/memberFrom";
//	}
	
	//id�ߺ� üũ
	//Model m : View�� ���� ���� �� �� : mav.addObject 
	//�������� idcheck?id=xman
//	@RequestMapping(value = "/idcheck")
	@GetMapping(value = "/idcheck")
	public String idCheck(Model m, String id) {
		//String id = request.getParameter("id");
		String dbid = "xman";
		if(dbid.equals(id)) {
			m.addAttribute("res", 1);
		} else {
			m.addAttribute("res", 0);
		}
		return "member/idchk";
	}
	//memberForm �Էµ� �Ķ���͸� ��� sysout���� ���� �ֿܼ� ����ϰ�
	//member/source.jsp������ �� �����͸� ���� EL�� ����Ͻÿ�.
//	@PostMapping(value = "/memberIn")
//	public String memberIn(Model m, String id, String pwd, String uname, int[] chk, HttpServletRequest request) {
//		System.out.println("id :" + id);
//		System.out.println("pwd : " + pwd);
//		System.out.println("uname : " + uname);
//		for(int i = 0; i < chk.length; i ++) {
//			System.out.println(chk[i]);
//		}
//		System.out.println("chk : " + chk.toString());
//		m.addAttribute("id", id);
//		m.addAttribute("pwd", pwd);
//		m.addAttribute("uname", uname);
//		m.addAttribute("chk", chk);
//		return "member/success";
//	}
	
	@PostMapping(value = "/memberIn")
	public String memberIn(Model m, MemberDTO vo) {
		System.out.println("id :" + vo.getId());
		System.out.println("pwd : " + vo.getPwd());
		System.out.println("uname : " + vo.getUname());
		for(int i = 0; i < vo.getChk().length; i ++) {
			System.out.println(vo.getChk()[i]);
		}
		
		m.addAttribute("vo", vo);
		return "member/success";
	}
	
}
