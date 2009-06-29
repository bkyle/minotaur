package parser;

public class MemberAccess extends ASTNode {

	public static final int MEMBER = 0;
	
	public MemberAccess(Node member) {
		super();
		this.jjtAddChild(member, 0);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable {
		return v.visit(this, data);
	}

}
