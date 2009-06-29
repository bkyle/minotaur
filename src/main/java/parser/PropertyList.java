package parser;

public class PropertyList extends ASTNode {

	public PropertyList() {
		super();
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
