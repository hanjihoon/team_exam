/**
reference - 
https://developers.google.com/accounts/docs/OAuth2WebServer
https://code.google.com/apis/console/
https://developers.google.com/+/api/latest/
**/
 
////handle all requests here
function doGet(e) {
  var HTMLToOutput;
  if(e.parameters.code){//if we get "code" as a parameter in, then this is a callback. we can make this more explicit
    getAndStoreAccessToken(e.parameters.code);
    HTMLToOutput = '<html><h1>Finished with oAuth</h1></html>';
  }
  else if(isTokenValid()){//if we already have a valid token, go off and start working with data
    HTMLToOutput = '<html><h1>Already have token</h1></html>';
  }
  else {//we are starting from scratch or resetting
    return HtmlService.createHtmlOutput("<html><h1>Lets start with oAuth</h1><a href='"+getURLForAuthorization()+"'>click here to start</a></html>");
  }
  
  HTMLToOutput += getData();
  return HtmlService.createHtmlOutput(HTMLToOutput);
}
 
//do meaningful google access here
function getData(){
  var getDataURL = 'https://app.goclio.com/api/v2/users/who_am_i';
  var dataResponse = UrlFetchApp.fetch(getDataURL,getUrlFetchOptions()).getContentText();  
  return dataResponse;
}
 
////oAuth related code
 
//hardcoded here for easily tweaking this. should move this to ScriptProperties or better parameterize them
var AUTHORIZE_URL = 'https://app.goclio.com/oauth/authorize'; //step 1. we can actually start directly here if that is necessary
var TOKEN_URL = 'https://app.goclio.com/oauth/token'; //step 2. after we get the callback, go get token
 
var CLIENT_ID = 'YOUR_CLIO_APP_ID_HERE';
var CLIENT_SECRET='YOUR_CLIO_APP_SECRET_HERE';
var REDIRECT_URL= ScriptApp.getService().getUrl();
 
//this is the user propety where we'll store the token, make sure this is unique across all user properties across all scripts
var tokenPropertyName = 'CLIO_OAUTH_TOKEN'; 
var baseURLPropertyName = 'CLIO_INSTANCE_URL'; 
 
 
//this is the URL where they'll authorize with salesforce.com
//may need to add a "scope" param here. like &scope=full for salesforce
//example scope for google - https://www.googleapis.com/plus/v1/activities
function getURLForAuthorization(){
  return AUTHORIZE_URL + '?response_type=code&client_id='+CLIENT_ID+'&redirect_uri='+REDIRECT_URL;  
}
 
//Google requires POST, salesforce and slc worked with GET
function getAndStoreAccessToken(code){
  var parameters = {
     method : 'post',
     payload : 'client_id='+CLIENT_ID+'&client_secret='+CLIENT_SECRET+'&grant_type=authorization_code&redirect_uri='+REDIRECT_URL+'&code=' + code
   };
  
  var response = UrlFetchApp.fetch(TOKEN_URL,parameters).getContentText();   
  var tokenResponse = JSON.parse(response);
  
  //store the token for later retrival
  UserProperties.setProperty(tokenPropertyName, tokenResponse.access_token);
}
 
//this may need to get tweaked per the API you are working with. 
//for instance, SLC had content type of application/vnd.slc+json. SLC also allows lower case 'bearer'
function getUrlFetchOptions() {
  var token = UserProperties.getProperty(tokenPropertyName);
  return {
            "contentType" : "application/json",
            "headers" : {
                         "Authorization" : "Bearer " + token,
                         "Accept" : "application/json"
                        }
         };
}
 
function isTokenValid() {
  var token = UserProperties.getProperty(tokenPropertyName);
  if(!token){ //if its empty or undefined
    return false;
  }
  return true; //naive check
  
  //if your API has a more fancy token checking mechanism, use it. for now we just check to see if there is a token. 
  /*
  var responseString;
  try{
     responseString = UrlFetchApp.fetch(BASE_URI+'/api/rest/system/session/check',getUrlFetchOptions(token)).getContentText();
  }catch(e){ //presumably an HTTP 401 will go here
    return false;
  }
  if(responseString){
    var responseObject = JSON.parse(responseString);
    return responseObject.authenticated;
  }