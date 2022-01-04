<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<script>
function accept(id){
	var param = {uiId : id};
	var au = new AjaxUtil("/friends/add", param);
	au.send(resultCallback);
	function resultCallback(res){
		alert(res.msg);
		if(res.biz){
			$("#"+id+"parent").css("display","none");
			location.href="/myfriends";
		}
	}
}

function reject(id){
	id = id.substr(6);
	var param = {uiId:id};
	var au = new AjaxUtil("/friends/reject", param);
	au.send(rejectCallback);
	function rejectCallback(res){
		alert(res.msg);
		if(res.biz){			
			location.href="/myfriends";
		}
	}
}

function remove(id){
	id = id.substr(6);	
	var param = {fId:id};
	var au = new AjaxUtil("/friends/remove", param);
	au.send(removeCallback);
	function removeCallback(res){
		alert(res.msg);
		if(res.biz){			
			location.href="/myfriends";
		}
	}
	
}




function popUpFunc(){
	$('.window.close.icon.link')
	  .popup();	
}

</script>
<body onload="popUpFunc()">
<div id="content">
<br><br><br><br><br><br>
<div class="ui link cards" style="width:70%; float:left">
<c:forEach items="${fList}" var="fMap">
	
	<div class="card" style="width:250px">
	<div class="content">
	<div class="right floated meta" id="delete${fMap.fId }" onclick="remove(id)">
		<i class="window close icon link" data-content="${fMap.fId }님을 삭제하겠습니까?" data-variation="width"></i>
	</div>
	<br>    
	<div class="header">${fMap.fName}</div>
	<div class="meta">
		<a>${fMap.fId}</a>
	</div>
	<div class="description">
		${fMap.fComment}
	</div>
    </div>
    <div class="extra content">
    </div>
  </div>
</c:forEach>
</div>


<div style="width:20%; float:right">

<c:forEach items="${callList}" var="callMap">
<c:choose>
	<c:when test="${callMap.count < 4}">
	<div class="ui piled segment" id="${callMap.uiId }parent" style="width:300">
		<p>${callMap.uiId }님께서 친구요청을 하셨습니다.</p>	
		<p>수락하시겠습니까?</p>	
		<button class="ui blue button" id="${callMap.uiId }" onclick="accept(id)">수락</button>
		<button class="ui button" id="reject${callMap.uiId }" onclick="reject(id)">거절</button>
	</div>
	</c:when>	
	<c:otherwise>		
	</c:otherwise>
</c:choose>
</c:forEach>	

</div>
</div>
</body>
</html>