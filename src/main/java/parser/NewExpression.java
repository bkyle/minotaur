package parser;

public class NewExpression extends Node {

	public static final int EXPRESSION = 0;
	
	public NewExpression(Node expression) {
		super();
		this.addChild(expression, 0);
	}

	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}
	
}
