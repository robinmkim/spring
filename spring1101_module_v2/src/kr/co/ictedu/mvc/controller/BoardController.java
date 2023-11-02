package kr.co.ictedu.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.ictedu.mvc.dao.BoardDaoInter;
import kr.co.ictedu.mvc.dto.BoardCommVO;
import kr.co.ictedu.mvc.dto.BoardVO;
import kr.co.ictedu.mvc.dto.CommPageVO;
import kr.co.ictedu.mvc.dto.PageVO;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardDaoInter dao;

	@Autowired
	private PageVO pageVO;

	@Autowired
	private CommPageVO commPageVO;

	@RequestMapping(value = "/boardform")
	public String boardForm() {
		return "board/boardform";
	}

	@PostMapping(value = "/uploadpro")
	public String boardUpload(Model m, BoardVO bvo, HttpServletRequest request) {
		String r_path = request.getSession().getServletContext().getRealPath("/");
		
		bvo.setVidn(uploadVideo(bvo, r_path));
		bvo.setImgn(uploadImage(bvo, r_path));
		
		dao.boardAdd(bvo);
		return "redirect:boardlist";
	}

	@RequestMapping("/boardlist")
	public String upBoardList(Model m, @RequestParam Map<String, String> paramMap) {
		m = paging(m, paramMap);

		return "board/boardlist";
	}

	@RequestMapping("/boarddetail")
	public String boardDetail(Model m, @RequestParam(required = true) int num,
			@RequestParam(defaultValue = "detail") String type, 
			@RequestParam Map<String, String> paramMap) {
		if (!type.equals("comm")) {
			dao.hitUpdate(num);
		}
		// Board Detail
		BoardVO vo = dao.boardDetail(num);
		m.addAttribute("vo", vo);
		
		return "board/boarddetail";
	}

	@RequestMapping(value = "/boarddelete")
	public String boardDelete(int num) {

		dao.boardDelete(num);
		return "redirect:boardlist";
	}
	
	
	@RequestMapping(value = "/boardmodify")
	public String boardModify(Model m, int num) {
		BoardVO vo = dao.boardDetail(num);
		m.addAttribute("vo", vo);

		return "board/boardmodify";
	}

	// JS로 선택하지 않은 checkbox value 가져오는 법 적용
	@RequestMapping(value = "/boardupdate")
	public String boardUpdate(BoardVO vo, String[] deletelist, HttpServletRequest request) {
		String r_path = request.getSession().getServletContext().getRealPath("/");

		BoardVO oldVO = dao.boardDetail(vo.getNum());
		String[] oldImgList = oldVO.getImglist();
		String oldVidn = oldVO.getVidn();
		String vidn = "";
		List<String> imgList = new ArrayList<String>(Arrays.asList(oldImgList));

		if (deletelist != null) {
			for (int i = 0; i < deletelist.length; i++) {
				for (int j = 0; j < imgList.size(); j++) {
					if (deletelist[i].equals(imgList.get(j))) {
						imgList.remove(j);
					}
				}
			}
		}
		String[] finalImgList = imgList.toArray(String[]::new);
		
		
		String newImgn = uploadImage(vo, r_path);
		
		for (String e : finalImgList) {
			newImgn += e + ";";
		}

		if(vo.getMfile().getOriginalFilename().equals("")) {
			vidn = oldVidn;
		} else {
			vidn = uploadVideo(vo, r_path);
		}

		vo.setVidn(vidn);
		vo.setImgn(newImgn);

		dao.boardUpdate(vo);

		return "redirect:boarddetail?num=" + vo.getNum();
	}

	@PostMapping("/bcominsert")
	public String bcominsert(BoardCommVO bcvo, Model model) {
		dao.commAdd(bcvo);
		// type => 댓글 작성 후 redirect 될때 조회수를 올리지 않게 하기 위해서 지정
		return "redirect:boarddetail?num=" + bcvo.getCnum() + "&type=comm#comm";
	}

	public String uploadVideo(BoardVO vo, String r_path) {
		// 비디오 파일
		MultipartFile mfile = vo.getMfile();
		// 비디오 파일 이름
		String vid_path = "resources\\videofile";
		String vidn = upload(vid_path, r_path, mfile);

		return vidn;
	}

	public String uploadImage(BoardVO vo, String r_path) {
		List<MultipartFile> mfList = vo.getMflist();
		String img_path = "resources\\imgfile";
		String imgn = "";

		for (MultipartFile e : mfList) {
			String oriIFn = upload(img_path, r_path, e);

			if (oriIFn != "") {
				imgn += oriIFn + ";";
			}

		}
		return imgn;
	}

	public String upload(String pathType, String r_path, MultipartFile mf) {
		String oriFn = mf.getOriginalFilename();
		StringBuffer path = new StringBuffer();
		path.append(r_path).append(pathType).append("\\");
		path.append(oriFn);

		File f = new File(path.toString());

		try {
			if (!mf.isEmpty()) {
				mf.transferTo(f);
			}
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		return oriFn;
	}

	public Model paging(Model m, Map<String, String> paramMap) {

		String cPage = paramMap.get("cPage");
		System.out.println("cPage: " + cPage);

		pageVO.setTotalRecord(dao.getTotal(paramMap));
		int totalRecord = pageVO.getTotalRecord();
		System.out.println("total record: " + totalRecord);

		pageVO.setTotalPage((int) Math.ceil((totalRecord) / (double) pageVO.getNumPerPage()));
		System.out.println("total page: " + totalRecord);
		pageVO.setTotalBlock((int) Math.ceil((double) pageVO.getTotalPage() / pageVO.getPagePerBlock()));
		System.out.println("total block: " + totalRecord);
		if (cPage != null) {
			pageVO.setNowPage(Integer.parseInt(cPage));
		} else {
			pageVO.setNowPage(1);
		}

		pageVO.setBeginPerPage((pageVO.getNowPage() - 1) * pageVO.getNumPerPage() + 1);
		pageVO.setEndPerPage((pageVO.getBeginPerPage() - 1) + pageVO.getNumPerPage());
		System.out.println("beginPage: " + pageVO.getBeginPerPage());
		System.out.println("endPage: " + pageVO.getEndPerPage());

		int startPage = (int) ((pageVO.getNowPage() - 1) / pageVO.getPagePerBlock()) * pageVO.getPagePerBlock() + 1;
		int endPage = startPage + pageVO.getPagePerBlock() - 1;
		if (endPage > pageVO.getTotalPage()) {
			endPage = pageVO.getTotalPage();
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("begin", String.valueOf(pageVO.getBeginPerPage()));
		map.put("end", String.valueOf(pageVO.getEndPerPage()));
		map.putAll(paramMap);

		List<BoardVO> list = dao.boardList(map);
		for (BoardVO e : list) {
			String[] imglist = e.getImgn().split(";");
			e.setImglist(imglist);
		}

		m.addAttribute("boardList", list);
		m.addAttribute("searchValue", paramMap.get("searchValue"));
		m.addAttribute("searchType", paramMap.get("searchType"));

		m.addAttribute("startPage", startPage);
		m.addAttribute("endPage", endPage);
		m.addAttribute("page", pageVO);

		return m;
	}
}
