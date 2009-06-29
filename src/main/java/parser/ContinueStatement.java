package parser;

public class ContinueStatement extends ASTNode {

	public static final int LABEL = 0;
	
	public ContinueStatement(Node identifier) {
		super();
		this.jjtAddChild(identifier, 0);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
