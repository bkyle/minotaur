package parser;

public class VariableDeclarationList extends ASTNode {

	public VariableDeclarationList() {
		super();
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
