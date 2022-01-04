<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title>Korea Map</title>
</head>
<script src="/js/map/d3.v3.min.js"></script>
<script src="/js/map/topojson.v0.min.js"></script>

<style>
svg {
	/* background-color: lightskyblue; */
}

.municipality {
	fill: #ffffff;
	stroke: #800080;
}

.municipality:hover {
	fill: #800080;
}

.regeonNameInP{
	position: absolute;
	margin-top: 34%;
	margin-left: 25%;
	margin-bottom: -40px;
}

</style>

<body onresize="resizeW()">
<div id="content">

<div class="regeonNameInP" id="regeonNameInP"><h3></h3></div>
<div class="mapTest" id="mapTest" style="float: left;margin-top:60 "></div>

<div id="kMapIcon" style="display: none;float: left;margin-top:60 ">
<div style="vertical-align: middle;">
	<img src="/img/koreamap.png" class="image" height="45" width="60" onclick="openMap()">
</div>

</div>

<script>
var colorConfirm = [];
var bw = document.body.clientWidth;
var bh = document.body.clientHeight;
var idx = 0;
var localizingRegeon = {"Jeju-do":"제주도","Gyeongsangnam-do":"경상남도","Gyeongsangbuk-do":"경상북도","Jeollanam-do":"전라남도","Jeollabuk-do":"전라북도",
		"Chungcheongnam-do":"충청남도","Chungcheongbuk-do":"충청북도","Gangwon-do":"강원도","Gyeonggi-do":"경기도","Sejongsi":"세종","Ulsan":"울산","Daejeon":"대전",
		"Gwangju":"광주","Incheon":"인천","Daegu":"대구","Busan":"부산","Seoul":"서울"};

function getRegName(id){
	$("#regeonNameInP h3").html(localizingRegeon[id]);	
}

function removeRegName(id){
	$("#regeonNameInP h3").html("");
}


function getList(str){	
	idx=1;	
	var bw = document.body.clientWidth;
	var bh = document.body.clientHeight;
	
	var localRegeonName = localizingRegeon[str];	
	
	for(var id of colorConfirm){
		$('#'+id).removeClass("active");		
	}	
	colorConfirm = [];
	
	var params = {regeonName:str};
	var au = new AjaxUtil("room/all",params);	
	au.send(searchCallback)	
	
	$("svg").animate({width: 0}, 1000);		
	$('#regeonName').val(str);
	$('#regeon').html(localRegeonName);
	$('#categoryWindow').css("width",bw*(1/2));
	$('#categoryWindow').css("display","");
	$('#categoryWindow').animate({width: bw*(4/5)}, 1000);
	$('#categoryWindow').css("float","none");
	$('#categoryWindow').css("margin","auto");	
	
	/* setTimeout(function(){ 
		$('#regeonNameInP').css("display","none");
	}, 500);	 */
	
	setTimeout(function(){ 
		$('#kMapIcon').css("display","");
	}, 1000);	
}

function openMap(){
	idx=2;
	
	var bw = document.body.clientWidth;
	var bh = document.body.clientHeight;
	
	//$('#kMapIcon').css("visibility","hidden");
	$('#categoryWindow').animate({width: bw-(bw/2.7)}, "slow");
	$('#categoryWindow').css("float","right");
	$('#categoryWindow').css("margin","0");	
	$("svg").animate({width: (bw/2.8)}, "slow");
	$('#kMapIcon').css("display","none");	
	
}

function openWindow(){
	$('#categoryNo').dropdown();
	$('#rSize').dropdown();
	$('#makeRoomW').modal('setting', 'closable', false).modal('show');	
}

function checkRoomInfo(){
	var rName = $("#rName").val();
	var categoryNo = $("#categoryNo").val();
	var rSize = $("#rSize").val();
	if(rName=="" || categoryNo=="" || rSize==""){
		
		return true;
	}
	return false;
}



