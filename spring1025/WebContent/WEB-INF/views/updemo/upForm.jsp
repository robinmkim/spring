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
		<%-- boardForm ��� �� �ڸ�. --%>
		<form action="uploadpro" method="post" enctype="multipart/form-data" id="upform">
			<input type="hidden" name="reip" value="<%=request.getRemoteAddr()%>">
			<div class="form-group">
				<label for="title">����</label>
				<input type="text" class="form-control" id="title"
					placeholder="���� �Է�(5-100)" name="title" maxlength="100"
					required="required" pattern=".{5,100}">
			</div>
			<div class="form-group">
				<label for="content">����</label>
				<textarea class="form-control" rows="5" id="content" name="content"
					placeholder="���� �ۼ�"></textarea>
			</div>
			<div class="form-group">
				<label for="writer">�ۼ���</label> <input type="text"
					class="form-control" id="writer" placeholder="�ۼ���(2��-30��)"
					name="writer" pattern=".{2,30}">
			</div>

			
			<div class="form-group">
				<label for="title">�̹���</label>
				<div class="col-sm-10"><input type="file" class="form-control" id="mfile"
					name="mfile" >
			    </div>		
			    <div class="col-sm-10">
			    <img src="${rPath}/image/noimage.jpg" id="imgx"
			    style="width: 210px; border: dotted 1px; margin: 5px 5px">
			    
			    </div>
			</div>
			<div class="form-group" style="text-align: right; margin-top: 10px;">
				<button type="submit" class="btn btn-primary">���</button>
				 <input type="button" value="����Ʈ"  class="btn btn-danger"
				 onclick="location='upList'"
				  />
			</div>
		</form>

	</div>

</article>
<script>
	function readURL(input) {
		if(input.files && input.files[0]){
			//js I/O
			var reader = new FileReader();
			//listener
			reader.onload = function(e){
				console.log("event occured" + e.target.result);
				//<img src> ������, attr("�Ӽ���", "��") -> (img src="")
				$('#imgx').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	$(function(){
		$('#mfile').change(function(){
			console.log($(this).val());
			if($(this).val().length > 0){
				readURL(this);
			} else{
				console.log("No Image")
			}
		})
	})
</script>
<%@include file="../temp/footer.jsp"%>