// ActionScript file
	import com.adobe.serialization.json.JSON;
	
	import mx.controls.Alert;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.http.HTTPService;
	
	import utils.Constants;
	
	public function sendLogout():void
	{
		var httpService:HTTPService = new HTTPService();
		httpService.url = Constants.SERVER_URL + "/logout";
		httpService.method = "POST";
		httpService.contentType = "application/json";
		httpService.resultFormat = HTTPService.RESULT_FORMAT_TEXT;
		httpService.addEventListener(ResultEvent.RESULT, setLogoutState);
		httpService.send("{}");
	}
	
	public function setLogoutState(evt:ResultEvent):void
	{
		var data:Object = JSON.decode(evt.result.toString());
		if(data.success == true)
		{
			parentApplication.currentState = "Login";
		}
		else if(data.success == false)
		{
			Alert.show(evt.result.toString());
		}		
	}
	
	
	
	