package parser;

public class PrimaryExpression extends ASTNode {

	public static int EXPRESSION = 0;
	
	public PrimaryExpression(Node expression) {
		this.jjtAddChild(expression, 0);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable {
		return v.visit(this, data);
	}
}
