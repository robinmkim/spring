<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="../temp/header.jsp"%>
<article>
	<header style="color:white;">FreeBoard</header>
	<ul class="list-unstyled">
	<li class="border-top my-3">ICT 1�� FreeBoard ������ �Դϴ�.</li>
	</ul>
	<div class="container">
	<div class="row">
		<h2>FreeBoard</h2>
		<form method="post" action="fboardInsert" autocomplete="off">
			<input type="hidden" name="reip" value="<%=request.getRemoteAddr()%>">
			<div class="row mb-3">
				<label for="subject" class="col-sm-2 col-form-label">����</label>
				<div class="col-sm-10">
					<input type="text"  class="form-control" id="subject" name="subject">
				</div>
			</div>
			<div class="row mb-3">
				<label for="writer" class="col-sm-2 col-form-label">�ۼ���</label>
				<div class="col-sm-10">
					<input type="text"  class="form-control" id="writer" name="writer" >
				</div>
			</div>
			<div class="row">
				<label for="content" class="col-sm-2 col-form-label">����</label>
				<div class="col-sm-10">
				<textarea  rows="10" cols="50" id="content" name="content"></textarea>
				</div>
			</div>
			<div  class="container text-center" role="group">
			<button class="btn btn-primary" type="submit">���ۼ�</button>
            <button class="btn btn-danger" type="button" onclick="location='fboardList'">����Ʈ</button>
			</div>
		</form>
		</div>
	</div>
</article>
<%@include file="../temp/footer.jsp"%>