package recipe
{
	import flash.net.FileReference;
	
	import mx.collections.ArrayCollection;
	import mx.managers.CursorManager;

	public class ExportAsCart
	{
		public function ExportAsCart(data:Array):void
		{
			CursorManager.setBusyCursor();
			parseDataToCSV(data);
		}
		
		
		private function parseDataToCSV(data:Array):void{
			data.sortOn("name", Array.CASEINSENSITIVE);
			
			var myData : String = "Menge;Bezeichnung\r\n";
			for(var i:int=0; i < data.length; i++)
			{
				myData += data[i]['amount']+";"+data[i]['name']+"\r\n";
			}
			saveFile(myData);
		}
		//save File
		private function saveFile(myData:String):void
		{
			//remove busy curser
			CursorManager.removeBusyCursor();
			
			//file chooser - location for export
			var myFile:FileReference = new FileReference();
			myFile.save(myData, "myCart.csv");
			
			
		}
	}
}