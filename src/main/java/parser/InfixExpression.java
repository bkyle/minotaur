package parser;

public class InfixExpression extends ASTNode {

	public static final int LHS = 0;
	public static final int OP = 1;
	public static final int RHS = 2;
	
	public InfixExpression(Node leftOperand, Node operator, Node rightOperand) {
		super();
		this.jjtAddChild(leftOperand, 0);
		this.jjtAddChild(operator, 1);
		this.jjtAddChild(rightOperand, 2);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
