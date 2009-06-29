package parser;

public class MemberExpression extends ASTNode {

	public static final int OBJECT = 0;
	public static final int MEMBER_OR_ELEMENT = 1;
	
	public MemberExpression(Node object, Node memberOrElement) {
		super();
		this.jjtAddChild(object, OBJECT);
		this.jjtAddChild(memberOrElement, MEMBER_OR_ELEMENT);
	}

	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
