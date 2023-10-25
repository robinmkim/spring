package kr.co.ictedu.mvc.controller.json;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JsonDemoController {
	private List<Map<String, String>> useList;
	public JsonDemoController() {
		useList = new ArrayList<Map<String, String>>();
	}
	
	@GetMapping("/blog2/{bnum}/{bid}")
	//Ŀ���Һ並 ����� ���� ��ȯ�Ǵ� ������ �˾Ƽ� ���, ���࿡ ��ȯ���� Object �� ��� Json Ÿ������ �����Ѵ�
	@ResponseBody
	public String myBlog(Model model, @PathVariable Integer bnum, @PathVariable String bid) {
		bnum = bnum- 10;
		String msg = "bnum:" + bnum + ",bid"+bid;
		System.out.println(msg);
		return msg;
	}
	
	//
	//jackson-databind �߰�
	@GetMapping("/board/{bnum}/{bid}")
	@ResponseBody
	public BoardVO myBlog(@PathVariable Integer bnum, @PathVariable String bid) {
		BoardVO vo = new BoardVO();
		vo.setNum(bnum);
		vo.setTitle("������ ���� ������" + bnum);
		vo.setWriter(bid);
		vo.setReip("192.168.0.11");
		vo.setContent("���� ȣȣȣ");
		vo.setBdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return vo;
	}
	
	@GetMapping("/boardJsonList")
	@ResponseBody
	public List<BoardVO> myBlog2(){
		List<BoardVO> list = new ArrayList();
		for(int i = 0; i <10; i++) {
			BoardVO vo = new BoardVO();
			vo.setNum(i+1);
			vo.setTitle("������ ���� ������" + i);
			vo.setWriter("�ƹ���");
			vo.setReip("192.168.0.11");
			vo.setContent("���� ȣȣȣ");
			vo.setBdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			list.add(vo);
		}
		return list;
	}
	
	//ajax�� ����� json ������ �ڵ鸵 form ****
	@GetMapping("/ajaxBoard")
	public String ajaxDemoBoard() {
		return "/board/ajaxBoard";
	}
	
	//RequestParamDemo : Ajax�� ���ؼ� json ������ �Ķ���͸� ó���ϱ� ���� ��� �Ұ�
	//@ResponseBody => ���� ���� �� json ������ ������
	//@RequestBody => ��û�� �Ķ���Ͱ� json Object �϶�
	@PostMapping("/RequestParamDemo")
	@ResponseBody
	public List<Map<String, String>> requestBodyDemo(@RequestBody Map<String, String> param){
		System.out.println("Test: " + param);
		useList.add(param);
		System.out.println(useList.size() + "�� ����");
		return useList;
	}
}
