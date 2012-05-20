package recipe
{
	public class RecipeUrl
	{
		private var _url:String;
		public function RecipeUrl(u:String)
		{
			_url = u;
		}
		public function set url(u:String):void{
			_url = u;
		}
		
		public function get url():String{
			return _url;
		}
	}
}