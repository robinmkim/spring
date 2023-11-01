<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../temp/header.jsp"%>
<article>
	<header style="color:white">FreeBoard</header>
	<ul class="list-unstyled">
	<li class="border-top my-3">ICT No1:: FreeBoard List ������ �Դϴ�.</li>
	</ul>
	<div class="container">
	<div class="row">
			<h2>FreeBoard List</h2>
			<table class="table table-hover">
				<thead>
					<tr class="table-secondary">
						<th scope="col">��ȣ</th>
						<th scope="col">����</th>
						<th scope="col">�ۼ���</th>
						<th scope="col">��ȸ��</th>
						<th scope="col">��¥</th>
					</tr>
				</thead>
				<tbody>
				<%-- �ݺ�����  --%>
				<c:forEach var="e" items="${list }">
					<tr>
						<th scope="row">${e.num }</th>
						<td>
						<a href="fboardHit?num=${e.num }" class="link-secondary">
						${e.subject }</a>
						</td>
						<td>${e.writer }</td>
						<td>${e.hit }</td>
						<td>${e.fdate }</td>
					</tr>
                 </c:forEach>
				<%-- �ݺ���  --%>	
				</tbody>
				<tfoot>
					<!-- 
				https://getbootstrap.com/docs/5.0/utilities/text/	
				<p class="text-start">
				<p class="text-center">
				<p class="text-end">
				 -->
					<tr>
						<th colspan="5" class="text-end">
						<!-- https://getbootstrap.com/docs/5.0/components/buttons/ -->
						<button  class="btn btn-outline-danger btn-sm" 
						onclick="location='fboardWrite'">���ۼ�</button>
						</th>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</article>
<%@include file="../temp/footer.jsp"%>