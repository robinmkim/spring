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
		<h2>시각화 영역</h2>
		<div id="chart1"></div>
	</div>
	<div>
		<h2>Login Log List</h2>
		<table class="table table-hover">
			<thead>
				<tr class="table-secondary">
					<th scope="col">번호</th>
					<c:if test="${sessionScope.sessionID == 'admin'}">
						<th scope="col">아이디</th>
					</c:if>
					<th scope="col">아이피</th>
					<th scope="col">기기</th>
					<th scope="col">상태</th>
					<th scope="col">접속일</th>
				</tr>
			</thead>
			<tbody>
				<%-- 반복시작  --%>
				<c:forEach var="item" items="${list}">
					<tr class="table-warning">
						<td>${item.num}</td>
						<c:if test="${sessionScope.sessionID == 'admin'}">
							<td>${item.idn}</td>
						</c:if>
						<td>${item.reip}</td>
						<td>${item.uagent}</td>
						<td>${item.status}</td>
						<td>${item.logtime}</td>
					</tr>
				</c:forEach>
				<%-- 반복끝  --%>
			</tbody>
		</table>
	</div>

</article>
<script src="https://d3js.org/d3.v3.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$.ajaxSetup({
		cache : false
	});
	$.ajax({
		url : "${cPath}/jsonTest1",
		success : function(jsondata) {
			console.log(jsondata[0].sub);
			console.log(jsondata[1]);
			console.log("---------------------");
			var chart = c3.generate({
				bindto : '#chart1',
				data : {
					json : [ jsondata[1] ],
					keys : {
						value : Object.keys(jsondata[1]),
					},
					type : 'donut'
				},
				donut : {
					title : jsondata[0].sub,
				},
			});
		},
		error : function(e) {
			console.log("error:" + e);
		}
	});
</script>
<%@include file="../temp/footer.jsp"%>