function createRoom(){	
	var rName = $('#rName').val();
	rNameVal = {rName:rName};
	if(checkRoomInfo()){
		alert("방 정보를 모두 입력해주세요.");		
		
	}else{
		var au = new AjaxUtil("room/check",rNameVal);	
		au.send(checkCallback);			
	}
}

function checkCallback(res){		
	if(res.msg!=null){
		alert(res.msg);
	}else{
		$('#createRoomForm').submit();	
	}
}	


var w = 600, h = 580; 
var proj = d3.geo.mercator()
	.center([136, 34.0])	
	.scale(2300);
	

var path = d3.geo.path().projection(proj);
var ids = [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16];

var svg = d3.select("#mapTest").append("svg")
	.attr("width", bw/2.8)
	.attr("height", h)
	.attr("preserveAspectRatio", "xMinYMin meet")
	.attr("viewBox", "0 0 300 300");
	
d3.json("/js/map/provinces-topo-simple.json", function(error, kor) {
	var municipalities = topojson.object(kor, kor.objects['provinces-geo']);
	svg.selectAll('path').data(municipalities.geometries).enter().append('path')
	.attr('d', path)
	.attr("id", function(d) { return d.properties.name_eng; })
	.attr('class', 'municipality')
	.attr('onclick', 'getList(id)')	
	.attr('onmouseover', 'getRegName(id)')
	.attr('onmouseout', 'removeRegName(id)')
	.attr('style','cursor:pointer;');
});

function resizeW(){
	var bw = document.body.clientWidth;
	var bh = document.body.clientHeight;
	
	if(idx==0){
		$('svg').width(bw/2.8);
		$('svg').height(bw/2.8);
	}
	
	if(idx==1){
		$('#categoryWindow').css("width",bw*(4/5));
	}
	else if(idx==2){
		$('#categoryWindow').css("width", bw-(bw/2.7));
		$('svg').css("width", (bw/2.8));
	}
}



function changeBtns(){
	for(var key in colorConfirm){
		var id = colorConfirm[key];
		$('#'+id).addClass("active");
	}
}
function activeJY(id){	
	var idx = colorConfirm.indexOf(id);
	if(idx==-1){
		colorConfirm[colorConfirm.length] = id;	
				
	}else{
		colorConfirm.splice(idx);
		$('#'+id).removeClass("active");
		$('#'+id).blur();
	}	
	
	changeBtns();	
	searchRoom();
}

function searchRoom(){	
	var regeonName = $('#regeonName').val();
	var params = {regeonName:regeonName, categoryList: colorConfirm};
	
	var au = new AjaxUtil("room/search",params);	
	
	au.send(searchCallback);
}

function searchCallback(res){	
	var listStr = "";
	for(var roomInfo of res){
		listStr += "<div class='item' style='height:45' id="+ roomInfo.rName +" ondblclick='joinRoom(id)'>";
		listStr += "<div class='right floated content' id="+ roomInfo.rName +" onclick='joinRoom(id)'>" 
		listStr += "<div class='ui animated button' tabindex='0'>"
		listStr += "<div class='visible content'>"+ roomInfo.currentAttendee + "/" + roomInfo.rSize + "</div>"
		listStr += "<div class='hidden content'><i class='right arrow icon'></i></div></div></div>"
		listStr += "<i class='large "+ roomInfo.iconName + " middle aligned icon'></i>";
		listStr += "<div class='content'>";
		listStr += "<a class='header' id="+ roomInfo.rName +">"+ roomInfo.rName +"</a>";
		listStr += "<div class='description'>"+ roomInfo.rTag +"</div>"
		listStr += "</div></div>"			
	}			
	$("#roomSearchList").html(listStr);	
}

