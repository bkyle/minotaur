package parser;

public class ParameterList extends ASTNode {

	public ParameterList() {
		super();
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}
	
}
