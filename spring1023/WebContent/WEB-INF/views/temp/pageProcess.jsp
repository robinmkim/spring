<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageurl" value="?cPage"/>
<tr>
	<th colspan="6" style="text-align: center;">
		<ul class="pagination" style="margin: 0 auto; width: 35%;">
			<!-- 이전 페이지 -->
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
							<a class="page-link" href="${pageurl}=${i.index}">${i.index}</a>
						</li>
					</c:otherwise>	
				</c:choose>	
			</c:forEach>
			<!-- 다음 페이지 -->
		</ul>
	</th>
	
</tr>