package events
{
	import flash.events.Event;
	
	import mx.controls.Alert;
	import mx.rpc.events.ResultEvent;

	public class HttpRequestEvent extends Event
	{
		public var eventData:String;
		public static const LOGIN_EVENT:String = 'Login Event';
		public static const REGISTER_EVENT:String = 'Register Event';
		public static const LOGOUT_EVENT:String = 'Logout Event';
		public static const ADD_EXTERNAL_RECIPE_EVENT:String = 'Add Recipe Event';
		public static const ADD_OWN_RECIPE_EVENT:String = 'Add Own Recipe Event';
		public static const GET_RECIPES_EVENT:String = 'Get Recipes Event';
		public static const EXPORT_RECIPE_EVENT:String = 'Export Recipes Event';
		public function HttpRequestEvent(type:String, data:String = null)
		{
			super(type);
			if(data != null) eventData = data;
		}
	}
}