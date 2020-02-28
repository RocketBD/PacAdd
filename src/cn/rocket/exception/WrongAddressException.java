package cn.rocket.exception;

public class WrongAddressException extends Exception {
	private static final long serialVersionUID = -394620265585144442L;
	
	public WrongAddressException() {
		super("The address(s) is(are) wrong! Please check them and input again.");
	}
	
	public WrongAddressException(String input) {
		super("The address \""+input+"\" you input is wrong! Input the right one.");
	}
}
