<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="styleSheet" href="/css/groupcall/randomstyle.css" type="text/css" media="screen">
<link rel="stylesheet" type="text/css"
	href="/css/components/container.css">
<link rel="stylesheet" type="text/css" href="/css/components/grid.css">
<link rel="stylesheet" type="text/css" href="/css/components/header.css">
<link rel="stylesheet" type="text/css" href="/css/components/image.css">
<link rel="stylesheet" type="text/css" href="/css/components/menu.css">
<link rel="stylesheet" type="text/css"
	href="/css/components/divider.css">
<link rel="stylesheet" type="text/css"
	href="/css/components/dropdown.css">
<link rel="stylesheet" type="text/css"
	href="/css/components/segment.css">
<link rel="stylesheet" type="text/css" href="/css/components/form.css">
<link rel="stylesheet" type="text/css" href="/css/components/list.css">
<link rel="stylesheet" type="text/css" href="/css/components/icon.css">
<link rel="stylesheet" type="text/css"
	href="/css/components/sidebar.css">
<link rel="stylesheet" type="text/css"
	href="/css/components/transition.css">
<link rel="stylesheet" type="text/css" href="/css/components/input.css">
<link rel="stylesheet" type="text/css" href="/css/components/button.css">
<link rel="stylesheet" type="text/css"
	href="/css/components/message.css">
<link rel="stylesheet" type="text/css" href="/css/components/modal.css">
<link rel="stylesheet" type="text/css" href="/css/components/table.css">

<link rel="stylesheet" type="text/css" href="/css/components/accordion.css">
<link rel="stylesheet" type="text/css" href="/css/components/shape.css">
<link rel="stylesheet" type="text/css" href="/css/components/checkbox.css">
<link rel="stylesheet" type="text/css" href="/css/components/loader.css">

<script src="/js/jquery-3.3.1.js"></script>
<script src="/js/components/visibility.js"></script>
<script src="/js/components/sidebar.js"></script>
<script src="/js/components/transition.js"></script>
<script src="/js/components/form.js"></script>
<script src="/js/components/dropdown.js"></script>
<script src="/js/components/dimmer.js"></script>
<script src="/js/components/modal.js"></script>
<script src="/js/components/accordion.js"></script>
<script src="/js/components/shape.js"></script>
<script src="/js/components/checkbox.js"></script>
<script src="/js/adapter.js"></script>
<script src="/js/groupcall/randomconferenceroom.js"></script>
<script src="/js/groupcall/participant.js"></script>
<script src="/js/kurento-utils.js"></script>
<script src="/js/common.js"></script>
<title>Insert title here</title>
<style>

/* .chatSpace {
  position: absolute;
  top: 55%;
  left: 65%; 
  width: 430px;
}

.chatIcon {
  position: absolute;
  top: 55%;
  left: 65%; 
  
} */
.spaceSpace{
	position:absolute;
	top: 60%;
	left: 65%;

}
</style>
<script>

function openchat(){	
	$("#chatIcon").css("display","none");	
	$("#chatSpace").css("display","");	
	$('#chatSpace').animate({width: 430}, "slow");
	
}
function clopsechat(){
	$('#chatSpace').animate({width: 0}, "slow");
	
	setTimeout(function(){ 		
		$('#chatSpace').css("display","none");	
	}, 500);	
	setTimeout(function(){ 
		$("#chatIcon").css("display","");	
	}, 1000);
}


</script>


</head>
<body onload="register()">
<div id="container">

		<div id="wrapper">			
			<div id="room" style="display: none;">
				<h2 id="room-header"></h2>
				<div id="participants"></div>
				<input type="button" id="button-leave" onmouseup="leaveRoom();"	value="Leave room">
			</div>
		</div>		
		
		
</div>

<div id="container" class="spaceSpace">

		<div id="chatSpace" class="chatSpace">
				<fieldset style="overflow-y: auto;" id="chatingBox">				
			        <textarea id="messageWindow" rows="10" cols="50" readonly="true"></textarea>
			        <br/>			        
			        <input id="inputMessage" type="text"/>
			        <input type="submit" value="send" onclick="send()" />
			        <img src="/img/koreamap.png" class="image" height="40" width="60" style="float:right"onclick="clopsechat()">		
			    </fieldset>					
		</div>
		
		<div id="chatIcon" class="chatIcon"  style="display:none">		
			<img src="/img/koreamap.png" class="image" height="45" width="80" onclick="openchat()">		
		</div>

</div>

		
		<input type="hidden" id="rName" value=${rName }>
		<input type="hidden" id="uiNickName" value=${uiNickName }>		
		<input type="hidden" id="uiId" value=${uiId }>
		
		<script type="text/javascript">		
        var textarea = document.getElementById("messageWindow");      
        var inputMessage = document.getElementById('inputMessage');
        var uiId = document.getElementById('uiId');
        var uiNickName = document.getElementById('uiNickName');
        var roomName = document.getElementById('rName');
        var uiNickNameList = [];
        
	    ws.onerror = function(event) {
	      onError(event)
	    };
	    
	    ws.onopen = function(event) {
	      onOpen(event)
	    };
	    
	    function onOpen(event) {
	    	var param = {rRName: roomName.value};
	        var au = new AjaxUtil("/random/get", param);
	        au.send(joinCallback);
	       
	    }	    
	    
	    function joinCallback(res){
        	for(var privateId of res){        		
        		uiNickNameList.push(privateId);	        		
        	}        	
        	var msg = {"id": "randomSendMessage", "uiId": uiId.value, "name": roomName.value, "uiNickName": uiNickName.value, "msg": uiNickName.value+"님께서 입장하셨습니다.", "nickNameList":uiNickNameList};
        	ws.send(JSON.stringify(msg));
        	
        }
	    
	    function onError(event) {
	      alert(event.data);
	    }
	    
	    function send() {
	    	var msg = {"id": "randomSendMessage", "uiId": uiId.value, "name": roomName.value, "uiNickName": uiNickName.value, "msg": inputMessage.value, "nickNameList":uiNickNameList};
	        ws.send(JSON.stringify(msg));
	        inputMessage.value = "";
	    }
		</script>
					
	</div>
	
	
</body>
</html>