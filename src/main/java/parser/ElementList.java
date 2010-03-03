package parser;

public class ElementList extends ASTNode {

	public Object accept(Visitor v, Object data) throws Throwable {
		return v.visit(this, data);
	}

}
