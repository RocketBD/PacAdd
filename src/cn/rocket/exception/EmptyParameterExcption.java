package cn.rocket.exception;

public class EmptyParameterExcption extends Exception {
	private static final long serialVersionUID = 2517470854616817599L;
	
	public EmptyParameterExcption() {
		super("No parameter!");
	}
}
