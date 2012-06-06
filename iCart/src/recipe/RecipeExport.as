package recipe
{
	import flash.net.FileReference;
	import flash.net.FileReferenceList;
	import flash.net.SharedObject;
	
	public class RecipeExport
	{
		public function RecipeExport()
		{
			//Recipes to save
			
			
			//file chooser - location for export
			var myFilename:FileReference = new FileReference();
			myFilename.save("Save ALL the tests!!!");
			
			//Erstellen des SharedObject und Herstellen der Verbindung zum lokalen File
			var mySharedObject:SharedObject = SharedObject.getLocal("fileName");
			
			//Beispiel-Array zum Befüllen der lokalen Datei
			var myArray:Array = new Array();
			myArray[0] = "text";
			
			//Befüllen der Datei
			mySharedObject.data.fileName = myArray;
			
			//Schreiben der Datei
			mySharedObject.flush();
			
			//Laden des Inhaltes
			myArray[1] = mySharedObject.data.fileName;
		}
	}
}