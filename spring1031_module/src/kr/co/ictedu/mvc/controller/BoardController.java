package kr.co.ictedu.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.ictedu.mvc.dto.BoardImageVO;
import kr.co.ictedu.mvc.dto.BoardVO;
import kr.co.ictedu.mvc.dto.BoardVideoVO;
import kr.co.ictedu.mvc.dto.PageVO;
import kr.co.ictedu.mvc.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService service;
	
	@Autowired
	private PageVO pageVO;

	@RequestMapping(value = "/boardForm")
	public String boardForm() {
		return "board/boardform";
	}

	// uploadpro
	@PostMapping("/uploadpro")
	public String uploadFile(Model m, BoardVO vo, HttpServletRequest request) {
		String r_path = request.getSession().getServletContext().getRealPath("/");
		
		BoardVideoVO bvvo = videoUpload(vo, r_path);
		List<BoardImageVO> list = uploadImage(vo, r_path);
		service.addBoard(vo, list, bvvo);
		return "redirect:upList";
	}
	
	public BoardVideoVO videoUpload(BoardVO vo, String r_path) {
		BoardVideoVO bvvo = new BoardVideoVO();
		MultipartFile vfile = vo.getVfile();
		String oriVFn = vfile.getOriginalFilename();
		
		String video_path = "resources\\videofile";
		
		StringBuffer path = new StringBuffer();
		path.append(r_path).append(video_path).append("\\");
		path.append(oriVFn);
		System.out.println("FullPath: " + path);
		
		File f = new File(path.toString());

		try {
			vfile.transferTo(f);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		bvvo.setVname(oriVFn);
		bvvo.setReip(vo.getReip());
		
		return bvvo;
	}
	
	public List<BoardImageVO> uploadImage(BoardVO vo, String r_path){
		System.out.println("이미지 안에 왔다");
		List<BoardImageVO> list = new ArrayList();
		
		List<MultipartFile> mfList = vo.getMflist();
		String img_path = "resources\\imgfile";
		
		StringBuffer path = new StringBuffer();
		path.append(r_path).append(img_path).append("\\");
		
		for(MultipartFile e : mfList) {
			BoardImageVO bivo = new BoardImageVO();
			String oriIFn = e.getOriginalFilename();

			path.append(oriIFn);
			System.out.println("FullPath: " + path);
			File f = new File(path.toString());

			try {
				e.transferTo(f);
			} catch (IllegalStateException | IOException error) {
				error.printStackTrace();
			}
			
			if(list.isEmpty()) {
				bivo.setIsThumb(1);
			} else {
				bivo.setIsThumb(0);
			}
			
			bivo.setIname(oriIFn);
			bivo.setReip(vo.getReip());
			
			list.add(bivo);
		}
		
		return list;
	}
	
	@RequestMapping("/boardlist")
	public String upBoardList(Model m, @RequestParam Map<String, String> paramMap) {
		System.out.println("print parameter");
		System.out.println("print parametere when search");
		String cPage = paramMap.get("cPage");
	
		
		
		System.out.println("cPage: " + cPage);
		System.out.println("searchType: " + paramMap.get("searchType"));
		System.out.println("searchValue: " + paramMap.get("searchValue"));
		System.out.println("****************************************");

		
		//1. totalRecord
		pageVO.setTotalRecord(service.getTotal(paramMap));
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
		List<BoardVO> list = service.boardList(map);
		for(BoardVO e: list) {
			System.out.println(e.toString());
		}
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
		
		
		return "board/boardlist";
	}

}
