<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${boardVO.bName}</title>
</head>
<script>
	function goUpdateBoard() {
		$("#goUpdate").click();
	}
	function reloadCall() {
		location.reload(true);
	}

	function deleteBoard(bNo) {
		var isDelete = confirm("삭제 시 복구 되지 않습니다.");
		if (isDelete) {
			var param = {
				bNo : bNo
			};
			var ajax = new AjaxUtil("/board/delete", param);
			ajax.send(function(){
				location.href="/board?page=1&block=1";
			});
		}
	}

	function addComment(bNo, uiNickName, iconName) {
		var bcText = $("#bcText").val();
		if (uiNickName != null) {
			if(bcText){
			var param = {
				bNo : bNo,
				bcText : bcText,
				uiNickName : uiNickName,
				iconName : iconName
			}
			}else{
				alert("댓글을 입력하세요");
				return;
			}
			var ajax = new AjaxUtil("/board/comment", param);
			ajax.send(reloadCall);
		}else{
			alert("로그인이 필요합니다!");
		}
	}

	function boardReconmmand(bNo, uiNo) {
		if (uiNo) {
			var param = {
				bNo : bNo,
				uiNo : uiNo
			};
			var ajax = new AjaxUtil("/board/recommand", param);
			ajax.send(reloadCall);
		} else {
			alert("로그인이 필요합니다");
		}
	}
	
	function boardExpose(bNo, uiNo) {
		if (uiNo) {
			var param = {
				bNo : bNo,
				uiNo : uiNo
			};
			var ajax = new AjaxUtil("/board/expose", param);
			ajax.send(reloadCall);
		} else {
			alert("로그인이 필요합니다");
		}
	}

	function commentDelete(bcNo,bNo) {
		var isDelete = confirm("삭제 시 복구 되지 않습니다.");

		if (isDelete) {
			var param = {
				bNo:bNo,
				bcNo : bcNo
			};
			var ajax = new AjaxUtil("/board/comment/delete", param);
			ajax.send(reloadCall);
		}
	}
</script>
<link href='https://fonts.googleapis.com/css?family=Faustina'
	rel='stylesheet'>
<link href='https://fonts.googleapis.com/css?family=Fjalla One'
	rel='stylesheet'>
<style>
body p {
	font-family: 'Faustina';
	font-size: 22px;
}

h1 h2 h3 h4 {
	font-family: 'Fjalla One';
}
</style>
<body>
	<div id='content' class="ui small segment" style="width: 80%; margin:100 0 0 180">
		<div class="segment">
			<h4 id="bNo" style='font-family: Fjalla one;'
				class="title ui left floated purple header">번호 : ${boardVO.bNo}</h4>
			<h3 style='font-family: Fjalla one;'
				class="title ui left floated header">제목 : ${boardVO.bName}</h3>
			<h4 class="ui right floated header">
				<i class="user circle icon"></i>글쓴이 : ${boardVO.uiNickName}
			</h4>
			<h4 class="ui right floated header">조회수 : ${boardVO.bHit}</h4>
		</div>
		<c:if test="${loginUserInfoVO.uiNickName == boardVO.uiNickName}">
			<button id="delete"
				class="title ui right floated negative labeled button icon"
				onclick="deleteBoard('${boardVO.bNo}')" style="margin-top: -5">
				<i class="trash alternate icon"></i>삭제
			</button>
			<button id="update"
				class="ui right floated basic labeled top attached button icon"
				onclick="goUpdateBoard()" style="margin-top: -5">
				<i class="edit outline alternate icon"></i>수정
			</button>
		</c:if>

		<div class="ui clearing divider"></div>

		<c:forEach items="${imageVOList}" var="imageVO">
			<img class="ui spaced image"
				src="/resources/uploadImg/${imageVO.imgId}">
		</c:forEach>

		<p>${boardVO.bContent}</p>
		<br> <br>
		<button class="ui labeled basic button icon"
			onclick="boardReconmmand('${boardVO.bNo}','${loginUserInfoVO.uiNo}')">
			<i class="thumbs up outline icon"></i>추천 : ${boardVO.bRecom}
		</button>
		<button class="ui labeled inverted yellow button icon"
			onclick="boardExpose('${boardVO.bNo}','${loginUserInfoVO.uiNo}')">
			<i class="exclamation icon"></i>신고
		</button>
		<div id='content' class="ui segment">
			<div class="ui comments">
				<h3 class="ui dividing header">Comments</h3>
				<c:forEach items="${comentList}" var="comment">
					<div>
						<div class="comment">
							<div class="content">
								<a class="ui avatar threaded image"> <i
									class="large ${comment.iconName} middle aligned icon"></i>
								</a> <a class="author">${comment.uiNickName}</a> <span class="date">(${comment.bcRegDate})</span>
								<c:if test="${loginUserInfoVO.uiNickName == comment.uiNickName}">
									<button class="ui basic compact mini button"
										onclick="commentDelete(${comment.bcNo},${boardVO.bNo})">삭제</button>
								</c:if>
								<div class="metadata">
									<div class="text">${comment.bcText}</div>
								</div>
								<br>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>

			<div class="ui reply form">
				<div class="field">
					<textarea id="bcText"
						<c:if test="${loginUserInfoVO.uiNickName==null}">disabled</c:if>></textarea>
				</div>
				<button id="addComment" class="ui blue labeled submit icon button"
					onclick="addComment('${boardVO.bNo}','${loginUserInfoVO.uiNickName}', '${loginUserInfoVO.iconName}')">
					<i class="icon edit"></i> 등록
				</button>
			</div>

		</div>
	</div>
	<form action="/board/update" method="post"
		enctype="multipart/form-data" style="display: none">
		<input name="bNo" value="${boardVO.bNo}" /><input name="bName"
			value="${boardVO.bName}" /><input name="bContent"
			value="${boardVO.bContent}" />
		<button id="goUpdate"></button>
	</form>

</body>

</html>