package parser;

public class Literal extends ASTNode {

	public static final int STRING = 0;
	public static final int NUMBER = 1;
	public static final int BOOLEAN = 2;
	public static final int REGEX = 3;
	public static final int NULL = 4;
	public static final int ARRAY = 5;
	public static final int OBJECT = 6;
	
	private int type = -1;
	
	public Literal(int type, Object image) {
		// TODO: Literal is currently a node with it's value being either a string
		// or another node.  Literal should be broken down into subclasses, one for
		// each type of node.
		
		super();
		this.type = type;
		
		if (image instanceof Node)
			this.jjtAddChild((Node)image, 0);
		else
			this.value = image;
	}
	
	public int getType() {
		return this.type;
	}
	
	public Object getValue() {
		return this.value;
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
