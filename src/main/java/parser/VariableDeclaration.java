package parser;

public class VariableDeclaration extends ASTNode {

	public static final int IDENTIFIER = 0;
	public static final int INITIALIZER = 1;
	
	public VariableDeclaration(Node identifier, Node initializer) {
		super();
		this.jjtAddChild(identifier, 0);
		this.jjtAddChild(initializer, 1);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