function joinRoom(id){	
	$("#giveRName").val(id);
	
	var params = {rName: id};	
	var au = new AjaxUtil("uir/check",params);	
	au.send(sizeCheckCallback);
	
	function sizeCheckCallback(res){		
		if(res.currentAttendee < res.rSize){
			$("#joinRoomForm").submit();			
		}else{			
			alert("방의 인원이 다 찼습니다. ")
		}		
	}
	
	
	
	
}


</script>

<br><br><br>
<div id="categoryWindow" style="float: right; display : none ">
	<div> 
		<div class="ui segments">
			<div class="ui segment">
				<p id="regeon"></p>
			</div>
		
					<div class="ui segment">
						<c:forEach items="${clList}" var="colorList">	
							<c:choose>
								<c:when test="${colorList.colorNo eq '7'}">
									<button class="${colorList.colorClass}" id="${colorList.colorId}" onclick="activeJY(id)">${colorList.categoryName}</button>
									</div>
									<div class="ui segment">
								</c:when>
								<c:when test="${colorList.colorNo eq '12'}">									
									<button class="${colorList.colorClass}" id="${colorList.colorId}" onclick="activeJY(id)" style="color:${colorList.colorCode}">${colorList.categoryName}</button>
								</c:when>
								<c:when test="${colorList.colorNo eq '13'}">									
									<button class="${colorList.colorClass}" id="${colorList.colorId}" onclick="activeJY(id)" style="border-color:${colorList.colorCode}; color:#767676">${colorList.categoryName}</button>
									</div>
								</c:when>
								<c:otherwise>
									<button class="${colorList.colorClass}" id="${colorList.colorId}" onclick="activeJY(id)">${colorList.categoryName}</button>								
								</c:otherwise>
							</c:choose>								
						</c:forEach>
							
			<div class="ui segment">
				<div class="ui segment">
				
					<div class="ui relaxed selection divided list" id="roomSearchList" style="height:200px; overflow-y: scroll;">
					
					</div>
					
				</div>
				<button class="ui button" style="float: right; background-color: #2c5bba;color:white" onclick="openWindow()" >Make Room</button>
				<br><br><br>	
			</div>
		</div>
	</div>
</div>


<div class="ui basic modal"  style="border-style: solid;" id="makeRoomW">
	<div class="ui icon header">
	<i class="archive icon"></i>    
</div>  
	<div class="content" style="width:400; margin:auto">
		<form action="video" id="createRoomForm">
	    	방&emsp;이름:&emsp;
	    	<div class="ui input">
	  			<input type="text" placeholder="RoomName" id="rName" name="rName" required>
			</div>
			<br><br>
			
	    	카테고리:&emsp; 
	    	<select name="categoryNo" class="ui dropdown" name="categoryNo" id="categoryNo" required>
			  <option value="">Category</option>			  
			  <c:forEach items="${ctList}" var="cateList">
			  	<option value=${cateList.categoryNo}>${cateList.categoryName}</option>
			  </c:forEach>	  	  
			</select>			
			<br><br>
			
			방사이즈:&emsp;
	    	<select name="rSize" class="ui dropdown" id="rSize" name="rSize" required>
			  <option value="">Size</option>
			  <option value=2>2</option>
			  <option value=3>3</option>
			  <option value=4>4</option>
			  <option value=5>5</option>
			  <option value=6>6</option>
			</select>
			<br><br>
			
			태&emsp;그&emsp;:&emsp;
	    	<div class="ui input">
	  			<input type="text" placeholder="Tag" id="rTag" name="rTag">
			</div>
			
			<input type="hidden" name="regeonName" id="regeonName">
			
			
		</form>
		
	</div>
	
	<div class="actions">
		<div class="ui red basic cancel inverted button">
			<i class="remove icon"></i>
			No
		</div>		
		<div class="ui green ok inverted button" onclick="createRoom()">
			<i class="checkmark icon"></i>
			Yes
		</div>
	</div>
</div>

<form action="join" id="joinRoomForm">
	<input type="hidden" id="giveRName" name="rName">	
</form>

</body>
</html>
