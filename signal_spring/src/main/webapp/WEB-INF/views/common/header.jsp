<%@page import="com.iot.test.vo.UserInfoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<%
	String uri = request.getRequestURI();
	UserInfoVO user = (UserInfoVO) session.getAttribute("user");
	if (uri.indexOf("login") == -1 && uri.indexOf("signup") == -1 && user == null) {
		response.sendRedirect("/login");
	}
%>



<style type="text/css">
.hidden.menu {
	display: none;
}

.masthead.segment {
	min-height: 700px;
	padding: 1em 0em;
}

.masthead .logo.item img {
	margin-right: 1em;
}

.masthead .ui.menu .ui.button {
	margin-left: 0.5em;
}

.masthead h1.ui.header {
	margin-top: 3em;
	margin-bottom: 0em;
	font-size: 4em;
	font-weight: normal;
}

.masthead h2 {
	font-size: 1.7em;
	font-weight: normal;
}

.ui.vertical.stripe {
	padding: 8em 0em;
}

.ui.vertical.stripe h3 {
	font-size: 2em;
}

.ui.vertical.stripe .button+h3, .ui.vertical.stripe p+h3 {
	margin-top: 3em;
}

.ui.vertical.stripe .floated.image {
	clear: both;
}

.ui.vertical.stripe p {
	font-size: 1.33em;
}

.ui.vertical.stripe .horizontal.divider {
	margin: 3em 0em;
}

.quote.stripe.segment {
	padding: 0em;
}

.quote.stripe.segment .grid .column {
	padding-top: 5em;
	padding-bottom: 5em;
}

.footer.segment {
	padding: 5em 0em;
}

.secondary.pointing.menu .toc.item {
	display: none;
}

@media only screen and (max-width: 700px) {
	.ui.fixed.menu {
		display: none !important;
	}
	.secondary.pointing.menu .item, .secondary.pointing.menu .menu {
		display: none;
	}
	.secondary.pointing.menu .toc.item {
		display: block;
	}
	.masthead.segment {
		min-height: 350px;
	}
	.masthead h1.ui.header {
		font-size: 2em;
		margin-top: 1.5em;
	}
	.masthead h2 {
		margin-top: 0.5em;
		font-size: 1.5em;
	}
}
</style>
<script>
	$(document).ready(function() {
		
		/* $('.ui.accordion')
		  .accordion();
		*/
		$('.ui.inverted.dropdown.button').dropdown();
 
		// create sidebar and attach to menu open
		$('.ui.sidebar').sidebar('attach events', '.toc.item');

		$('#content').appendTo('#headerContent');
		$('.vertical').appendTo('.pusher');
		
		var page = window.location.pathname;
		for(var menuA of $('.ui.menu a')){
			var text = menuA.text.toLowerCase();
			if(page.indexOf(text)!=-1){
				$("a[href='/"+text+"'").addClass('active');
			}		
		};
		
		for(var dropBt of $("#dropBt div.item")){
			var forWrite = dropBt.innerText;	
			var inText = dropBt.innerText;				
			if(inText.indexOf(" ")!=-1){
				inText = inText.replace(" ","");			
			} 
			inText = inText.toLowerCase();
			if(page.indexOf(inText)!=-1){
				$("#myPageBt").html(forWrite);
			}
		}
	});	
	
function openRandom(){
	$('#goRandom').modal('show');	
}



function setChecked(id){	
	var rRSizes;	
	for(var i=2;i<=6;i++){		
		rRSizes = $("#rRSize"+i);
		rRSizes.css("checked","");				
	}	
	document.getElementById(id).checked = true;	
}

function inRandomChat(){
	var param = {};
	var rRSize;
	for(var i=2;i<=6;i++){		
		rRSize = $("#rRSize"+i);
		if(rRSize[0].checked==true){			
			param = {"rRSize": rRSize.val()};
		}
	}	
	$("#loadingW").modal('show');	
	
	var au = new AjaxUtil("random/check",param);
	au.send(matchingCallback);	
}

function matchingCallback(res){
	var waitTime = res.waitTime;
	if(res.biz){			
		$("#rRName").val(res.rRName);
		$("#uiNickName").val(res.uiNickName);	
		$('#resultMessage').html(res.msg);		
		setTimeout(function(){			
			$("#loadingW").modal('hide');
			$("#goinRChat").submit();			
		}, waitTime);	 
	}
	
	else{
		$('#resultMessage').html(res.msg);	
		setTimeout(function(){			
			$("#loadingW").modal('hide');	
			$('#resultMessage').html("Loading");
		}, 4000);						
	}
	
	
}

function moveMyFriends(){		
	location.href="/myfriends";
}

