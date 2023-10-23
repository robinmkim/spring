<%@ page language="java" contentType="text/html; charset=EUC-KR"
   pageEncoding="EUC-KR"%>
<%@include file="../temp/header.jsp"%>
<article>
   <header style="color: white">FreeBoard</header>
   <ul class="list-unstyled">
      <li class="border-top my-3">ICT No1 Detail 페이지 입니다.</li>
   </ul>
   <div class="container">
      <div class="row">
         <h2>upListBoard Detail</h2>
         <div class="row mb-3">
            <label for="subject" class="col-sm-2 col-form-label">제목</label>
            <div class="col-sm-10">
               <input type="text" name="title" class="form-control" id="title"
                  readonly="readonly" value="${vo.title}">
            </div>
         </div>
         <div class="row mb-3">
            <label for="writer" class="col-sm-2 col-form-label">작성자</label>
            <div class="col-sm-10">
               <input type="text" name="writer" class="form-control" id="writer"
                  readonly="readonly" value="${vo.writer}">
            </div>
         </div>
         <div class="row">
            <label for="content" class="col-sm-2 col-form-label">내용</label>
            <div class="col-sm-10">
               <textarea name="content" rows="10" cols="50" id="content"
                  readonly="readonly">${vo.content}</textarea>
            </div>
         </div>
         <div class="row mb-3">
            <label for="pwd" class="col-sm-2 col-form-label">아이피</label>
            <div class="col-sm-10">
               <input type="text" class="form-control" readonly="readonly"
                  value="${vo.reip}">
            </div>
         </div>
         <div class="row mb-3">
            <label for="pwd" class="col-sm-2 col-form-label">그림 : ${vo.imgn}</label>
            <div class="col-sm-10">
                <img src="${rPath}/imgfile/${vo.imgn}" style="width: 200px; border: dotted 1px; cursor: pointer;">
            </div>
         </div>
         <div class="row mb-3">
            <label for="pwd" class="col-sm-2 col-form-label">날짜</label>
            <div class="col-sm-10">
               <input type="text" class="form-control" readonly="readonly"
                  value="${vo.bdate}">
            </div>
         </div>
         <div class="container text-center" role="group">
            <%-- chkpwdForm을 화면출력하고, sysout으로 2파라미터값을 출력 해보기 --%>
            <button class="btn btn-primary" type="button" 
			onclick="location='upboardModify?num=${vo.num}'">수정</button>
            <button class="btn btn-primary" type="button" id="deletebtn">삭제</button>
            <button class="btn btn-danger" type="button"
               onclick="location='fboardList'">리스트</button>
         </div>
      </div>
   </div>
</article>

<script type="text/javascript">
   document.getElementById("deletebtn").addEventListener("click", function() {
            var num = ${vo.num};
            //서버에 데이터를 요청하거나 서버로부터 데이터를 전송받을 수 있다.
            var xhr = new XMLHttpRequest();
            var url = "<%=request.getContextPath()%>" + "/boardDelete";
            //POST메서드로 HTTP 용청을 열고, 지정한 URL로 비동기적으로 요청을 보낸다.
            xhr.open("POST", url, true);

            // POST 요청을 보내기 전에 서버로 보낼 데이터 설정
            var data = "num=" + num;

            // HTTP 헤더 설정 (옵션)
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            // 요청이 완료되면 실행될 콜백 함수
            xhr.onreadystatechange = function() {
               if (xhr.readyState === 4) { // 요청이 완료되면
                  if (xhr.status === 200) { // HTTP 상태 코드 200은 성공을 나타냅니다.
                     alert("데이터 삭제 성공");
                     // 삭제 성공 시 목록 페이지로 이동
                     window.location.href = "<%=request.getContextPath()%>" + "/upList"; 
                  } else {
                     alert("데이터 삭제 실패");
                  }
               }
            };
            // 데이터를 보내기
            xhr.send(data);
         });
</script>