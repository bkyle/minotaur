package parser;

public class CallExpression extends ASTNode {

	public static final int EXPRESSION = 0;
	public static final int ARGUMENTS = 1;
	
	public CallExpression(Node expression, Node arguments)
	{
		super();
		this.jjtAddChild(expression, 0);
		this.jjtAddChild(arguments, 1);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
