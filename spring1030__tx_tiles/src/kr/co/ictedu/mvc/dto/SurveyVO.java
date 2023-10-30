package kr.co.ictedu.mvc.dto;

import java.util.List;

public class SurveyVO {
	private int num;
	private String sub;
	private int code;
	private String sdate;
	private int surveyTotal;
	
	//1. n����
	private List<SurveyContentVO> survey;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public int getSurveyTotal() {
		return surveyTotal;
	}

	public void setSurveyTotal(int surveyTotal) {
		this.surveyTotal = surveyTotal;
	}

	public List<SurveyContentVO> getSurvey() {
		return survey;
	}

	public void setSurvey(List<SurveyContentVO> survey) {
		this.survey = survey;
	}
	
	
}
