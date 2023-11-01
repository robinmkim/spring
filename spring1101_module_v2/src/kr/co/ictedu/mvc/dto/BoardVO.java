package kr.co.ictedu.mvc.dto;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {
	//DB data
	private int num;
	private String title;
	private String writer;
	private String content;
	private int hit ;
	private String reip;
	private String bdate;
	private String imgn;
	private String vidn;
	
	//View data
	private List<MultipartFile> mflist;
	private MultipartFile mfile;
	private String[] imglist;
	private int cnt;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getReip() {
		return reip;
	}
	public void setReip(String reip) {
		this.reip = reip;
	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public List<MultipartFile> getMflist() {
		return mflist;
	}
	public void setMflist(List<MultipartFile> mflist) {
		this.mflist = mflist;
	}
	public MultipartFile getMfile() {
		return mfile;
	}
	public void setMfile(MultipartFile mfile) {
		this.mfile = mfile;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getImgn() {
		return imgn;
	}
	public void setImgn(String imgn) {
		this.imgn = imgn;
	}
	public String[] getImglist() {
		return imglist;
	}
	public void setImglist(String[] imglist) {
		this.imglist = imglist;
	}
	public String getVidn() {
		return vidn;
	}
	public void setVidn(String vidn) {
		this.vidn = vidn;
	}
	@Override
	public String toString() {
		return "BoardVO [num=" + num + ", title=" + title + ", writer=" + writer + ", content=" + content + ", hit="
				+ hit + ", reip=" + reip + ", bdate=" + bdate + ", imgn=" + imgn + ", vidn=" + vidn + ", mflist="
				+ mflist + ", mfile=" + mfile + ", imglist=" + Arrays.toString(imglist) + ", cnt=" + cnt + "]";
	}
	
	
}
