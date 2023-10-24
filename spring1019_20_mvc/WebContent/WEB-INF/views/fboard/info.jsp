<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="../temp/header.jsp"%>
<article>
	<header style="color:white">FreeBoard</header>
	<ul class="list-unstyled">
	<li class="border-top my-3">ICT No1 Detail 페이지 입니다.</li>
	</ul>
	<div class="container">
	<div class="row">
		<h2>FreeBoard Detail </h2>
			<div class="row mb-3">
				<label for="subject" class="col-sm-2 col-form-label">제목</label>
				<div class="col-sm-10">
					<input type="text" name="subject" class="form-control" id="subject"
					 readonly="readonly" value="${v.subject}"
					>
				</div>
			</div>
			<div class="row mb-3">
				<label for="writer" class="col-sm-2 col-form-label">작성자</label>
				<div class="col-sm-10">
					<input type="text" name="writer" class="form-control" id="writer"
					 readonly="readonly" value="${v.writer}"
					>
				</div>
			</div>
			<div class="row">
				<label for="content" class="col-sm-2 col-form-label">내용</label>
				<div class="col-sm-10">
				<textarea name="content" rows="10" cols="50" id="content" readonly="readonly" >${v.content}</textarea>
				</div>
			</div>
			<div class="row mb-3">
				<label for="pwd" class="col-sm-2 col-form-label" >아이피</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" 
					 readonly="readonly" value="${v.reip}">
				</div>
			</div>	
			<div class="row mb-3">
				<label for="pwd" class="col-sm-2 col-form-label">날짜</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" 
					 readonly="readonly" value="${v.fdate}">
				</div>
			</div>	
			<div  class="container text-center" role="group">
			<%-- chkpwdForm을 화면출력하고, sysout으로 2파라미터값을 출력 해보기 --%>
			<form action="fboardDelete" method="post">
				<input type="hidden" name="num" value="${v.num}">
				<button class="btn btn-primary" type="button" 
			onclick="location='fboardModify?num=${v.num}'">수정</button>
				<input class="btn btn-primary" type="submit" value="삭제"/>
            	<button class="btn btn-danger" type="button" onclick="location='fboardList'">리스트</button>
			</form>
			</div>
		</div>
	</div>
</article>
<%@include file="../temp/footer.jsp"%>
