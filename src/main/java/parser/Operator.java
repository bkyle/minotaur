package parser;

public class Operator extends ASTNode {

	public Operator(String value) {
		super();
		this.value = value;
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