function moveMyPost(){
	location.href="/board/mypost?page=1&block=1";
}



</script>
<body>

	<!-- Sidebar Menu -->
	<div id="headerMenu" class="ui vertical inverted sidebar menu">
		<a href="/home" class="item">Home</a> <a href="/map" class="item">Map</a>
		<a href="/random" class="item">Random</a> <a
			href="/board?page=1&block=1" class="item">Board</a> <a
			href="${(Log=='login')? '/login':'/user/logout'}" class="item">${(Log=='login')?'Login':'Logout'}</a>
		<a href="/signup" class="item">Signup</a>
	</div>
	<!-- Page Contents -->
	<div class='pusher'>
		<div id="headerContent"
			class="ui inverted vertical masthead center aligned segment"
			style="background-image: url('/img/main4.jpg'); background-position: center; background-repeat: no-repeat; background-size: cover">
			<div class="ui container">
				<div id='headerMenu'
					class="ui large secondary inverted pointing menu"
					style="border-style: none">
					<a class="toc item"> <i class="sidebar icon"></i>
					</a> <a href="/home" class="item">Home</a> <!-- <a href="/map" class="item">Map</a>
					<a class="item" onclick="openRandom()"
						style="display:${(Log=='login')?'none':''};">Random</a>--> <a
						href="/board?page=1&block=1" class="item">Board</a>
					<div class="right item">
						<a href="${(Log=='login')? '/login':'/user/logout'}"
							class="ui inverted button">${(Log=='login')?'Log in':'Log out'}</a>
						<a href="/signup" class="ui inverted button"
							style="display:${(Log=='login')?'':'none'}">Sign up</a>

						<div id="myPage" class="ui inverted dropdown button"
							style="display:${(Log=='login')?'none':''};">
							<div class="text" id="myPageBt">My Page</div>
							<div class="menu" id="dropBt">
								<div class="item" onclick="moveMyFriends()">
									<i class="icon users"></i>My Friends
									<div class="floating ui red label">${acceptSize}</div>
								</div>
								<div class="item" onclick="moveMyPost()">
									<i class="clipboard outline icon"></i>My Post
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="ui basic modal" id="goRandom" style="border-style: solid;">
		<div class="ui icon header">
			<i class="handshake outline icon"></i>
		</div>

		<div class="content" style="width: 600; margin: auto">
			<form action="random/chat" id="goinRChat">
				<div class="ui form">

					<div class="grouped fields">
						<label style="color: white"><h2>&emsp;&emsp;&ensp;How
								many do you want to chat to people</h2></label> <br>
						<div class="field" style="margin-left: 100">
							<div class="ui radio checkbox">
								<input type="radio" id="rRSize2" name="rRSize" value="2"
									onclick="setChecked(id)" checked> <label
									style="color: white">&emsp;With Two People</label>
							</div>
						</div>
						<div class="field" style="margin-left: 100">
							<div class="ui radio checkbox">
								<input type="radio" id="rRSize3" name="rRSize" value="3"
									onclick="setChecked(id)"> <label style="color: white">&emsp;With
									Three People</label>
							</div>
						</div>
						<div class="field" style="margin-left: 100">
							<div class="ui radio checkbox">
								<input type="radio" id="rRSize4" name="rRSize" value="4"
									onclick="setChecked(id)"> <label style="color: white">&emsp;With
									Four People</label>
							</div>
						</div>
						<div class="field" style="margin-left: 100">
							<div class="ui radio checkbox">
								<input type="radio" id="rRSize5" name="rRSize" value="5"
									onclick="setChecked(id)"> <label style="color: white">&emsp;With
									Five People</label>
							</div>
						</div>
						<div class="field" style="margin-left: 100">
							<div class="ui radio checkbox">
								<input type="radio" id="rRSize6" name="rRSize" value="6"
									onclick="setChecked(id)"> <label style="color: white">&emsp;With
									Six People</label>
							</div>
						</div>
					</div>
				</div>

				<input type="hidden" id="rRName" name="rRName"> <input
					type="hidden" id="uiNickName" name="uiNickName">
			</form>
		</div>

		<div class="actions">
			<div class="ui red basic cancel inverted button">
				<i class="remove icon"></i> No
			</div>
			<div class="ui green ok inverted button" onclick="inRandomChat()">
				<i class="checkmark icon"></i> Yes
			</div>
		</div>
	</div>


	<div class="ui basic modal" id="loadingW">

		<div class="content" style="width: 600; margin: auto">
			<label style="color: white; text-align: center;"><h2
					id="resultMessage">Loading</h2></label>
		</div>
		<br>
		<div class="ui large text loader"></div>


	</div>


</body>


</html>