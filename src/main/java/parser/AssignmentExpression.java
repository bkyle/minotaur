package parser;

public class AssignmentExpression extends InfixExpression {

	public AssignmentExpression(Node leftOperand, Node operator,
			Node rightOperand) {
		super(leftOperand, operator, rightOperand);
	}

	public Object accept(Visitor v, Object data) throws Throwable
	{
		return v.visit(this, data);
	}

}
