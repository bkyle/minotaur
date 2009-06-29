package parser;

public class IfStatement extends ASTNode {

	public static final int CONDITION = 0;
	public static final int STATEMENT = 1;
	public static final int ELSE = 2;
	
	public IfStatement(Node expression, Node statement, Node elseClause) {
		super();
		this.jjtAddChild(expression, 0);
		this.jjtAddChild(statement, 1);
		this.jjtAddChild(elseClause, 2);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
