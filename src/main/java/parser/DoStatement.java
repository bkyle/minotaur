package parser;

public class DoStatement extends ASTNode {

	public static final int BODY = 0;
	public static final int CONDITION = 1;
	
	public DoStatement(Node body, Node expression) {
		super();
		this.jjtAddChild(body, 0);
		this.jjtAddChild(expression, 1);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
