package parser;

public class ElementAccess extends ASTNode {

	public static final int EXPRESSION = 0;
	
	public ElementAccess(Node EXPRESSION) {
		super();
		this.jjtAddChild(EXPRESSION, 0);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable {
		return v.visit(this, data);
	}

}
