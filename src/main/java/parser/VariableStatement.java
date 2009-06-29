package parser;

public class VariableStatement extends ASTNode {

	public static final int DECLARATIONS = 0;
	
	public VariableStatement(Node declarations) {
		super();
		this.addChild(declarations, 0);
		
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
