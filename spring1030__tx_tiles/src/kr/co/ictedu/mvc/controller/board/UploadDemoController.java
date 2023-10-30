package kr.co.ictedu.mvc.controller.board;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.ictedu.mvc.dao.BoardDaoInter;
import kr.co.ictedu.mvc.dto.BoardCommDTO;
import kr.co.ictedu.mvc.dto.BoardVO;
import kr.co.ictedu.mvc.dto.PageVO;

@Controller
public class UploadDemoController {
	
	@Autowired
	private BoardDaoInter boardDaoInter;
	
	@GetMapping("/upform")
	public String upform() {
		return "updemo/upForm";
	}
	
	@Autowired
	private PageVO pageVO;
	
	//uploadpro
	@PostMapping("/uploadpro")
	public String uploadFile(Model m, BoardVO vo, HttpServletRequest request) {
		//parameter 테스트
		System.out.println("Title: " + vo.getTitle());
		MultipartFile mf = vo.getMfile();
		String oriFn = mf.getOriginalFilename();
		System.out.println(oriFn);
		
		//경로 테스트: 이미지가 저장될 경로
		String img_path = "resources\\imgfile";
		
		//이클립스 상에 저장할 이미지 경로
		String r_path = request.getSession().getServletContext().getRealPath("/");
		System.out.println("r_path: " + r_path);
		
		long size = mf.getSize();
		String contentType = mf.getContentType();
//		* contentType의 종류
//		- application/vnd.ms-excel
//		- application/pdf
//		- text/plain
//		- application/haansofthwp
//		- image/jpeg
		System.out.println("file size: " + size);
		System.out.println("file type: " + contentType);
		
		//메모리상(임시저장소)에 파일을 우리가 설정한 겨 ㅇ로에 복사 하겠다.
		//이미지가 저장될 경로 만들기
		StringBuffer path = new StringBuffer();
		path.append(r_path).append(img_path).append("\\");
		path.append(oriFn);
		System.out.println("FullPath: " + path);
		
		//추상경로(이미지를 저장할 경로) File 객체로 생성'
		//File 클래스 메서드 복습 *****
		File f = new File(path.toString());
		
		//임시 메모리에 담긴 즉 업로드한 파일의 값 -> File클래스의 경로로 복사
		try {
			mf.transferTo(f);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		//Dao 에 입력 처리
		vo.setImgn(oriFn);
		boardDaoInter.upboardAdd(vo);
		return "redirect:upList";
	}
	
	@RequestMapping("/upList")
	public String upBoardList(Model m, @RequestParam Map<String, String> paramMap) {
		System.out.println("print parameter");
		System.out.println("print parametere when search");
		String cPage = paramMap.get("cPage");
	
		
		
		System.out.println("cPage: " + cPage);
		System.out.println("searchType: " + paramMap.get("searchType"));
		System.out.println("searchValue: " + paramMap.get("searchValue"));
		System.out.println("****************************************");
		
		//1. totalRecord
		pageVO.setTotalRecord(boardDaoInter.getTotal(paramMap));
		int totalRecord = pageVO.getTotalRecord();
		System.out.println("1. total record: " + totalRecord);
		
		pageVO.setTotalPage((int) Math.ceil((totalRecord)/(double) pageVO.getNumPerPage()));
		System.out.println("2. total page: " + pageVO.getTotalPage());
		
		pageVO.setTotalBlock((int) Math.ceil((double) pageVO.getTotalPage() / pageVO.getPagePerBlock()));
		System.out.println("3. total Block : " + pageVO.getTotalBlock());
		
		if(cPage != null) {
			pageVO.setNowPage(Integer.parseInt(cPage));
		} else {
			pageVO.setNowPage(1);
		}
		
		System.out.println("4. nowPage: " + pageVO.getNowPage());
		
		pageVO.setBeginPerPage((pageVO.getNowPage() - 1) * pageVO.getNumPerPage() + 1);
		pageVO.setEndPerPage((pageVO.getBeginPerPage() - 1) + pageVO.getNumPerPage());
		System.out.println("5. begin page: " + pageVO.getBeginPerPage());
		System.out.println("5. end page: " + pageVO.getEndPerPage());
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("begin", String.valueOf(pageVO.getBeginPerPage()));
		map.put("end", String.valueOf(pageVO.getEndPerPage()));
		
		map.putAll(paramMap);
		System.out.println("========MapAll========");
		for(Map.Entry<String, String> e: map.entrySet()) {
			System.out.println(e.getKey() + "," + e.getValue());
		}
		List<BoardVO> list = boardDaoInter.upboardList(map);
		m.addAttribute("boardList", list);
		System.out.println(list.size());
		
		int startPage = (int) ((pageVO.getNowPage() - 1) / pageVO.getPagePerBlock()) * pageVO.getPagePerBlock() + 1;
		int endPage = startPage + pageVO.getPagePerBlock()- 1;
		if(endPage > pageVO.getTotalPage()) {
			endPage = pageVO.getTotalPage();
		}
		System.out.println("6. startPage: " + startPage);
		System.out.println("6. endPage: " + endPage);
		m.addAttribute("searchValue", paramMap.get("searchValue"));
		m.addAttribute("searchType", paramMap.get("searchValue"));
		m.addAttribute("startPage", startPage);
		m.addAttribute("endPage", endPage);
		m.addAttribute("page", pageVO);
		
		
		return "updemo/upList";
	}
	
	
	@PostMapping("/bcominsert")
	public String bcominsert(BoardCommDTO cvo, Model model) {
		boardDaoInter.commAdd(cvo);
		return "redirect:boardDetail?num=" + cvo.getUcode() + "&type=comm#comm";
	}
	
	@GetMapping("/upboardModify")
	public String upboardModify(Model m, 
			@RequestParam(required=true) int num, 
			@RequestParam(defaultValue = "detail") String type) {
		if(!type.equals("comm")) {
			boardDaoInter.updateHit(num);
		}
		BoardVO vo = boardDaoInter.getItem(num);
		m.addAttribute("vo", vo);
		return "/updemo/boardModify";
	}
	@PostMapping("/upboardUpdate")
	public String upboardUpdate(Model m, BoardVO vo, HttpServletRequest request) {
		MultipartFile mf = vo.getMfile();
		String oriFn = mf.getOriginalFilename();
				
		String img_path = "resources\\imgfile";
				
		String r_path = request.getSession().getServletContext().getRealPath("/");
				
		long size = mf.getSize();
		String contentType = mf.getContentType();

		StringBuffer path = new StringBuffer();
		path.append(r_path).append(img_path).append("\\");
		path.append(oriFn);
				
		File f = new File(path.toString());
		
		try {
			mf.transferTo(f);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		vo.setImgn(oriFn);
		boardDaoInter.updateItem(vo);
		System.out.println("FullPath: " + path);
		return "redirect:boardDetail?num=" + vo.getNum();
	}
	
	@PostMapping("/boardDelete")
	public String upboardDelete(int num) {
		boardDaoInter.deletUpBoard(num);
		return "redirect:upList";
	}
	
	@GetMapping("/boardDetail")
	public String upboardDetail(Model m, 
			@RequestParam Map<String, String> paramMap,
			@RequestParam(required = true) int num, 
			@RequestParam(defaultValue = "detail") String type) {
		if (!type.equals("comm")) {
	        boardDaoInter.updateHit(num);;
	    } 
		
		BoardVO vo = boardDaoInter.getItem(num);
		m.addAttribute("vo", vo);

		// 페이징 시작
		String cPage = paramMap.get("cPage");
		System.out.println(boardDaoInter.getTotalComm(paramMap));
		// 1. totalRecord
		pageVO.setTotalRecord(boardDaoInter.getTotalComm(paramMap));
		int totalRecord = pageVO.getTotalRecord();

		// 2. totalPage
		// totalRecord/numPerPage
		// = (int)Math.ceil(totalRecord/(double)numPerPage);
		// int totalPage
		pageVO.setTotalPage((int) Math.ceil(totalRecord / (double) pageVO.getNumPerPage()));
		System.out.println("2. totalPage :" + pageVO.getTotalPage());
		/*
		 * 3. totalBlock // 전체 블록 구하기 => 전체페이지()/보여줄블록수() totalBlock = 6/5; totalBlock =
		 * (int)Math.ceil((double) totalPage / pagePerBlock)
		 * System.out.println("3. totalBlock :" + totalBlock);
		 */
		pageVO.setTotalBlock((int) Math.ceil((double) pageVO.getTotalPage() / pageVO.getPagePerBlock()));
		System.out.println("3. totalBlock :" + pageVO.getTotalBlock());
		// 현재 페이지를 요청할 때 파라미터로 현재 페이지값을 받는다. 1 ~~~~~~~~~ n

		if (cPage != null) {
			pageVO.setNowPage(Integer.parseInt(cPage));
		} else {
			pageVO.setNowPage(1);
		}
		System.out.println("4. nowPage:" + pageVO.getNowPage());

		pageVO.setBeginPerPage((pageVO.getNowPage() - 1) * pageVO.getNumPerPage() + 1);
		pageVO.setEndPerPage((pageVO.getBeginPerPage() - 1) + pageVO.getNumPerPage());
		System.out.println("5. beginPerPage = " + pageVO.getBeginPerPage());
		System.out.println("5-1. endPerPage = " + pageVO.getEndPerPage());

		// 페이징 테스트
		Map<String, String> map = new HashMap<String, String>();
		map.put("begin", String.valueOf(pageVO.getBeginPerPage()));
		map.put("end", String.valueOf(pageVO.getEndPerPage()));
		

		// map에 paramMap합치기
		map.putAll(paramMap);
		// 검수
		System.out.println("===========Map All=============");
		for (Map.Entry<String, String> e : map.entrySet()) {
			System.out.println(e.getKey() + "," + e.getValue());
		}
		List<BoardCommDTO> list = boardDaoInter.listCommBoard(map);
		System.out.println("Size:" + list.size());

		/*
		 * int startPage = (int)((nowPage -1)/pagePerBlock) * pagePerBlock + 1
		 * 
		 * int endPage = startPage + pagePerBlock -1; // endPage의 연산의 범위가 우리가 구한
		 * totalPage값미만이라면// totalPage의 값으로 대입시킨다.
		 * 
		 * if (endPage > pageVO.getTotalPage()) { endPage = pageVO.getTotalPage();
		 * 
		 * } System.out.println("6.startPage = " + startPage);
		 * System.out.println("6-1. endPage =" + endPage);
		 */
		int startPage = (int) ((pageVO.getNowPage() - 1) / pageVO.getPagePerBlock()) * pageVO.getPagePerBlock() + 1;
		int endPage = startPage + pageVO.getPagePerBlock() - 1;
		if (endPage > pageVO.getTotalPage()) {
			endPage = pageVO.getTotalPage();

		}
		System.out.println("6.startPage = " + startPage);
		System.out.println("6-1. endPage =" + endPage);
		// 페이징 끝

		// 댓글 값을 뷰로 전달
		m.addAttribute("startPage", startPage); // 블록에 시작페이지 값
		m.addAttribute("endPage", endPage); // 블록에 종료 페이지값
		m.addAttribute("page", pageVO); // nowPage, pagePerBlock, totalPage
		m.addAttribute("listcomm", list);
		m.addAttribute("num", num);

		return "updemo/boardInfo";
	}
	
}
