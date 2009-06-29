package parser;

public class Block extends ASTNode {

	public static final int STATEMENTS = 0;
	
	public Block(Node statements) {
		super();
		this.jjtAddChild(statements, 0);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}
	
}
