<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="../temp/header.jsp"%>
<article>
	<header style="color: white">FileUpLoad Demo</header>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>
	<div class="container">

		<div class="container-fluid px-4">
			<h1 class="mt-4">Board Detail</h1>
			<div class="card mb-4">
				<div class="card-body">
					<form>
						<div class="mb-3 mt-3">
							<label class="form-label">��ȣ</label> ${vo.num}
						</div>
						<div class="mb-3">
							<label class="form-label">����</label> <input type="text"
								class="form-control" id="title" name="title" value="${vo.title}"
								disabled>
						</div>
						<div class="mb-3">
							<label class="form-label">����</label>
							<textarea class="form-control" id="content" name="content"
								disabled>${vo.content}</textarea>
						</div>
						<div class="mb-3">
							<label class="form-label">�ۼ���¥</label> ${vo.bdate}
						</div>
						<div class="mb-3">
							<label class="form-label">�ۼ���</label> <input type="text"
								class="form-control" id="writer" name="writer"
								value="${vo.writer}" disabled>
						</div>
						<div class="mb-3">
							<label class="form-label">�̹���</label>

							<c:forEach var="e" items="${vo.imglist}">
								<img src="${rPath}/imgfile/${e}"
									style="width: 80px; border: dotted 1px; cursor: pointer;">
							</c:forEach>

						</div>
						<div class="mb-3">
							<label class="form-label">����</label>
							<video muted autoplay loop>
								<source src="${rPath}/videofile/${vo.vidn}" type="video/mp4">
							</video>
						</div>

						<div class="mb-3">
							<label class="form-label">������</label> ${vo.reip}
						</div>
						<a href="boardlist" class="btn btn-outline-secondary">list</a> <a
							href="boardmodify?num=${vo.num}" class="btn btn-outline-warning">modify</a>
						<a href="boarddelete?num=${vo.num}" class="btn btn-outline-danger"
							onclick="return confirm('�����Ͻðڽ��ϱ�?')">remove</a>
					</form>
				</div>
			</div>
			<%-- ��� ���� ���� --%>
			<div class="card mb-4">
				<div class="card-body">
					<fieldset>
						<legend>MyBoard Detail</legend>

						<form method="post" action="bcominsert">
							<input type="hidden" name="reip"
								value="<%=request.getRemoteAddr()%>"> <input
								type="hidden" name="cnum" value="${vo.num }">
							<div class="row mb-3">
								<div class="col-sm-10">
									<label class="col-sm-2 col-form-label">writer</label> <input
										type="text" name="cwriter" id="uwriter"
										placeholder="writer�� �Է��ϼ���." />
									<textarea name="ccontent" class="form-control" id="ucontent"
										rows="3"></textarea>
								</div>
								<div class="mt-2 col-sm-10 d-flex justify-content-end">
									<input type="submit" value="���" id="btn1" class="btn btn-info" />
								</div>
								<div class="col-sm-10">
									<table class="table">
										<thead>
											<tr>
												<td>no</td>
												<td>writer</td>
												<td>content</td>
												<td>reip</td>
												<td>date</td>
											</tr>
										</thead>
										<tbody id="comm">
											<c:forEach var="e" items="${listcomm}">
												<tr>
													<td>${e.num}</td>
													<td><a href="">${e.cwriter}</a></td>
													<td>${e.ccontent}</td>
													<td>${e.reip}</td>
													<td>${e.cdate}</td>
												</tr>
											</c:forEach>
										</tbody>

									</table>
								</div>
							</div>
						</form>
						<%@include file="../temp/commpageProcess.jsp"%>
					</fieldset>
				</div>
			</div>

			<%-- ��� ���� �� --%>

		</div>

	</div>
</article>
<script>
    var page = 1;
    var num = ${vo.num};
    var isLoading = false;

    function fetchData() {
    	
        if (isLoading) return;
		//�ε��� �����ְ� 
        isLoading = true;
        $('#loading').show();

        setTimeout(function () {
            $.ajax({
                url: '/spring1101_module_v2/api/board/boardcommlist',
                type: 'GET',
                data: {
                    page: page,
                    num: num,
                },
                success: function (data) {
                	console.log(data);
                    // ajax�� json �����͸�  ���������� �����͸� �޾ƿ� ��� ó��
                    console.log(data.commList.length)
                    if (data.commList.length > 0) {
                        for (var i = 0; i < data.commList.length; i++) {
                            // Create a new table row for each item in data.upboardList
                            var newRow = '<tr>' +
                                '<td>' + data.commList[i].num + '</td>' +
                                '<td>' + data.commList[i].cwriter + '</td>' +
                                '<td>' + data.commList[i].ccontent + '</td>' +
                                '<td>' + data.commList[i].reip + '</td>' +
                                '<td>' + data.commList[i].cdate + '</td>' +
                                '</tr>';
                            // Append the new row to your table
                            $('#comm').append(newRow);
                        }
                        page++;
                    }
                },
                complete: function () {
            		//�Ϸ�� �Ŀ��� �ε��� ���̵���.
                	isLoading = false;
            
                    $('#loading').hide();
                }
            });
        }, 500); 
    }

    // �ʱ⿡ json���� �޾ƿ� ������ �Ѹ��� 
    fetchData();

    // ��ũ�� �̺�Ʈ �����ϴ� �ڵ鷯 
    $(window).scroll(function () {
    	  // ��ũ���� �Ʒ��� 50px ���Ϸ� ������ �� fetchData �Լ� ȣ��
        if ($(window).scrollTop() + $(window).height() >= $(document).height() - 50) {
          
            fetchData();
        }
    });
</script>
<%@include file="../temp/footer.jsp"%>