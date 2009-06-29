package parser;

public class SwitchStatement extends ASTNode {

	public static final int EXPRESSION = 0;
	public static final int CASE_BLOCK = 1;
	
	public SwitchStatement(Node expression, Node caseBlock)
	{
		super();
		this.jjtAddChild(expression, 0);
		this.jjtAddChild(caseBlock, 1);
	}
	
	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
