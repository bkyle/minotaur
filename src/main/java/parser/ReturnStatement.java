package parser;

public class ReturnStatement extends ASTNode {

	public static final int EXPRESSION = 0;
	
	public ReturnStatement(Node expression) {
		super();
		this.jjtAddChild(expression, 0);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
