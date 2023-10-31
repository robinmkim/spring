package kr.co.ictedu.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.co.ictedu.mvc.dto.BoardImageVO;
import kr.co.ictedu.mvc.dto.BoardVO;
import kr.co.ictedu.mvc.dto.BoardVideoVO;
import kr.co.ictedu.mvc.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService service;

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

}
