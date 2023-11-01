package kr.co.ictedu.mvc.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {
	private int num;
	private String title;
	private String writer;
	private String content;
	private int hit ;
	private String reip;
	private String bdate;
	private List<MultipartFile> mflist;
	private List<String> imgName;
	private MultipartFile vfile;
	private String videoName;
	private String thumbName;
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
	public MultipartFile getVfile() {
		return vfile;
	}
	public void setVfile(MultipartFile vfile) {
		this.vfile = vfile;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public List<String> getImgName() {
		return imgName;
	}
	public void setImgName(List<String> imgName) {
		this.imgName = imgName;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getThumbName() {
		return thumbName;
	}
	public void setThumbName(String thumbName) {
		this.thumbName = thumbName;
	}
}
