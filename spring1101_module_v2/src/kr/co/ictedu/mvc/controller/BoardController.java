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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.ictedu.mvc.dao.BoardDaoInter;
import kr.co.ictedu.mvc.dto.BoardVO;
import kr.co.ictedu.mvc.dto.PageVO;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardDaoInter dao;

	@Autowired
	private PageVO pageVO;

	@RequestMapping(value = "/boardform")
	public String boardForm() {
		return "board/boardform";
	}

	@PostMapping(value = "/uploadpro")
	public String boardUpload(Model m, BoardVO bvo, HttpServletRequest request) {
		String r_path = request.getSession().getServletContext().getRealPath("/");

		// 비디오 파일
		MultipartFile mfile = bvo.getMfile();
		// 비디오 파일 이름
		String oriVFn = mfile.getOriginalFilename();
		// BoardVO에 vidn 저장
		bvo.setVidn(oriVFn);

		String video_path = "resources\\videofile";

		StringBuffer path = new StringBuffer();
		path.append(r_path).append(video_path).append("\\");
		path.append(oriVFn);
		System.out.println("FullPath: " + path);

		File f = new File(path.toString());

		try {
			mfile.transferTo(f);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		bvo.setImgn(uploadImage(bvo, r_path));

		dao.boardAdd(bvo);
		return "redirect:boardlist";
	}

	public String uploadImage(BoardVO vo, String r_path) {
		List<MultipartFile> mfList = vo.getMflist();
		String img_path = "resources\\imgfile";
		String imgn = "";

		for (MultipartFile e : mfList) {
			StringBuffer path = new StringBuffer();
			path.append(r_path).append(img_path).append("\\");
			String oriIFn = e.getOriginalFilename();
			System.out.println("oriFN: " + oriIFn);

			if(oriIFn != "") {
				imgn += oriIFn + ";";
			}

			path.append(oriIFn);
			System.out.println("FullPath: " + path);
			File f = new File(path.toString());

			try {
				if (!e.isEmpty()) {
					e.transferTo(f);
				}
			} catch (IllegalStateException | IOException error) {
				error.printStackTrace();
			}

		}
		System.out.println(imgn);

		return imgn;
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

		// 1. totalRecord
		pageVO.setTotalRecord(dao.getTotal(paramMap));
		int totalRecord = pageVO.getTotalRecord();
		System.out.println("1. total record: " + totalRecord);

		pageVO.setTotalPage((int) Math.ceil((totalRecord) / (double) pageVO.getNumPerPage()));
		System.out.println("2. total page: " + pageVO.getTotalPage());

		pageVO.setTotalBlock((int) Math.ceil((double) pageVO.getTotalPage() / pageVO.getPagePerBlock()));
		System.out.println("3. total Block : " + pageVO.getTotalBlock());

		if (cPage != null) {
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
		for (Map.Entry<String, String> e : map.entrySet()) {
			System.out.println(e.getKey() + "," + e.getValue());
		}
		List<BoardVO> list = dao.boardList(map);
		System.out.println();
		for (BoardVO e : list) {
			String[] imglist = e.getImgn().split(";");
			e.setImglist(imglist);
		}
		m.addAttribute("boardList", list);

		int startPage = (int) ((pageVO.getNowPage() - 1) / pageVO.getPagePerBlock()) * pageVO.getPagePerBlock() + 1;
		int endPage = startPage + pageVO.getPagePerBlock() - 1;
		if (endPage > pageVO.getTotalPage()) {
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

	@RequestMapping(value = "/boarddetail")
	public String boardDetail(Model m, int num) {
		BoardVO vo = dao.boardDetail(num);
		String[] imglist = vo.getImgn().split(";");
		vo.setImglist(imglist);

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
		System.out.println("finalImgList: ");
		for(String e : finalImgList) {
			System.out.println(e);
		}
		System.out.println(vo.toString());

		// 비디오 파일
		MultipartFile mfile = vo.getMfile();
		// 비디오 파일 이름
		String oriVFn = mfile.getOriginalFilename();
		// BoardVO에 vidn 저장
		vo.setVidn(oriVFn);

		String video_path = "resources\\videofile";

		StringBuffer path = new StringBuffer();
		path.append(r_path).append(video_path).append("\\");
		path.append(oriVFn);
		System.out.println("FullPath: " + path);

		File f = new File(path.toString());

		try {
			if (!mfile.isEmpty()) {
				mfile.transferTo(f);
			}
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		String newImgn = uploadImage(vo, r_path);
		System.out.println(newImgn);
		for (String e : finalImgList) {
			newImgn += e + ";";
			System.out.println(newImgn);
		}
		vo.setImgn(newImgn);

		dao.boardUpdate(vo);

		return "redirect:boarddetail?num=" + vo.getNum();
	}
}
