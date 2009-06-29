package parser;

public class StatementList extends ASTNode {

	public StatementList() {
		super();
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
