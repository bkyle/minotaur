package parser;

public class EmptyStatement extends ASTNode {

	public EmptyStatement() {
		super();
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
