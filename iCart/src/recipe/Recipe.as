package recipe
{
	

	public class Recipe
	{
		private var _name:String;
		private var _amountPortion:int;
		private var _ingredient:Array;
		
		
		public function Recipe(n:String, a:int, i:Array){
			_name = n;
			_amountPortion = a;
			_ingredient = i;
		}
		
		public function get ingredient():Array
		{
			return _ingredient;
		}

		public function set ingredient(value:Array):void
		{
			_ingredient = value;
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