package kr.co.ictedu.mvc.dto;

public class BoardCommVO {
	private int num;
	private int cnum;
	private String cwriter;
	private String ccontent;
	private String reip;
	private String cdate;

	public BoardCommVO(int num, int cnum, String cwriter, String ccontent, String reip, String cdate) {
		this.num = num;
		this.cnum = cnum;
		this.cwriter = cwriter;
		this.ccontent = ccontent;
		this.reip = reip;
		this.cdate = cdate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getCnum() {
		return cnum;
	}

	public void setCnum(int cnum) {
		this.cnum = cnum;
	}

	public String getCwriter() {
		return cwriter;
	}

	public void setCwriter(String cwriter) {
		this.cwriter = cwriter;
	}

	public String getCcontent() {
		return ccontent;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}

	public String getReip() {
		return reip;
	}

	public void setReip(String reip) {
		this.reip = reip;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	@Override
	public String toString() {
		return "BoardCommVO [num=" + num + ", cnum=" + cnum + ", cwriter=" + cwriter + ", ccontent=" + ccontent
				+ ", reip=" + reip + ", cdate=" + cdate + "]";
	}

}