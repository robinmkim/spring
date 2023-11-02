<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- temp/pageProcess.jsp --%>
<c:set var="commurl" value="?num=${num}&cPage" />
<tr>
	<th colspan="6" style="text-align: center;">
		<ul class="pagination" style="margin: 0 auto; width: 35%;">
			<!-- 이전 페이지 -->
			<c:choose>
				<c:when test="${startPage <= page.pagePerBlock }">
					<li class="page-item disabled">
					<a class="page-link" href="#">Previous</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item active"><a class="page-link"
						href="${commurl}=${startPage -1 }"> Previous </a></li>
				</c:otherwise>
			</c:choose>
			<!-- 현재 페이지 startPage, endPage -->
			<c:forEach varStatus="i" begin="${startPage}" end="${endPage}">
				<c:choose>
					<c:when test="${i.index == page.nowPage}">
						<li class="page-item active">
							<a class="page-link" href="#">${i.index}</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="page-item">
							<a class="page-link" href="${commurl}=${i.index}">${i.index}</a>
						</li>
					</c:otherwise>	
				</c:choose>	
			</c:forEach>
			<!-- 다음 페이지 -->
			<c:choose>
				<c:when test="${endPage >= page.totalPage }">
				<li class="page-item disabled"><a class="page-link" href="#">
				Next
				</a></li>
				</c:when>
				<c:otherwise>
				<li class="page-item active">
					<a href="${commurl }=${endPage + 1 }" class="page-link"> Next </a>
				</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</th>
	
</tr>