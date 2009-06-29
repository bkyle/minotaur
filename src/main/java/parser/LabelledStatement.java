package parser;

public class LabelledStatement extends ASTNode {

	public static final int LABEL = 0;
	public static final int STATEMENT = 1;
	
	public LabelledStatement(Node identifier, Node statement) {
		super();
		this.jjtAddChild(identifier, 0);
		this.jjtAddChild(statement, 1);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
