package recipe
{
	import events.HttpRequestEvent;
	
	import flash.net.FileReference;
	import flash.net.FileReferenceList;
	import flash.net.SharedObject;
	
	import mx.controls.Alert;
	import mx.managers.CursorManager;
	
	import utils.HttpRequests;
	
	public class RecipeExport
	{
		public function RecipeExport()
		{
			getRecipes();			
			
			
		}
		
		//Recipes to save
		private function getRecipes():void
		{
			CursorManager.setBusyCursor();
			var httpRequest:HttpRequests = new HttpRequests();
			httpRequest.getRecipesForExportRequest();
			httpRequest.addEventListener(HttpRequestEvent.EXPORT_RECIPE_EVENT, saveFile);
		}
		
		
		
		//save File
		private function saveFile(myData:HttpRequestEvent):void
		{
			//remove busy curser
			CursorManager.removeBusyCursor();
			
			//file chooser - location for export
			var myFile:FileReference = new FileReference();
			myFile.save(myData.eventData, "Export.iCart");
			
			
		}
	}
}