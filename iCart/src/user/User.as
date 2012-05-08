package user
{
	public class User
	{
		private var _email:String;
		private var _firstname:String;
		private var _lastname:String;
		private var _password:String;

		
		public function User(e:String, fn:String, ln:String, pw:String){
			_email = e;
			_firstname = fn;
			_lastname = ln;
			_password = pw;
		}
		
		public function set email(e:String):void{
			_email = e;
		}
		
		public function get email():String{
			return _email;
		}
		
		public function set firstname(fn:String):void{
			_firstname = fn;
		}
		
		public function get firstname():String{
			return _firstname;
		}
		
		public function set lastname(ln:String):void{
			_lastname = ln;
		}
		
		public function get lastname():String{
			return _lastname;
		}
		
		public function set password(pw:String):void{
			_password = pw;
		}
		
		public function get password():String{
			return _password;
		}
		
	}
}