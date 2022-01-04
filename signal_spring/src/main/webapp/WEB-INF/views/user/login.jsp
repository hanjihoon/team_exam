<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Roboto"
	rel="stylesheet" type="text/css">
<script src="https://apis.google.com/js/api:client.js"></script>
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id"
	content="292614374166-d8adkm1fm7nk958f4segl383lritl87o.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js" async defer></script>


<style type="text/css">
body {
	background-size: cover;
}

body>.grid {
	height: 100%;
}

.image {
	position: absolute;
	margin-top: 40px;
	margin-left: 20px;
	margin-bottom: -40px;
}

.column {
	max-width: 450px;
}
</style>

<script>
	/* 	function login() {
	 var uiId = document.getElementById("uiId").value;
	 var uiPwd = document.getElementById("uiPwd").value;
	 var param = {
	 uiId : uiId,
	 uiPwd : uiPwd
	 };		
	 var au = new AjaxUtil("/user/login", param, null, "post");
	 au.send(test);
	 function test(res) {
	 alert(res.msg);
	 if(res.biz){
	 document.location.href="/home";
	 }
	 }
	 } */

	function login() {
		var uiId = document.getElementById("uiId").value;
		var uiPwd = document.getElementById("uiPwd").value;
		var param = {
			uiId : uiId,
			uiPwd : uiPwd
		};
		var au = new AjaxUtil("/user/login", param, null, "post");
		au.send(loginCall);

	}
	function loginCall(res) {
		alert(res.msg);
		if (res.biz) {
			document.location.href = "/home";
		}
	}
	var googleUser = {};
	var startApp = function() {
		gapi
				.load(
						'auth2',
						function() {
							// Retrieve the singleton for the GoogleAuth library and set up the client.
							auth2 = gapi.auth2
									.init({
										client_id : '292614374166-d8adkm1fm7nk958f4segl383lritl87o.apps.googleusercontent.com',
										cookiepolicy : 'single_host_origin',
									// Request scopes in addition to 'profile' and 'email'
									//scope: 'additional_scope'
									});
							attachSignin(document.getElementById('customBtn'));
						});
	}

	var revokeAllScopes = function() {
		auth2.disconnect();
	}

	function checkIdCall(res) {
		if (res.biz) {
			if (confirm("회원가입이 필요합니다. 회원가입 페이지로 이동하시겠습니까?")) {
				$("#googleSignup").submit();
			}
		}
	}

	function attachSignin(element) {
		auth2.attachClickHandler(element, {}, function(googleUser) {
			var profile = googleUser.getBasicProfile();
			$("#googleId").val(profile.getId());
			$("#googleEmail").val(profile.getEmail());
			var param = {
				uiId : profile.getId()
			}
			setTimeout(function() {
				var ajax = new AjaxUtil("/user/check", param);
				ajax.send(checkIdCall)
			}, 100);
			$(".buttonText").on("click", revokeAllScopes());
			var ajax = new AjaxUtil("/user/login", param);
			ajax.send(loginCall);

		}, function(error) {
			alert(JSON.stringify(error, undefined, 2));
		});

	}

	function signOut() {
		var auth2 = gapi.auth2.getAuthInstance();
		auth2.signOut().then(function() {
		});
	}
</script>

<style type="text/css">
#customBtn {
	display: inline-block;
	background: white;
	color: #444;
	width: 190px;
	border-radius: 5px;
	border: thin solid #888;
	box-shadow: 1px 1px 1px grey;
	white-space: nowrap;
}

#customBtn:hover {
	cursor: pointer;
}

span.label {
	font-family: serif;
	font-weight: normal;
}

span.icon {
	background: url('/identity/sign-in/g-normal.png') transparent 5px 50%
		no-repeat;
	display: inline-block;
	vertical-align: middle;
	width: 42px;
	height: 42px;
}

span.buttonText {
	display: inline-block;
	vertical-align: middle;
	padding-left: 42px;
	padding-right: 42px;
	font-size: 14px;
	font-weight: bold;
	/* Use the Roboto font that is loaded in the <head> */
	font-family: 'Roboto', sans-serif;
}
</style>
</head>
<body>

	<div id="content">
		<br> <br> <br>
		<div class="ui middle aligned center aligned grid">
			<div class="column">

				<img class="ui image" src="/img/tippler2.png">

				<div class="ui large form">
					<div class="ui stacked segment">
						<div class="field">
							<div class="ui left icon input">
								<i class="user icon"></i> <input type="text" name="email"
									placeholder="ID" id="uiId">
							</div>
						</div>
						<div class="field">
							<div class="ui left icon input">
								<i class="lock icon"></i> <input type="password" name="password"
									placeholder="Password" id="uiPwd">
							</div>
						</div>
						<div class="ui fluid large magenta button" onclick="login()">
							<h3 class="ui inverted header">Login</h3>
						</div>
					</div>
					<div class="ui error message"></div>
				</div>
				<div id="customBtn" class="ui button purple labeled icon">
					<i class="google icon"></i>Sign in with Google
					<script>
						startApp();
					</script>
				</div>
				<br> <br> <a class="ui button purple labeled icon"
					href="/signup"><i class="paw icon"></i>Sign Up</a>
			</div>
		</div>
	</div>
	<form id="googleSignup" action="/google/signup" style="display: none;">
		<input id="googleId" name="id" /> <input id="googleEmail"
			name="email" />
	</form>
</body>

</html>
