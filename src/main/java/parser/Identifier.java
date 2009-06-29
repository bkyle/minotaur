package parser;

public class Identifier extends ASTNode {

	public Identifier(String image) {
		super();
		this.value = image;
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
