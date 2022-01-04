<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
body {
	background-color: #DADADA;
}

body>.grid {
	height: 100%;
}

.image {
	margin-top: -100px;
}

.column {
	max-width: 450px;
}
</style>
</head>
<script>


var idCheck = 0;
var pwdCheck = 0;
var nickCheck = 0;
var emailCheck = 0;
var iconCheck = 0;


var checkIdOk = 0;


var reS = /^\w+$/;
var reN = /[0-9]/;
var reL = /[a-z]/;
var reU = /[A-Z]/;

var param;

function Validation(){
	var uiId = $("#uiId").val().trim();
	var uiPwd = $("#uiPwd").val().trim();
	var uiNickName = $("#uiNickName").val().trim();
	var uiEmail = $("#uiEmail").val().trim();
	
	param = {uiId:uiId,uiPwd:uiPwd,uiNickName:uiNickName,uiEmail:uiEmail,iconName:"a"};
	
	if(uiId.length < 5){		
		idCheck++;
		alert("5글자 이상의 ID를 다시 입력해주세요.");
		return true;
	}else if(!reS.test(uiId)){		
		idCheck++;
		alert("특수문자는 ID로 사용하실수 없습니다.");
		return true;
	}else if(uiPwd.length < 7){		
		pwdCheck++;
		alert("7글자 이상의 PASSWORD를  입력해주세요.");
		return true;
	}else if(reS.test(uiPwd) || !reN.test(uiPwd) || !reL.test(uiPwd) || !reU.test(uiPwd)){	
		pwdCheck++;
		alert("대문자, 소문자, 숫자, 특수문자를 조합해서 비밀번호를 설정해야 합니다.");
		return true;
	}else if(uiNickName.length < 4){	
		nickCheck++;
		alert("4글자 이상의 닉네임을 입력해주세요.");
		return true;
	}else if(uiEmail.length < 7){	
		emailCheck++;
		alert("Email을 다시 입력하세요.");
		return true;
	}
	
	return false;	
}

function signup(){
	var check = Validation();	
	if(check){		
		if(idCheck==1){			
			idCheck--;	
			$("#uiId").focus();
		}
		else if(pwdCheck==1){			
			pwdCheck--;	
			$("#uiPwd").focus();
		}
		else if(nickCheck==1){			
			nickCheck--;	
			$("#uiNickName").focus();
		}
		
		return;		
	}	
	
	if(checkIdOk!=0){
		var au = new AjaxUtil("/user/signup", param);
		au.send(callback);
		
		function callback(res){
			alert(res.msg);
			if(res.biz){
				location.href="/login";
			}
		}		
	}else{		
		alert("아이디 중복체크를 해주세요.")
		return;
	}	
	
}


function idValidation(){
	var uiId = $("#uiId").val().trim();
	if(uiId==""){		
		alert("아이디를 입력해주세요.");
		return;
	}	
	var idParam ={uiId:uiId};
	
	var au = new AjaxUtil("user/check", idParam);
	au.send(checkCallback);
	
	function  checkCallback(res){
		alert(res.msg);
		if(res.biz){
			$("#checkyUpp").css("color","green");
			checkIdOk++;
		}else{
			$("#checkyUpp").css("color","red");
			setTimeout(function(){ 
				$("#checkyUpp").css("color","");
				}, 3000);
		}
	}	
}

function chCursor(){
	$("#checkyUp").css("cursor","pointer");	
}

function reChCursor(){
	$("#checkyUp").css("cursor","");
}

function openIconBox(){
	$('#choiceIconBox').modal('show');	
}

function getIcon(){
	var params = {};
	var au = new AjaxUtil("icon/get",params);
	au.send(iconCallback);
	if($("#uiId").prop("disabled")){
		checkIdOk++;
	}
}


//onmouseover transition
function transitionPulse(id){
	$('#' + id).transition('jiggle');
	
	
}

function checkVal(id){
}


</script>
<body>
	<div id="content">
		<br> <br> <br> <br> <br>
		<div class="ui middle aligned center aligned grid">
			<div class="column">
				<img class="ui logo image" id="tippleLogo" src="/img/tippler2.png">
				<form class="ui large form">

					<div class="ui stacked segment">

						<div class="field">
							<div class="ui left icon input">
								<i class="user icon"></i> <input type="text" id="uiId"
									name="uiId" placeholder="ID"
									<c:if test="${id!=null}">value="${id}" disabled</c:if>>

								<div class="ui basic button right icon input"
									style="width: 50px" id="checkyUp"
									<c:if test="${id==null}"> onclick="idValidation()"</c:if>
									onmouseover="chCursor()" onmouseout="reChCursor">
									<i class="large check icon" id="checkyUpp"></i>
								</div>

							</div>
						</div>

						<div class="field">
							<div class="ui left icon input">
								<i class="lock icon"></i> <input type="password" id="uiPwd"
									name="uiPwd" placeholder="Password"
									<c:if test="${email!=null}">value="P@ssw0rd" disabled</c:if>>

							</div>
						</div>

						<div class="field">
							<div class="ui left icon input">
								<i class="user circle icon"></i> <input type="text"
									id="uiNickName" name="uiNickName" placeholder="Nickname">

							</div>
						</div>

						<div class="field">
							<div class="ui left icon input">
								<i class="at icon"></i> <input type="text" id="uiEmail"
									name="uiEmail" placeholder="Email"
									<c:if test="${email!=null}">value="${email}" disabled</c:if>>
							</div>
						</div>

						<div class="ui fluid large magenta submit button"
							onclick="signup()">Sign-Up</div>
					</div>

				</form>
			</div>
		</div>

	</div>

	<div class="ui modal" id="choiceIconBox">
		<div class="header">Icon Choice</div>

		<div class="image content">

			<div class="ui image" style="width: 250px"></div>

			<div class="description">
				<div class="ui header" id="iconBox"></div>
			</div>

		</div>

		<div class="actions">

			<div class="ui black deny button" id="no" onclick="checkVal(id)">
				Nope</div>
		</div>
	</div>

</body>

</html>
