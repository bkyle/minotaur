package parser;

public class UnaryExpression extends ASTNode {

	public static final int OP = 0;
	public static final int EXPRESSION = 1;
	
	public UnaryExpression(Node operator, Node expression) {
		super();
		this.jjtAddChild(operator, 0);
		this.jjtAddChild(expression, 1);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
