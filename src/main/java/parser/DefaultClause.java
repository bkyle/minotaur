package parser;

public class DefaultClause extends ASTNode {

	public static final int BODY = 0;
	
	public DefaultClause(Node body) {
		super();
		this.jjtAddChild(body, 0);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
