<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="../temp/header.jsp"%>
<article>
	<div class="container">
		<h1>${vo.name}님 가입을 축하 드립니다.</h1>
		<span>${vo.id} 입니다.</span>
		<p>가입 날짜: ${vo.mdate}</p>

	</div>

</article>
<%@include file="../temp/footer.jsp"%>