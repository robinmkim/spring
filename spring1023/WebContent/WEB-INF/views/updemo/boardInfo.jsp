<%@ page language="java" contentType="text/html; charset=EUC-KR"
   pageEncoding="EUC-KR"%>
<%@include file="../temp/header.jsp"%>
<article>
   <header style="color: white">FreeBoard</header>
   <ul class="list-unstyled">
      <li class="border-top my-3">ICT No1 Detail ������ �Դϴ�.</li>
   </ul>
   <div class="container">
      <div class="row">
         <h2>upListBoard Detail</h2>
         <div class="row mb-3">
            <label for="subject" class="col-sm-2 col-form-label">����</label>
            <div class="col-sm-10">
               <input type="text" name="title" class="form-control" id="title"
                  readonly="readonly" value="${vo.title}">
            </div>
         </div>
         <div class="row mb-3">
            <label for="writer" class="col-sm-2 col-form-label">�ۼ���</label>
            <div class="col-sm-10">
               <input type="text" name="writer" class="form-control" id="writer"
                  readonly="readonly" value="${vo.writer}">
            </div>
         </div>
         <div class="row">
            <label for="content" class="col-sm-2 col-form-label">����</label>
            <div class="col-sm-10">
               <textarea name="content" rows="10" cols="50" id="content"
                  readonly="readonly">${vo.content}</textarea>
            </div>
         </div>
         <div class="row mb-3">
            <label for="pwd" class="col-sm-2 col-form-label">������</label>
            <div class="col-sm-10">
               <input type="text" class="form-control" readonly="readonly"
                  value="${vo.reip}">
            </div>
         </div>
         <div class="row mb-3">
            <label for="pwd" class="col-sm-2 col-form-label">�׸� : ${vo.imgn}</label>
            <div class="col-sm-10">
                <img src="${rPath}/imgfile/${vo.imgn}" style="width: 200px; border: dotted 1px; cursor: pointer;">
            </div>
         </div>
         <div class="row mb-3">
            <label for="pwd" class="col-sm-2 col-form-label">��¥</label>
            <div class="col-sm-10">
               <input type="text" class="form-control" readonly="readonly"
                  value="${vo.bdate}">
            </div>
         </div>
         <div class="container text-center" role="group">
            <%-- chkpwdForm�� ȭ������ϰ�, sysout���� 2�Ķ���Ͱ��� ��� �غ��� --%>
            <button class="btn btn-primary" type="button" 
			onclick="location='upboardModify?num=${vo.num}'">����</button>
            <button class="btn btn-primary" type="button" id="deletebtn">����</button>
            <button class="btn btn-danger" type="button"
               onclick="location='fboardList'">����Ʈ</button>
         </div>
      </div>
   </div>
</article>

<script type="text/javascript">
   document.getElementById("deletebtn").addEventListener("click", function() {
            var num = ${vo.num};
            //������ �����͸� ��û�ϰų� �����κ��� �����͸� ���۹��� �� �ִ�.
            var xhr = new XMLHttpRequest();
            var url = "<%=request.getContextPath()%>" + "/boardDelete";
            //POST�޼���� HTTP ��û�� ����, ������ URL�� �񵿱������� ��û�� ������.
            xhr.open("POST", url, true);

            // POST ��û�� ������ ���� ������ ���� ������ ����
            var data = "num=" + num;

            // HTTP ��� ���� (�ɼ�)
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            // ��û�� �Ϸ�Ǹ� ����� �ݹ� �Լ�
            xhr.onreadystatechange = function() {
               if (xhr.readyState === 4) { // ��û�� �Ϸ�Ǹ�
                  if (xhr.status === 200) { // HTTP ���� �ڵ� 200�� ������ ��Ÿ���ϴ�.
                     alert("������ ���� ����");
                     // ���� ���� �� ��� �������� �̵�
                     window.location.href = "<%=request.getContextPath()%>" + "/upList"; 
                  } else {
                     alert("������ ���� ����");
                  }
               }
            };
            // �����͸� ������
            xhr.send(data);
         });
</script>