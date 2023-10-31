package kr.co.ictedu.mvc.dto;

import org.springframework.stereotype.Component;

@Component
public class PageVO {
	// ����¡ ó���� ���� �Ӽ�
	private int nowPage; // ���� ������ �� -> �޴��������� �����Ǵ� ����
	private int nowBlock; // ���� �� -> [][][][][] -> 1 block
	private int totalRecord; // �� �Խù� �� . Dao�� ���� ����
	private int numPerPage; // �� ������ �� ������ �Խù� ��
	private int pagePerBlock; // �� �� �� ������ ������ ��
	private int totalPage; // ��ü ������ �� => totalRecord/numPerPage
	private int totalBlock; // ��ü �� ��
	private int beginPerPage; // �� ������ �� ���� �Խù��� index ��
	private int endPerPage; // �� ������ �� ������ �Խù��� index ��

	public PageVO() {
		this.nowPage = 1;
		this.nowBlock = 1;
		this.numPerPage = 10;
		this.pagePerBlock = 5;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getNowBlock() {
		return nowBlock;
	}

	public void setNowBlock(int nowBlock) {
		this.nowBlock = nowBlock;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public int getPagePerBlock() {
		return pagePerBlock;
	}

	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalBlock() {
		return totalBlock;
	}

	public void setTotalBlock(int totalBlock) {
		this.totalBlock = totalBlock;
	}

	public int getBeginPerPage() {
		return beginPerPage;
	}

	public void setBeginPerPage(int beginPerPage) {
		this.beginPerPage = beginPerPage;
	}

	public int getEndPerPage() {
		return endPerPage;
	}

	public void setEndPerPage(int endPerPage) {
		this.endPerPage = endPerPage;
	}
	
	

}
