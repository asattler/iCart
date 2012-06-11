package utils
{
	
	import com.adobe.serialization.json.JSON;
	
	import events.HttpRequestEvent;
	
	import flash.events.EventDispatcher;
	
	import mx.rpc.events.ResultEvent;
	import mx.rpc.http.HTTPService;
	
	import recipe.Recipe;
	import recipe.RecipeUrl;
	
	import user.User;

	public class HttpRequests extends EventDispatcher
	{
		
		
		private var httpService:HTTPService;

		
		public function HttpRequests():void
		{
			initService();
		}
		
		private function initService():void
		{
			httpService = new HTTPService();
			httpService.method = "POST";
			httpService.contentType = "application/json";
			httpService.resultFormat = HTTPService.RESULT_FORMAT_TEXT;
		}
		
		public function loginRequest(user:User):void
		{
			httpService.url = Constants.SERVER_URL + "/login";
			httpService.addEventListener(ResultEvent.RESULT, loginComplete);
			httpService.send(JSON.encode(user));
		}
		private function loginComplete(event:ResultEvent):void
		{
			this.dispatchEvent(new HttpRequestEvent(HttpRequestEvent.LOGIN_EVENT, event.result.toString()));
		}
		
		public function registerRequest(user:User):void
		{
			httpService.url = Constants.SERVER_URL + "/register";
			httpService.addEventListener(ResultEvent.RESULT, registerComplete);
			httpService.send(JSON.encode(user));
		}
		private function registerComplete(event:ResultEvent):void
		{
			this.dispatchEvent(new HttpRequestEvent(HttpRequestEvent.REGISTER_EVENT, event.result.toString()));
		}
		
		public function logoutRequest():void
		{
			httpService.url = Constants.SERVER_URL + "/logout";
			httpService.addEventListener(ResultEvent.RESULT, logoutComplete);
			httpService.send("{}");
		}
		private function logoutComplete(event:ResultEvent):void
		{
			this.dispatchEvent(new HttpRequestEvent(HttpRequestEvent.LOGOUT_EVENT, event.result.toString()));
		}
		
		public function addExternalRecipeRequest(url:RecipeUrl):void
		{
			httpService.url = Constants.SERVER_URL + "/addRecipe";
			httpService.addEventListener(ResultEvent.RESULT, addExternalRecipeComplete);
			httpService.send(JSON.encode(url));
		}
		private function addExternalRecipeComplete(event:ResultEvent):void
		{
			this.dispatchEvent(new HttpRequestEvent(HttpRequestEvent.ADD_EXTERNAL_RECIPE_EVENT, event.result.toString()));
		}
		
		public function addOwnRecipeRequest(recipe:Recipe):void
		{
			httpService.url = Constants.SERVER_URL + "/addOwnRecipe";
			httpService.addEventListener(ResultEvent.RESULT, addOwnRecipeComplete);
			httpService.send(JSON.encode(recipe));
		}
		private function addOwnRecipeComplete(event:ResultEvent):void
		{
			this.dispatchEvent(new HttpRequestEvent(HttpRequestEvent.ADD_EXTERNAL_RECIPE_EVENT, event.result.toString()));
		}
		
		public function getRecipesRequest():void
		{
			httpService.url = Constants.SERVER_URL + "/listRecipes";
			httpService.addEventListener(ResultEvent.RESULT, getRecipesComplete);
			httpService.send();
		}
		private function getRecipesComplete(event:ResultEvent):void
		{
			this.dispatchEvent(new HttpRequestEvent(HttpRequestEvent.GET_RECIPES_EVENT, event.result.toString()));
		}
		
		public function getRecipesForExportRequest():void
		{
			httpService.url = Constants.SERVER_URL + "/listRecipes";
			httpService.addEventListener(ResultEvent.RESULT, getRecipesForExportComplete);
			httpService.send();
		}
		private function getRecipesForExportComplete(event:ResultEvent):void
		{
			this.dispatchEvent(new HttpRequestEvent(HttpRequestEvent.EXPORT_RECIPE_EVENT, event.result.toString()));
		}
		
			
	}
}