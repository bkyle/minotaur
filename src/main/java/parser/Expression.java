package parser;

public class Expression extends ASTNode {

	public Expression() {
		super();
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
