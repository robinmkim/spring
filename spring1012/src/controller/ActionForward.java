package controller;

public class ActionForward {
	private String url; //모델이 실행한 후 이동할 viewName
	private boolean method; // 모델이 실행 후 이동할 이동 방식 => 메서드에 들어오는 값은  forward || redirect

	public ActionForward() {
	}

	//hello, false: forward, true: redirect
	public ActionForward(String url, boolean method) {
		this.url = url;
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isMethod() {
		return method;
	}

	public void setMethod(boolean method) {
		this.method = method;
	}
	
	
	
}
