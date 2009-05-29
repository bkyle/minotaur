package parser;

public class ASTNode extends SimpleNode {

	public ASTNode(int i) {
		super(i);
	}

	public ASTNode(JavascriptParser p, int i) {
		super(p, i);
	}
	
	public int getId() {
		return this.id;
	}

}
