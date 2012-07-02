package recipe
{
	

	public class Recipe
	{
		private var _name:String;
		private var _amountPortion:int;
		private var _ingredients:Array;
		
		
		public function Recipe(n:String, a:int, i:Array){
			_name = n;
			_amountPortion = a;
			_ingredients = i;
		}
		
		public function get ingredients():Array
		{
			return _ingredients;
		}

		public function set ingredients(value:Array):void
		{
			_ingredients = value;
		}

		public function get amountPortion():int
		{
			return _amountPortion;
		}

		public function set amountPortion(value:int):void
		{
			_amountPortion = value;
		}

		public function get name():String
		{
			return _name;
		}

		public function set name(value:String):void
		{
			_name = value;
		}

	}
}