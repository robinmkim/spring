<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%> 
<%@include file="../temp/header.jsp"%>
<article>
	<header style="color: white">Board Ajax Demo</header>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>
	<div>
		<%-- ���� form���� �Ʒ�ó�� ���� �ؼ� ���� �س���  --%>
		<input type="hidden" name="reip" id="reip"
			value="<%=request.getRemoteAddr()%>">
		<p>
			<input type="text" class="form-control" id="title"
				placeholder="���� �Է�(4-100)" name="title" maxlength="100">
		</p>
		<p>
			<textarea class="form-control" rows="3" id="content" name="content"
				placeholder="���� �ۼ�"></textarea>
		</p>
		<p>
			<input type="text" class="form-control" id="writer"
				placeholder="�ۼ���(2��-10��)" name="writer">
		</p>
		<%-- ���� form���� �Ʒ�ó�� ���� �ؼ� ���� �س��� �� --%>
		<button type="button" class="btn btn-outline-secondary" id="load1">AjaxListLoad</button>
		<button type="button" class="btn btn-outline-secondary" id="sendAjax">����������</button>
	</div>
	<div class="container">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>��ȣ</th>
					<th>����</th>
					<th>�ۼ���</th>
					<th>����</th>
					<th>�ۼ���¥</th>
				</tr>
			</thead>
			<tbody id="target">

			</tbody>
			<tfoot>
				<%-- page���� --%>
				<tr>
					<th colspan="5" style="text-align: center;">
						<ul class="pagination" style="margin: 0 auto; width: 35%">
							<li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
							<li class="page-item active"><a class="page-link" href="#">1</a></li>
							<li class="page-item"><a class="page-link" href="#">2</a></li>
							<li class="page-item"><a class="page-link" href="#">3</a></li>
							<li class="page-item"><a class="page-link" href="#">4</a></li>
							<li class="page-item"><a class="page-link" href="#">5</a></li>
							<li class="page-item"><a class="page-link" href="#">Next</a></li>
						</ul>
					</th>
				</tr>
				<%-- �˻����� --%>
				<tr>
					<th colspan="5">
						<form class="d-flex">
							<select class="form-control btn-mini" id="sel1"
								style="margin-left: 120px;">
								<option>1</option>
								<option>2</option>
								<option>3</option>
								<option>4</option>
							</select> <input class="form-control me-2" type="text"
								placeholder="Search.." style="width: 300px">
							<button class="btn btn-outline-secondary" type="button">Search</button>
						</form>
					</th>
				</tr>
				<tr>
					<td colspan="5" style="text-align: right;">
						<button type="button" class="btn btn-outline-secondary" id="wbtn">���ۼ�</button>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</article>
<script>
window.onload = function() {
	let number = 1;
	document.querySelector("#load1").onclick = function(e) {
		e.preventDefault();
		let param = null;
		sendRequest("boardJsonList", param, res, "get");
	}
	document.querySelector("#sendAjax").onclick = function(e) {
		e.preventDefault();
		//�������� ������ ��ü
		/*
		num:1,
		title:"������ ���� ����������0",
		writer:"��浿",
		content:"����1",
		reip:"192.168.0.113",
		bdate:"2023-06-14"
		 */
		const data = {}
		data.num=number;
		data.title=document.getElementById("title").value;
		data.content=document.getElementById("content").value;
		data.writer=document.getElementById("writer").value;
		data.reip=document.getElementById("reip").value;
		data.bdate="2023-06-14";
		let param = JSON.stringify(data); //json���� ��ȯ
		let type = "json";
		//alert(data.bdate);
		sendRequest("RequestParamDemo", param, res2, "post", type);
		number++;
	}
	//callback
	function res(){
		if(xhr.readyState === 4){
			if(xhr.status === 200){
				let res = xhr.responseText;
				let boards = JSON.parse(res);			
				let table = document.getElementById('target');

				
				let arrNum = new Array();
				let arrTitle = new Array();
				let arrWriter = new Array();
				let arrContent = new Array();
				let arrBdate = new Array();

				for(i=0; i<boards.length; i++){ // �� ��ü �������¹�
					arrTitle[i] = boards[i].title;
				      arrNum[i] = boards[i].num;
					  arrWriter[i] = boards[i].writer;
					  arrContent[i] = boards[i].content;
					  arrBdate[i] = boards[i].bdate;
				 }

				   for(i=0; i<arrTitle.length; i++){
				      let tr = document.createElement("tr");
				      
				      let td1 = document.createElement("td");           
				      td1.appendChild(document.createTextNode(arrNum[i] + ""));
				      
				      let td2 = document.createElement("td");          
				      td2.appendChild(document.createTextNode(arrTitle[i] + ""));
				      
				      let td3 = document.createElement("td");          
				      td3.appendChild(document.createTextNode(arrWriter[i]+ ""));
				      
				      let td4 = document.createElement("td");          
				      td4.appendChild(document.createTextNode(arrContent[i]+ ""));
				      
				      let td5 = document.createElement("td");          
				      td5.appendChild(document.createTextNode(arrBdate[i]+ ""));
				      
				      tr.appendChild(td1);
				      tr.appendChild(td2);
				      tr.appendChild(td3);
				      tr.appendChild(td4);
				      tr.appendChild(td5);
				      
				      target.appendChild(tr);
				   }

			}
		}
	}
	function res2(){
		if(xhr.readyState === 4){
			if(xhr.status === 200){
				let res = xhr.responseText;
				console.log("res2 ==>" +res)
			}
		}
	}
	
}
</script>