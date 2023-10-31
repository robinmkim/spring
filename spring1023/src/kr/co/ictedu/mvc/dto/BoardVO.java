package kr.co.ictedu.mvc.dto;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {
	private int num;
	private String title;
	private String writer;
	private String content;
	private String bdate;
	private String reip;
	private String imgn;
	private int cnt;
	private MultipartFile mfile;
	private int hit;
	private String iname;
	
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public String getImgn() {
		return imgn;
	}
	public void setImgn(String imgn) {
		this.imgn = imgn;
	}
	public MultipartFile getMfile() {
		return mfile;
	}
	public void setMfile(MultipartFile mfile) {
		this.mfile = mfile;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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
	public String getReip() {
		return reip;
	}
	public void setReip(String reip) {
		this.reip = reip;
	}
	public String getIname() {
		return iname;
	}
	public void setIname(String iname) {
		this.iname = iname;
	}
	@Override
	public String toString() {
		return "BoardVO [num=" + num + ", title=" + title + ", writer=" + writer + ", content=" + content + ", bdate="
				+ bdate + ", reip=" + reip + ", imgn=" + imgn + ", cnt=" + cnt + ", mfile=" + mfile + ", hit=" + hit
				+ ", iname=" + iname + "]";
	}
	
	
}
