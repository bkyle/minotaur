package parser;

public class ForStatement extends ASTNode {

	public static final int INITIALIZER = 0;
	public static final int CONDITION = 1;
	public static final int STEP = 2;
	public static final int BODY = 3;

	public ForStatement(Node initializer, Node condition, Node step, Node body) {
		super();
		this.jjtAddChild(initializer, 0);
		this.jjtAddChild(condition, 1);
		this.jjtAddChild(step, 2);
		this.jjtAddChild(body, 3);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
