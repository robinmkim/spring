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

//ModelAndView : 모델이 실행하고 난 후 컨트롤서블릿에게 View 이름 값(forward)
//View 이름, 동적으로 모델의 값이 달라질 수도 있을 때 사용 권장

@Controller
public class MemberController {
	
//	@GetMapping("/memForm")
//	public ModelAndView memForm(){
//		ModelAndView mav = new ModelAndView("member/memberForm");
//		return mav;
//	}
	
	//단순하게 뷰만 반환
	@GetMapping("/memForm")
	public String memForm() {
		return "member/memberFrom";
	}
	
	//id중복 체크
	//Model m : View로 값을 전달 할 때 : mav.addObject 
	//여러분이 idcheck?id=xman
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
	//memberForm 입력된 파라미터를 모두 sysout으로 각각 콘솔에 출력하고
	//member/source.jsp에서는 이 데이터를 각각 EL로 출력하시오.
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
	//@RequestParam 어노테이션은 파라미터를 검증하는 용도인데
	//Map<String, String> vo => @RequestParam을 지정한다
	//Map일 경우에는 checkbox와 같이 배열타입을 따로 인자로 선언해서 가져오는 방법이 좋다.
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
		String wcMsg = "황연합니다." + id + "님, 등급: " + type;
		m.addAttribute("wcMsg", wcMsg);
		return "member/success3";
	}
	
	@GetMapping(value="/joingSuccess2")
	public String joinSuccess2(Model model, @RequestParam(defaultValue = "guest") String type, @RequestParam(required= true) String id) {
		String wcMsg = "황연합니다." + id + "님, 등급: " + type;
		model.addAttribute("wcMsg", wcMsg);
		return "member/success3";
	}

	
	//<중요>: 블로그나, Vue, Reactㅁ 단의 파라미터 값으로 전송 받을때도 자주 사용
	//Param
	//@PathVariable -> path중에 {path} 값으로 받아서 파라미터로 인식 하겠다는 설정
	//http://localhost/spring1019_mvc/path1/xman
	@GetMapping("/path1/{m}")
	public String path1(Model model, @PathVariable String m) {
		System.out.println(m);
		model.addAttribute("wcMsg", m);
		return "member/success3";
	}
	
	//@PathVariable -> path중에 {path} 값으로 받아서 파라미터로 인식 하겠다는 설정
		//http://localhost/spring1019_mvc/path2/xman/seoul
	@GetMapping("/path2/{m}/{addr}")
	public String path2(Model model, @PathVariable String m, @PathVariable("addr") String n) {
		System.out.println(m);
		String msg = m+"/" +n;
		model.addAttribute("wcMsg", msg);
		return "member/success3";
	}
	
	
	//김민섭 : B팀
	/*Ajax 최종 테스트
	 *아이디값이 존재하면 1, 아니면 0
	 *<요구사항> : url:idcheck
	 *param : id는 반드시 존재해야 한다.
	 *"xman", "bigdaddy", "postman" 배열로 만들어서
	 *전송된 파라미터 값이 아이디 배열에 존재하면 커스텀뷰에 1 반환하고 아니면 0을  반환하는 메서드를 저의한 후 
	 *실제 폼에서 테스트 하세요.
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


