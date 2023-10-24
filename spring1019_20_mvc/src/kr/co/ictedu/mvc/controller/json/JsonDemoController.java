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
	//커스텀뷰를 만들어 놓고 반환되는 내용을 알아서 출력, 만약에 반환값이 Object 일 경우 Json 타입으로 제공한다
	@ResponseBody
	public String myBlog(Model model, @PathVariable Integer bnum, @PathVariable String bid) {
		bnum = bnum- 10;
		String msg = "bnum:" + bnum + ",bid"+bid;
		System.out.println(msg);
		return msg;
	}
	
	//
	//jackson-databind 추가
	@GetMapping("/board/{bnum}/{bid}")
	@ResponseBody
	public BoardVO myBlog(@PathVariable Integer bnum, @PathVariable String bid) {
		BoardVO vo = new BoardVO();
		vo.setNum(bnum);
		vo.setTitle("오늘은 왠지 ㅎㅎㅎ" + bnum);
		vo.setWriter(bid);
		vo.setReip("192.168.0.11");
		vo.setContent("하하 호호호");
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
			vo.setTitle("오늘은 왠지 ㅎㅎㅎ" + i);
			vo.setWriter("아무개");
			vo.setReip("192.168.0.11");
			vo.setContent("하하 호호호");
			vo.setBdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			list.add(vo);
		}
		return list;
	}
	
	//ajax를 사용한 json 데이터 핸들링 form ****
	@GetMapping("/ajaxBoard")
	public String ajaxDemoBoard() {
		return "/board/ajaxBoard";
	}
	
	//RequestParamDemo : Ajax에 의해서 json 값으로 파라미터를 처리하기 위한 방법 소개
	//@ResponseBody => 모델이 수행 후 json 데이터 보낼때
	//@RequestBody => 요청의 파라미터가 json Object 일때
	@PostMapping("/RequestParamDemo")
	@ResponseBody
	public List<Map<String, String>> requestBodyDemo(@RequestBody Map<String, String> param){
		System.out.println("Test: " + param);
		useList.add(param);
		System.out.println(useList.size() + "개 누적");
		return useList;
	}
}
