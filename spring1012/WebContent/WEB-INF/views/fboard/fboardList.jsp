<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR"> 
<style type="text/css">
table {
  border-collapse: separate;
  border-spacing: 0;
  width: 100%;
}
th,
td {
  padding: 6px 15px;
}
th {
  background: #42444e;
  color: #fff;
  text-align: left;
}
tr:first-child th:first-child {
  border-top-left-radius: 6px;
}
tr:first-child th:last-child {
  border-top-right-radius: 6px;
}
td {
  border-right: 1px solid #c6c9cc;
  border-bottom: 1px solid #c6c9cc;
}
td:first-child {
  border-left: 1px solid #c6c9cc;
}
tr:nth-child(even) td {
  background: #eaeaed;
}
tr:last-child td:first-child {
  border-bottom-left-radius: 6px;
}
tr:last-child td:last-child {
  border-bottom-right-radius: 6px;
}
a{
	text-decoration: none;
}
</style>

<title>List</title>
</head>
<body>
<h2>MyBatis List Print</h2>
<table>
	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>날짜</th>
		</tr>
	</thead>
	<tfoot>
		<tr>
			<th colspan="5">
				<input type="button" value="writer">
			</th>
		</tr>
	</tfoot>
	<tbody>
		<c:forEach var="e" items="${list}">
		<tr>
			<td>${e.num}</td>
			<td><a href="#">${e.subject}</a></td>
			<td>${e.writer}</td>
			<td>${e.hit}</td>
			<td>${e.fdate}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>