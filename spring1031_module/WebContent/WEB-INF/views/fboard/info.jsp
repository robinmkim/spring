<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../temp/header.jsp"%>
<article>
	<header style="color:white">FreeBoard</header>
	<ul class="list-unstyled">
	<li class="border-top my-3">ICT No1 Detail ������ �Դϴ�.</li>
	</ul>
	<div class="container">
	<div class="row">
	<form method="post" action="fboardDelete" name="form">
		<h2>FreeBoard Detail </h2>
			<div class="row mb-3">
			
				<label for="subject" class="col-sm-2 col-form-label">����</label>
				<div class="col-sm-10">
					<input type="text" name="subject" class="form-control" id="subject"
					value="${v.subject}" readonly="readonly"
					>
				</div>
			</div>
			<div class="row mb-3">
				<label for="writer" class="col-sm-2 col-form-label">�ۼ���</label>
				<div class="col-sm-10">
					<input type="text" name="writer" class="form-control" id="writer"
					value="${v.writer}" readonly="readonly"
					>
				</div>
			</div>
			<div class="row">
				<label for="content" class="col-sm-2 col-form-label">����</label>
				<div class="col-sm-10">
				<textarea name="content" rows="10" cols="50" id="content" readonly="readonly">${v.content }</textarea>
				</div>
			</div>
			<div class="row mb-3">
				<label for="pwd" class="col-sm-2 col-form-label">������</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" 
					value="${v.reip}" readonly="readonly">
				</div>
			</div>	
			<div class="row mb-3">
				<label for="pwd" class="col-sm-2 col-form-label">��¥</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" 
					value="${v.fdate}" readonly="readonly">
				</div>
			</div>	
			<div  class="container text-center" role="group">
			<%-- chkpwdForm�� ȭ������ϰ�, sysout���� 2�Ķ���Ͱ��� ��� �غ��� --%>
			<button class="btn btn-primary" type="button" 
			onclick="location='fboardModify?num=${v.num}'">����</button>
			<button class="btn btn-primary" type="submit" 
			>����</button>
            <button class="btn btn-danger" type="button" onclick="location='fboardList'">����Ʈ</button>
			</div>
				<input type="hidden" name="num" value="${v.num}">
	</form>
		</div>
	</div>
</article>
<%@include file="../temp/footer.jsp"%>





