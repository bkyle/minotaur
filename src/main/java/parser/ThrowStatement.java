package parser;

public class ThrowStatement extends ASTNode {

	public static final int EXPRESSION = 0;
	
	public ThrowStatement(Node expression) {
		super();
		this.jjtAddChild(expression, 0);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
