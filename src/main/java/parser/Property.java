package parser;

public class Property extends ASTNode {

	public static final int NAME = 0;
	public static final int VALUE = 1;
	
	public Property(Node name, Node value) {
		super();
		this.jjtAddChild(name, 0);
		this.jjtAddChild(value, 1);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
