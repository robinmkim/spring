<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="../temp/header.jsp"%>
<article>
	<header>
		<h1>FileUpload Demo</h1>
	</header>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>
	<div class="container">
<%--contents --%>	
	<form method="post" action="memberIn">
		<table style="width: 80%; margin: auto " class="table table-hover">
			<tr>
				<td>���̵�</td>
				<td><input type="text" name="id" id="id" maxlength="10"
					style="width: 130px"> 
					<input type="button" value="�ߺ�Ȯ��"
					id="idChkBtn" style="width: 150px; 
					font-size: 10px; height: 30px;" class="btn btn-warning">
				</td>
			</tr>
			<tr>
				<td colspan="2" id="target"></td>
			</tr>
			<tr>
				<td>��й�ȣ</td>
				<td><input type="password" name="pwd" maxlength="10"></td>
			</tr>
			<tr>
				<td>�̸�</td>
				<td><input type="text" name="name"></td>
			</tr>
			<%-- �迭Ÿ���� �Ķ���� ó���ϱ�  
				chkarr="1,2"
			--%>
			<tr>
				<td>�̸���</td>
				<td><input type="email" name="email" id="email"></td>
			</tr>
			<tr>
				<td>��ȭ��ȣ</td>
				<td><input type="tel" name="tel" id="tel"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="����"></td>
			</tr>
		</table>
	</form>
	</div>

</article>	
	
	<script>
	window.onload=function(){
		document.querySelector("#idChkBtn").onclick=function(e){
			//�������� ���� ��� 
			e.preventDefault();
			let param = "id="+document.getElementById("id").value;
			sendRequest("idcheck", param, res, "get");
		};
		function res(){
			if(xhr.readyState === 4){
				if(xhr.status === 200){
					let idCnt = parseInt(xhr.responseText);
					console.log(idCnt+":"+typeof(idCnt));
					let msg ="";
					if(idCnt === 0){
						msg ="��밡���� ���̵� �Դϴ�.";
						document.getElementById("target").style.backgroundColor="orange";
					}else{
						msg ="������� ���̵� �Դϴ�.";
						document.getElementById("target").style.backgroundColor="red";
						document.getElementById("target").style.color='white';
					}
					document.getElementById("target").innerHTML=msg;
				}
			}
		}
	};
	</script>
</body>
</html>