package kr.co.ictedu.mvc.controller.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.ictedu.mvc.dto.MemberDTO;

//ModelAndView : ���� �����ϰ� �� �� ��Ʈ�Ѽ������� View �̸� ��(forward)
//View �̸�, �������� ���� ���� �޶��� ���� ���� �� ��� ����

@Controller
public class MemberController {
	
//	@GetMapping("/memForm")
//	public ModelAndView memForm(){
//		ModelAndView mav = new ModelAndView("member/memberForm");
//		return mav;
//	}
	
	//�ܼ��ϰ� �丸 ��ȯ
	@GetMapping("/memForm")
	public String memForm() {
		return "member/memberFrom";
	}
	
	//id�ߺ� üũ
	//Model m : View�� ���� ���� �� �� : mav.addObject 
	//�������� idcheck?id=xman
//	@RequestMapping(value = "/idcheck")
//	@GetMapping(value = "/idcheck")
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
	
//	@PostMapping(value = "/memberIn")
//	public String memberIn(Model m, @ModelAttribute(value="vo") MemberDTO vo) {
//		System.out.println("id :" + vo.getId());
//		System.out.println("pwd : " + vo.getPwd());
//		System.out.println("uname : " + vo.getUname());
//		for(int i = 0; i < vo.getChk().length; i ++) {
//			System.out.println(vo.getChk()[i]);
//		}
//		vo.setMdate("2023-10-19");
//		m.addAttribute("vo", vo);
//		return "member/success";
//	}
	
	//*****
	//@RequestParam ������̼��� �Ķ���͸� �����ϴ� �뵵�ε�
	//Map<String, String> vo => @RequestParam�� �����Ѵ�
	//Map�� ��쿡�� checkbox�� ���� �迭Ÿ���� ���� ���ڷ� �����ؼ� �������� ����� ����.
	@PostMapping(value = "/memberIn")
	public String memberIn(Model m, @RequestParam Map<String, Object> vo, String[] chk) {
		System.out.println("---------------------");
		System.out.println("id:" + vo.get("id"));
		System.out.println("pwd:" + vo.get("pwd"));
		System.out.println("uname:" + vo.get("uname"));
		
		for(Map.Entry<String, Object> e: vo.entrySet()) {
			System.out.println(e.getKey() + ":" + e.getValue() + ":" + e.getClass());
		}
		
		for(String e: chk) {
			System.out.println(e);
		}
		
		vo.put("mdate", "2023-10-19");
		m.addAttribute("vo", vo);
		return "member/success";
	}
	
	//htttp://localhost/spring1019_mvc/joinSuccess?type=xman&index=1
	@GetMapping(value = "/joinSuccess")
	public String joinSuccess(Model m, String type, String id) {
		String wcMsg = "Ȳ���մϴ�." + id + "��, ���: " + type;
		m.addAttribute("wcMsg", wcMsg);
		return "member/success3";
	}
	
	@GetMapping(value="/joingSuccess2")
	public String joinSuccess2(Model model, @RequestParam(defaultValue = "guest") String type, @RequestParam(required= true) String id) {
		String wcMsg = "Ȳ���մϴ�." + id + "��, ���: " + type;
		model.addAttribute("wcMsg", wcMsg);
		return "member/success3";
	}

	
	//<�߿�>: ��α׳�, Vue, React�� ���� �Ķ���� ������ ���� �������� ���� ���
	//Param
	//@PathVariable -> path�߿� {path} ������ �޾Ƽ� �Ķ���ͷ� �ν� �ϰڴٴ� ����
	//http://localhost/spring1019_mvc/path1/xman
	@GetMapping("/path1/{m}")
	public String path1(Model model, @PathVariable String m) {
		System.out.println(m);
		model.addAttribute("wcMsg", m);
		return "member/success3";
	}
	
	//@PathVariable -> path�߿� {path} ������ �޾Ƽ� �Ķ���ͷ� �ν� �ϰڴٴ� ����
		//http://localhost/spring1019_mvc/path2/xman/seoul
	@GetMapping("/path2/{m}/{addr}")
	public String path2(Model model, @PathVariable String m, @PathVariable("addr") String n) {
		System.out.println(m);
		String msg = m+"/" +n;
		model.addAttribute("wcMsg", msg);
		return "member/success3";
	}
	
	
	//��μ� : B��
	/*Ajax ���� �׽�Ʈ
	 *���̵��� �����ϸ� 1, �ƴϸ� 0
	 *<�䱸����> : url:idcheck
	 *param : id�� �ݵ�� �����ؾ� �Ѵ�.
	 *"xman", "bigdaddy", "postman" �迭�� ����
	 *���۵� �Ķ���� ���� ���̵� �迭�� �����ϸ� Ŀ���Һ信 1 ��ȯ�ϰ� �ƴϸ� 0��  ��ȯ�ϴ� �޼��带 ������ �� 
	 *���� ������ �׽�Ʈ �ϼ���.
	 **/
	@PostMapping("/idcheck/{uid}")
	@ResponseBody
	public String idCheckC(Model model, @PathVariable String uid) {
		System.out.println(uid);
		String[] idList = {"xman", "bigdaddy", "postman"};
		for(String id : idList) {
			System.out.println(id);
			if(uid.equals(id)) {
				return Integer.toString(1);
			}
		}
		return Integer.toString(0);
	}
}


