<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>        
<c:if test="${today !=null }">    
<li class="border-top my-3">������ ��¥��
 <b style="color:orange">${today }</b> �̰�, ��� ������ 
	���̵��Ǵ� ${reip } �Դϴ�.
</li>
</c:if>