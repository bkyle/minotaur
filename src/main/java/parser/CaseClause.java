package parser;

public class CaseClause extends ASTNode {

	public static final int EXPRESSION = 0;
	public static final int BODY = 1;
	
	public CaseClause(Node expression, Node body) {
		super();
		this.jjtAddChild(expression, 0);
		this.jjtAddChild(body, 1);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}
	
}
