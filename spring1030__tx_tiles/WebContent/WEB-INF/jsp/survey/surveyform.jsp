<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../temp/admin_header.jsp"%>
<div class="container">
	<div class="row">
		<h1>Title : ${title }</h1>
		<p>Message : ${message }</p>
		<div style="width: 350px; margin: auto">
			<%-- form start --%>
 <form method="post" action="addsurvey" >
	<table style="border-collapse: collapse; width: 450px; margin: auto">
		<thead>
			<tr><th colspan="2" style="background: #ff9933">���� �ۼ� ��</th></tr>
		</thead>
		<tbody>
<!-- 		<tr><th>��ȣ</th> -->
<!-- 			<td><input type="text" name="num" id="num"></td></tr> -->
			
			<tr><th>����</th>
			<td><input type="text" name="sub" id="sub"></td></tr>
			<tr><th>���׼�</th>
			<td><input type="number" name="code" 
			id="code" max="5" min="2" value="2" onchange="increTitle()"></td></tr>
			<tr><th>�⺻������</th>
			<td><input type="number" name="surveycnt" 
			id="surveycnt" max="100" min="0" value="2"></td></tr>
			<tr><th>��������1</th>
			<td><input type="text" name="surveytitle"> </td></tr>
			<tr>
			<tr><th>��������2</th>
			<td><input type="text" name="surveytitle"> </td></tr>
			<tr>
			<td id="target" colspan="2">
			</td> </tr>
		</tbody>
		<tfoot>
		<tr><th colspan="2"><input type="submit" value="send">
		&nbsp; <input type="button" value="list" onclick="location='surveylist'"></th></tr>
		</tfoot>
	</table>
	</form> 
		</div>
	</div>
</div>
<script>
	function increTitle(){
		var code = document.getElementById("code").value;
		var html ="<table style=\"border-collapse: collapse; width: 100%; margin: auto\">";
		for(var i = 1; i< code-1; i++){
			html += "<tr><th>��������"+(i+2)+"</th>";
			html += "<td><input type=\"text\" name=\"surveytitle\"></td></tr>";
		}
		html +="</table>";
		document.getElementById("target").innerHTML=html;
	}
</script>
</body>
</html>