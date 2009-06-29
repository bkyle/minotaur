package parser;

public class CaseBlock extends ASTNode {
	
	public CaseBlock() {
		super();
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
