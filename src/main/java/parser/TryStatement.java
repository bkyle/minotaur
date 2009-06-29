package parser;

public class TryStatement extends ASTNode {

	public static final int BODY = 0;
	public static final int CATCH_BLOCK = 1;
	public static final int FINALLY_BLOCK = 2;
	
	public TryStatement(Node block, Node catchBlock, Node finallyBlock) {
		super();
		this.jjtAddChild(block, 0);
		this.jjtAddChild(catchBlock, 1);
		this.jjtAddChild(finallyBlock, 2);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}
	
}
