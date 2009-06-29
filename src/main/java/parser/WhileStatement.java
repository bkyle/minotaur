package parser;

public class WhileStatement extends ASTNode {

	public static int CONDITION = 0;
	public static int BODY = 1;
	
	public WhileStatement(Node expression, Node body) {
		super();
		this.jjtAddChild(expression, 0);
		this.jjtAddChild(body, 1);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
