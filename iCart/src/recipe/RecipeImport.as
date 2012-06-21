package recipe
{
	
	import com.adobe.serialization.json.JSON;
	
	import events.HttpRequestEvent;
	
	import flash.events.Event;
	import flash.net.FileFilter;
	import flash.net.FileReference;
	
	import mx.controls.Alert;
	import mx.managers.CursorManager;
	
	import utils.HttpRequests;

	public class RecipeImport
	{
		private var iCartTypes:FileFilter = new FileFilter("iCart Files (*.iCart)", "*.iCart");
		private var allTypes:Array = new Array(iCartTypes);
		private var myFile:FileReference = new FileReference();
		
		public function RecipeImport()
		{
			openDialog();
		}
		
		private function openDialog():void
		{
			myFile.browse(allTypes);
			myFile.addEventListener(Event.SELECT, loadFile);
			try
			{
				var success:Boolean = myFile.browse();
			}
			catch (error:Error)
			{
				trace("Unable to browse for files.");
			}
		}
		
		
		private function loadFile(event:Event):void
		{
			myFile.addEventListener(Event.COMPLETE, sendRecipes);
			myFile.load();
			
		}
		
		
		private function sendRecipes(event:Event):void
		{
			var data:String =  myFile.data.toString();
			
			
			CursorManager.setBusyCursor();
			var httpRequest:HttpRequests = new HttpRequests();
			httpRequest.sendRecipesForImportRequest(data);
			httpRequest.addEventListener(HttpRequestEvent.IMPORT_RECIPE_EVENT, requestComplete);
		}
		private function requestComplete(myData:HttpRequestEvent):void
		{
			CursorManager.removeBusyCursor();
			var data:Object = JSON.decode(myData.eventData);
			if(data.success == "true"){
				Alert.show(data.description);
				
			}else {
				Alert.show(data.description);
			}
		}
	}
}