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
import kr.co.ictedu.mvc.dto.BoardVO;
import kr.co.ictedu.mvc.dto.FboardDTO;
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
		//parameter �׽�Ʈ
		System.out.println("Title: " + vo.getTitle());
		MultipartFile mf = vo.getMfile();
		String oriFn = mf.getOriginalFilename();
		System.out.println(oriFn);
		
		//��� �׽�Ʈ: �̹����� ����� ���
		String img_path = "resources\\imgfile";
		
		//��Ŭ���� �� ������ �̹��� ���
		String r_path = request.getSession().getServletContext().getRealPath("/");
		System.out.println("r_path: " + r_path);
		
		long size = mf.getSize();
		String contentType = mf.getContentType();
//		* contentType�� ����
//		- application/vnd.ms-excel
//		- application/pdf
//		- text/plain
//		- application/haansofthwp
//		- image/jpeg
		System.out.println("file size: " + size);
		System.out.println("file type: " + contentType);
		
		//�޸𸮻�(�ӽ������)�� ������ �츮�� ������ �� ���ο� ���� �ϰڴ�.
		//�̹����� ����� ��� �����
		StringBuffer path = new StringBuffer();
		path.append(r_path).append(img_path).append("\\");
		path.append(oriFn);
		System.out.println("FullPath: " + path);
		
		//�߻���(�̹����� ������ ���) File ��ü�� ����'
		//File Ŭ���� �޼��� ���� *****
		File f = new File(path.toString());
		
		//�ӽ� �޸𸮿� ��� �� ���ε��� ������ �� -> FileŬ������ ��η� ����
		try {
			mf.transferTo(f);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		//Dao �� �Է� ó��
		vo.setImgn(oriFn);
		boardDaoInter.upboardAdd(vo);
		return "redirect:upList";
	}
	
	@RequestMapping("/upList")
	public String upBoardList(Model m, @RequestParam Map<String, String> paramMap) {
		System.out.println("print parameter");
		System.out.println("print parametere when search");
		String cPage = paramMap.get("cPage");
		//1. totalRecord
		pageVO.setTotalRecord(boardDaoInter.getTotal());
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
		m.addAttribute("startPage", startPage);
		m.addAttribute("endPage", endPage);
		m.addAttribute("page", pageVO);
		
		
		return "updemo/upList";
	}
	
	@GetMapping("/boardDetail")
	public String boardDetail(Model m, int num) {
		BoardVO vo = boardDaoInter.getItem(num);
		m.addAttribute("vo", vo);
		return "/updemo/boardInfo";
	}
	
	@GetMapping("/upboardModify")
	public String upboardModify(Model m, int num) {
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
		return "redirect:boardDetail?num=" + vo.getNum();
	}
	
	   @PostMapping("/boardDelete")
	   public String upboardDelete(int num) {
	      boardDaoInter.deletUpBoard(num);
	      return "redirect:upList";
	   }
}
