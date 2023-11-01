<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- temp/pageProcess.jsp --%>
<c:set var="pageurl" value="?searchType=${searchType}&searchValue=${searchValue}&cPage" />
<tr>
	<th colspan="6" style="text-align: center;">
		<ul class="pagination" style="margin: 0 auto; width: 35%">
			<%-- ���������� --%>
			<c:choose>
				<c:when test="${startPage <= page.pagePerBlock }">
					<li class="page-item disabled">
					<a class="page-link" href="#">Previous</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item active"><a class="page-link"
						href="${pageurl }=${startPage -1 }"> Previous </a></li>
				</c:otherwise>
			</c:choose>
			<%-- ���� ������ startPage, endPage--%>
			<%--nowPage �� �ش��� ���� ������ ��ũ�� �ɾ ������ �޴� ���� ����--%>
			<c:forEach varStatus="i" begin="${startPage }" end="${endPage }">
				<%--���� �������� ��ũ�� ���� , ��ũ�� ���� --%>
				<c:choose>
					<c:when test="${i.index == page.nowPage }">
						<li class="page-item active"><a class="page-link" href="#">${i.index }</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="${pageurl }=${i.index }">${i.index }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<%-- ���������� --%> 
			<c:choose>
				<c:when test="${endPage >= page.totalPage }">
				<li class="page-item disabled"><a class="page-link" href="#">
				Next
				</a></li>
				</c:when>
				<c:otherwise>
				<li class="page-item active">
					<a href="${pageurl }=${endPage + 1 }" class="page-link"> Next </a>
				</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</th>
</tr>
