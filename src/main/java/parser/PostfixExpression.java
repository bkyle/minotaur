package parser;

public class PostfixExpression extends ASTNode {

	public static final int EXPRESSION = 0;
	public static final int OP = 1;
	
	public PostfixExpression(Node expression, Node operator) {
		super();
		this.jjtAddChild(expression, 0);
		this.jjtAddChild(operator, 1);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
