package parser;

public class ConditionalExpression extends ASTNode {

	public static final int CONDITION = 0;
	public static final int TRUE_EXPRESSION = 1;
	public static final int FALSE_EXPRESSION = 2;
	
	public ConditionalExpression(Node condition, Node trueExpression, Node falseExpression)
	{
		super();
		this.jjtAddChild(condition, 0);
		this.jjtAddChild(trueExpression, 1);
		this.jjtAddChild(falseExpression, 2);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}
	
}
