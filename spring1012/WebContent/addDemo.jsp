
<%@page import="dao.FboardDao"%>
<%@page import="dto.FboardDTO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>addDemo.jsp</title>
</head>
<body>
<%
	FboardDTO vo = new FboardDTO();
	vo.setSubject("안녕하세요");
	vo.setWriter("테스형");
	vo.setContent("MyBatis 연습");
	vo.setReip(request.getRemoteAddr());
	FboardDao.getDao().addFboard(vo);
%>
</body>
</html>