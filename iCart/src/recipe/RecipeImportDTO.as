package recipe
{
	public class RecipeImportDTO
	{
		private var _recipes:Array;
		
		public function RecipeImportDTO(r:Array)
		{
			_recipes = r;
		}
		
		
		public function get recipes():Array
		{
			return _recipes;
		}

		public function set recipes(value:Array):void
		{
			_recipes = value;
		}

	}
}