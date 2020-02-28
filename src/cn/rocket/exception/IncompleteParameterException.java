package cn.rocket.exception;

public class IncompleteParameterException extends Exception {
	private static final long serialVersionUID = 8651444993557621704L;
	
	public IncompleteParameterException() {
		super("Incomplete parameter! Please type address(s)!");
	}
}
