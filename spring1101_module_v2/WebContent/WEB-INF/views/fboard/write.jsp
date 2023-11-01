<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../temp/header.jsp"%>
<article>
	<header style="color:white;">FreeBoard</header>
	<ul class="list-unstyled">
	<li class="border-top my-3">ICT01�� FreeBoard ������ �Դϴ�.</li>
	</ul>
	<div class="container">
	<div class="row">
		<h2>FreeBoard</h2>
		<form method="post" action="fboardInsert" autocomplete="off">
			<input type="hidden" name="reip" value="<%=request.getRemoteAddr()%>">
			<div class="row mb-3">
				<label for="subject" class="col-sm-2 col-form-label">����</label>
				<div class="col-sm-10">
					<input type="text" name="subject" class="form-control" id="subject">
				</div>
			</div>
			<div class="row mb-3">
				<label for="writer" class="col-sm-2 col-form-label">�ۼ���</label>
				<div class="col-sm-10">
					<input type="text" name="writer" class="form-control" id="writer">
				</div>
			</div>
			<div class="row">
				<label for="content" class="col-sm-2 col-form-label">����</label>
				<div class="col-sm-10">
				<textarea name="content" rows="10" cols="50" id="content"></textarea>
				</div>
			</div>
<!-- 			<div class="row mb-3">
				<label for="pwd" class="col-sm-2 col-form-label">��й�ȣ</label>
				<div class="col-sm-10">
					<input type="password" name="pwd" class="form-control" id="pwd">
				</div>
			</div>	 -->
			<div  class="container text-center" role="group">
			<button class="btn btn-primary" type="submit">���ۼ�</button>
            <button class="btn btn-danger" type="button" onclick="location='tboardList'">����Ʈ</button>
			</div>
		</form>
		</div>
	</div>
</article>
<%@include file="../temp/footer.jsp"%>