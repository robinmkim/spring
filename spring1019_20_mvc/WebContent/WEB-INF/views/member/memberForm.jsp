<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="<%=application.getContextPath() %>/resources/js/ajaxdemo1.js"></script>
</head>
<body>
<%@include file="../main/menu.jsp" %>
<form method="post" action="memberIn">
     <table style="width: 80%; margin: auto">
   		<tr>
   		   <td>���̵�</td>
   		   <td>
   		   <input type="text" name="id" id="id" maxlength="10" 
   		   style="width: 130px"> 
   		   <input type="button" value="�ߺ�Ȯ��" id="idChkBtn" 
   		   style="width: 150px; font-size: 10px; height: 30px;">
   		   </td>
   		 </tr>
   		 <tr><td colspan="2" id="target"></td></tr>
   		  <tr>
   		   <td>��й�ȣ</td>
   		   <td><input type="password" name="pwd" maxlength="10"> 
   		   </td>
   		 </tr>
   		  <tr>
   		   <td>�̸�</td>
   		   <td><input type="text" name="uname"> 
   		   </td>
   		 </tr>
   		 <%-- �迭Ÿ���� �Ķ���� ó���ϱ�  --%>
   		 <tr>
   		   <td>üũ</td>
   		   <td><input type="checkbox" name="chk" value="1"> 1
   		   <input type="checkbox" name="chk" value="2"> 2
   		   <input type="checkbox" name="chk" value="3"> 3
   		   </td>
   		 </tr>
   		 
   		  <tr><td colspan="2">
   		  <input type="submit" value="����">
   		  </td></tr>
   </table>
     </form>
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