
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
	vo.setSubject("�ȳ��ϼ���");
	vo.setWriter("�׽���");
	vo.setContent("MyBatis ����");
	vo.setReip(request.getRemoteAddr());
	FboardDao.getDao().addFboard(vo);
%>
</body>
</html>