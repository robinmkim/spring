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
		<%-- boardForm 들어 갈 자리. --%>
		<form action="uploadpro" method="post" enctype="multipart/form-data" id="upform">
			<input type="hidden" name="reip" value="<%=request.getRemoteAddr()%>">
			<div class="form-group">
				<label for="title">제목</label>
				<input type="text" class="form-control" id="title"
					placeholder="제목 입력(5-100)" name="title" maxlength="100"
					required="required" pattern=".{5,100}">
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea class="form-control" rows="5" id="content" name="content"
					placeholder="내용 작성"></textarea>
			</div>
			<div class="form-group">
				<label for="writer">작성자</label> <input type="text"
					class="form-control" id="writer" placeholder="작성자(2자-30자)"
					name="writer" pattern=".{2,30}">
			</div>

			
			<div class="form-group">
				<label for="title">이미지</label>
				<div class="col-sm-10"><input type="file" accept="image/*" class="form-control" id="mflist"
					name="mflist" multiple="multiple">
			    </div>		
			    <div class="col-sm-10">
			    <img src="${rPath}/image/noimage.jpg" id="imgx"
			    style="width: 210px; border: dotted 1px; margin: 5px 5px">
			    
			    </div>
			</div>
			<div class="form-group">
				<label for="title">비디오</label>
				<div class="col-sm-10"><input type="file" accept="video/*" class="form-control" id="vfile"
					name="mfile" >
			    </div>		
			    <div class="col-sm-10">
			    <img src="${rPath}/image/noimage.jpg" id="imgx"
			    style="width: 210px; border: dotted 1px; margin: 5px 5px">
			    
			    </div>
			</div>
			<div class="form-group" style="text-align: right; margin-top: 10px;">
				<button type="submit" class="btn btn-primary">등록</button>
				 <input type="button" value="리스트"  class="btn btn-danger"
				 onclick="location='boardlist'"
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
				//<img src> 선택자, attr("속성명", "값") -> (img src="")
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
	
	console.log($('#title').value);
	console.log('#content');
	console.log('#writer');
	console.log('#mflist[]');
	console.log('#vfile');
</script>
<%@include file="../temp/footer.jsp"%>