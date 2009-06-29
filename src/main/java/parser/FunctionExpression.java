package parser;

public class FunctionExpression extends ASTNode {

	public static final int IDENTIFIER = 0;
	public static final int PARAMETERS = 1;
	public static final int BODY = 2;

	public FunctionExpression(Node identifier, Node parameters, Node body) {
		super();
		this.jjtAddChild(identifier, 0);
		this.jjtAddChild(parameters, 1);
		this.jjtAddChild(body, 2);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
