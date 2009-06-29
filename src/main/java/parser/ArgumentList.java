package parser;

public class ArgumentList extends ASTNode {

	public static final int ARGMENTS = 0;
	
	public ArgumentList() {
		super();
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}
	
}
