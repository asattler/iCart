package recipe
{
	import flash.events.Event;
	import flash.events.IOErrorEvent;
	import flash.net.FileReference;
	import flash.utils.ByteArray;
	
	import mx.controls.Alert;
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
			
			var myData : String = "Amount;Ingredient\r\n";
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
			
			var bytes:ByteArray = new ByteArray();
			bytes.writeMultiByte(myData, "unicode");
			
			//file chooser - location for export
			
			var myFile:FileReference = new FileReference();
			myFile.addEventListener(IOErrorEvent.IO_ERROR, function(e:Event):void{
				Alert.show("File is not writable");
			});
			
			myFile.save(bytes, "myCart.csv");	
			
			
			
		}
	}
}