<%@page import="java.sql.Connection"%>
<%@page import="ex3.MyConn"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>connTest.jsp</title>
</head>
<body>
<%
	Connection con = MyConn.getDs();
%>
<%=con %>
<%
	con.close();
%>
</body>
</html>