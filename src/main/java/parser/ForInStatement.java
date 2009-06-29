package parser;

public class ForInStatement extends ASTNode {

	public static final int INITIALIZER = 0;
	public static final int EXPRESSION = 1;
	public static final int BODY = 2;
	
	public ForInStatement(Node initializer, Node expression, Node body) {
		super();
		this.jjtAddChild(initializer, 0);
		this.jjtAddChild(expression, 1);
		this.jjtAddChild(body, 2);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
