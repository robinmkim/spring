<%@page import="dao.FboardDao"%>
<%@page import="dto.FboardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>listDemo</title>
</head>
<body>
	<%
		List<FboardDTO> list = FboardDao.getDao().listFboard();
	%>
	<%
		for (FboardDTO e : list) {
	%>
	<p><%=e.getNum()%>
		|
		<%=e.getSubject()%>
		|
		<%=e.getWriter()%>
	</p>
	<%
		}
	%>
</body>
</html>