/*
 * (C) Copyright 2014 Kurento (http://kurento.org/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

history.pushState(null, null, location.href);
    window.onpopstate = function () {
        history.go(1);
};

var ws = new WebSocket('wss://' + location.host + '/groupcall');

var participants = {};
var name;
var leaveIdx = 0;

window.onbeforeunload = function() {
	if(leaveIdx == 0 ){		
		leaveRoom();		
	}	
	ws.close();
};

ws.onmessage = function(message) {	
	
	var parsedMessage = JSON.parse(message.data);
	console.info('Received message: ' + message.data);
		
	switch (parsedMessage.id) {	
		case 'existingParticipants':
			onExistingParticipants(parsedMessage);
			break;
		case 'newParticipantArrived':
			onNewParticipant(parsedMessage);
			break;
		case 'participantLeft':
			onParticipantLeft(parsedMessage);
			break;
		case 'receiveVideoAnswer':
			receiveVideoResponse(parsedMessage);
			break;
		case 'iceCandidate':
			participants[parsedMessage.name].rtcPeer.addIceCandidate(
					parsedMessage.candidate, function(error) {
						if (error) {
							console.error("Error adding candidate: " + error);
							return;
						}
					});
			break;
		default:
			console.error('Unrecognized message', parsedMessage);
	}
	
	if(parsedMessage.msg){
		var textarea = document.getElementById("messageWindow");
		textarea.value += parsedMessage.uiNickName + " : " + parsedMessage.msg + "\n";	
		$("#chatingBox").scrollTop($("#chatingBox")[0].scrollHeight);
		$("#messageWindow").scrollTop($("#messageWindow")[0].scrollHeight);		
	}	
}

function register() {
	setTimeout(function() {
		$.ajax({
			type : "POST",
			url : "/user/uiId",
			success : function please(res) {
				name = res;
				var room = document.getElementById("rName").value;
				document.getElementById('room-header').innerText = 'RANDOM ROOM';
									
				document.getElementById('room').style.display = 'block';
				var message = {
					id : 'joinRoom',
					name : name,
					room : room
				}
				sendMessage(message);
			}
			,
			error : function(xhr, status, e) {
				alert("에러 : " + xhr.responseText);
			},
			done : function(e) {
			}
		});

	}, 500)
}

function onNewParticipant(request) {
	receiveVideo(request.name);
}

function receiveVideoResponse(result) {
	participants[result.name].rtcPeer.processAnswer(result.sdpAnswer, function(
			error) {
		if (error)
			return console.error(error);
	});
}

function callResponse(message) {
	if (message.response != 'accepted') {
		console.info('Call not accepted by peer. Closing call');
		stop();
	} else {
		webRtcPeer.processAnswer(message.sdpAnswer, function(error) {
			if (error)
				return console.error(error);
		});
	}
}

function onExistingParticipants(msg) {
	var constraints = {
		audio : true,
		video : {
			mandatory : {
				maxWidth : 320,
				maxFrameRate : 15,
				minFrameRate : 15
			}
		}
	};
	console.log(name + " registered in room " + room);
	var participant = new Participant(name);
	participants[name] = participant;
	var video = participant.getVideoElement();

	var options = {
		localVideo : video,
		mediaConstraints : constraints,
		onicecandidate : participant.onIceCandidate.bind(participant)
	}
	participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerSendonly(
			options, function(error) {
				if (error) {
					return console.error(error);
				}
				this.generateOffer(participant.offerToReceiveVideo
						.bind(participant));
			});

	msg.data.forEach(receiveVideo);
}

function leaveRoom() {
	leaveIdx++;
	var uiNickName = document.getElementById('uiNickName').value;	
	var uiId = $("#uiId").val();
	var leaveParam = {
		uiNickName : uiNickName
	};
	leaveParam = JSON.stringify(leaveParam);
	
	$.ajax({
		type : "POST",
		url : "/random/remove",
		data : leaveParam,
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success : function(res) {
			
			sendMessage({
				id : 'leaveRoom',
				uiId : uiId					
			});
			
			for ( var key in participants) {
				participants[key].dispose();
			}
			document.getElementById('room').style.display = 'none';
			ws.close();			
			location.href = "/map";
		}
		,
		error : function(xhr, status, e) {
			alert("에러 : " + xhr.responseText);
		}
	});	
}

function receiveVideo(sender) {
	var participant = new Participant(sender);
	participants[sender] = participant;
	var video = participant.getVideoElement();

	var options = {
		remoteVideo : video,
		onicecandidate : participant.onIceCandidate.bind(participant)
	}

	participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerRecvonly(
			options, function(error) {
				if (error) {
					return console.error(error);
				}
				this.generateOffer(participant.offerToReceiveVideo
						.bind(participant));
			});
	;
}

function onParticipantLeft(request) {
	console.log('Participant ' + request.name + ' left');
	var participant = participants[request.name];
	participant.dispose();
	delete participants[request.name];
}

function sendMessage(message) {
	var jsonMessage = JSON.stringify(message);
	console.log('Senging message: ' + jsonMessage);
	ws.send(jsonMessage);
}
