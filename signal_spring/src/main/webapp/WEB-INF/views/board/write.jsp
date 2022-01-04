<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
#preview img {
	width: 100px;
	height: 100px;
}

#preview p {
	text-overflow: ellipsis;
	overflow: hidden;
}

.preview-box {
	border: 1px solid;
	padding: 5px;
	border-radius: 2px;
	margin-bottom: 10px;
}
</style>
<script>
	var files = {};
	var previewIndex = 0;
	$(document).ready(function() {
		$('#attach input[type=file]').change(function() {
			addPreview($(this)); //preview form 추가하기
		})

		$("#saveButton").on("click", function() {
			if (!$("#bName").val()) {
				$("#saveButton").attr("type", "button");
				alert("제목을 작성해주세요");
			} else if (!$("#bContent").val()) {
				$("#saveButton").attr("type", "button");
				alert("내용을 작성해주세요");
			}
		})
	});
	// image preview 기능 구현
	// input = file object[]
	function addPreview(input) {

		if (input[0].files.length < 7) {
			for (var fileIndex = 0; fileIndex < input[0].files.length; fileIndex++) {
				var file = input[0].files[fileIndex];

				var reader = new FileReader();
				reader.onload = function(img) {
					if (validation(file.name, file.size,
							$(".preview-box").length)) {
						return;
					} else {
						//div id="preview" 내에 동적코드추가.
						//이 부분을 수정해서 이미지 링크 외 파일명, 사이즈 등의 부가설명을 할 수 있을 것입니다
						var imgNum = previewIndex++;
						$("#preview")
								.append(
										"<div class=\"preview-box\" value=\"" + imgNum +"\">"
												+ "<img class=\"thumbnail\" src=\"" + img.target.result + "\"\/>"
												+ "<p class=\"ui grey inverted header\">"
												+ file.name
												+ "</p>"
												+ "<a class=\"ui negative button\" href=\"#\" value=\""
												+ imgNum
												+ "\" onclick=\"deletePreview(this)\">"
												+ "Delete" + "</a>" + "</div>");
						console.log(img.target.result);
						files[imgNum] = file;
					}
				};
				reader.readAsDataURL(file);

			}
		} else
			alert("이미지는 6개까지 업로드 할 수 있습니다.");
	}

	//preview 영역에서 삭제 버튼 클릭시 해당 미리보기이미지 영역 삭제
	function deletePreview(obj) {
		var imgNum = obj.attributes['value'].value;
		delete files[imgNum];
		$("#preview .preview-box[value=" + imgNum + "]").remove();
	}

	//client-side validation
	//always server-side validation required
	function validation(fileName, size, count) {
		fileName = fileName + "";
		var fileNameExtensionIndex = fileName.lastIndexOf('.') + 1;
		var fileNameExtension = fileName.toLowerCase().substring(
				fileNameExtensionIndex, fileName.length);
		if (!((fileNameExtension === 'jpg') || (fileNameExtension === 'gif') || (fileNameExtension === 'png'))) {
			alert('jpg, gif, png의 형식의 파일만 업로드 할 수 있습니다.');
			return true;
		} else if (size > 20000000) {
			alert('이미지 용량은 20MB를 넘을 수 없습니다.');
			return true;
		} else if (count > 5) {
			alert("이미지는 6개까지 업로드 할 수 있습니다.");
			return true;
		} else {
			return false;
		}
	}
</script>
<body>
	<div id='content' class="ui container">
		<br> <br> <br>
		<form id="uploadForm" action="/board/complete" method="post"
			enctype="multipart/form-data" class="ui form">

			<div class="field">
				<h3 class="ui dividing inverted header">Title</h3>
				<input id="bName" name="bName" type="text" class="ui input">
			</div>

			<div class="field">
				<h3 class="ui dividing inverted header">Text</h3>
				<textarea id="bContent" name="bContent"></textarea>
			</div>

			<!-- 첨부 버튼 -->
			<div id="attach">
				<label class="ui button inverted labeled icon" for="uploadInputBox">
					<i class="upload icon"></i>Image Upload
				</label> <input id="uploadInputBox" style="display: none" type="file"
					name="filedata" multiple />
			</div>
			<br>

			<!-- 미리보기 영역 -->
			<div id="preview" class="content"></div>
			<div>
				<button id="saveButton" class="ui purple button">Save</button>
			</div>
		</form>
	</div>
</body>
</html>