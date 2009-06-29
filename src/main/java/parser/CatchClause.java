package parser;

public class CatchClause extends ASTNode {

	public static final int IDENTIFIER = 0;
	public static final int BLOCK = 1;
	
	public CatchClause(Node identifier, Node block) {
		super();
		this.jjtAddChild(identifier, 0);
		this.jjtAddChild(block, 1);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}
	
}
