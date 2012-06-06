// ActionScript file
	import com.adobe.serialization.json.JSON;
	
	import events.HttpRequestEvent;
	
	import mx.controls.Alert;
	import mx.managers.CursorManager;
	
	import utils.HttpRequests;
	
	public function sendLogout():void
	{
		CursorManager.setBusyCursor();
		var httpRequest:HttpRequests = new HttpRequests();
		httpRequest.logoutRequest();
		httpRequest.addEventListener(HttpRequestEvent.LOGIN_EVENT, setLogoutState);
	}
	
	public function setLogoutState(evt:HttpRequestEvent):void
	{
		CursorManager.removeBusyCursor();
		var data:Object = JSON.decode(evt.eventData);
		if(data.success == true)
		{
			parentApplication.currentState = "Login";
		}
		else if(data.success == false)
		{
			Alert.show(evt.eventData);
		}		
	}
	
	
	
	