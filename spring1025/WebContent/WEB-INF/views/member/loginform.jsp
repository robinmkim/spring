<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="../temp/header.jsp"%>
<article>
	<header>
		<h1>Login Form</h1>
	</header>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>
	<div class="container">
		<form method="post" action="loginProcess" id="loginInfo">
			<div class="form-group">
				<label for="id">ID</label>
				<input type="text" name="id" id="id" 
				maxlength="10" placeholder="���̵� �Է�"
				class="form-control" required="required"
				pattern=".{2,10}">
			 </div>
			 <div class="form-group">
				<label for="pwd">PW</label>
				<input type="password" name="pwd" id="pwd" 
				maxlength="10" placeholder="********"
				class="form-control" required="required">
			 </div>
			 <div class="form-group">
			 	<button type="submit" class="btn btn-primary">�α���</button>
			 	<button type="button" class="btn btn-primary">���̵�/��й�ȣ ã��</button>
			 </div>
			
			</table>
		</form>
	</div>
</article>
<%@include file="../temp/footer.jsp"%>