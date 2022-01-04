
var AjaxUtil = function (url, params, type, dataType){
	this.param = null;
	if(params!=null){
		this.param = JSON.stringify(params);
	}
	this.url = url;
	this.type = type?type:"POST";
	this.dataType = dataType?dataType:"json";
		
	this.send = function(callback){
		if(callback){
			this.callbackSuccess = callback;
		}
		$.ajax({ 
	        type     : this.type
	    ,   url      : this.url	    
	    ,   beforeSend: function(xhr) {
	        xhr.setRequestHeader("Content-Type", "application/json");
	    }
	    ,  data : this.param
	    ,  success : this.callbackSuccess
	    ,   error : function(xhr, status, e) {
		    	alert("에러 : "+xhr.responseText);
		},
		done : function(e) {
		}
		});
	}
}



