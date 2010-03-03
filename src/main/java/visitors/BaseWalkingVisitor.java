package visitors;

import parser.ArgumentList;
import parser.AssignmentExpression;
import parser.Block;
import parser.BreakStatement;
import parser.CallExpression;
import parser.CaseBlock;
import parser.CaseClause;
import parser.CatchClause;
import parser.ConditionalExpression;
import parser.ContinueStatement;
import parser.DebuggerStatement;
import parser.DefaultClause;
import parser.DoStatement;
import parser.ElementAccess;
import parser.ElementList;
import parser.EmptyStatement;
import parser.Expression;
import parser.ExpressionStatement;
import parser.ForInStatement;
import parser.ForStatement;
import parser.FunctionDeclaration;
import parser.FunctionExpression;
import parser.Identifier;
import parser.IfStatement;
import parser.InfixExpression;
import parser.LabelledStatement;
import parser.Literal;
import parser.MemberAccess;
import parser.MemberExpression;
import parser.NewExpression;
import parser.Node;
import parser.Operator;
import parser.ParameterList;
import parser.PostfixExpression;
import parser.PrimaryExpression;
import parser.Property;
import parser.PropertyList;
import parser.ReturnStatement;
import parser.SourceElements;
import parser.StatementList;
import parser.SwitchStatement;
import parser.ThrowStatement;
import parser.TryStatement;
import parser.UnaryExpression;
import parser.VariableDeclaration;
import parser.VariableDeclarationList;
import parser.VariableStatement;
import parser.Visitor;
import parser.WhileStatement;
import parser.WithStatement;

public class BaseWalkingVisitor implements Visitor {

	private void visitChildren(Node node, Object data) throws Throwable {
		for (int i=0; i<node.getNumChildren(); i++)
			node.getChild(i).accept(this, data);
	}
	
	public Object visit(ArgumentList node, Object data) throws Throwable {
		for (int i=0; i<node.getNumChildren(); i++) {
			node.getChild(i).accept(this, data);
		}
		
		return null;
	}

	public Object visit(AssignmentExpression node, Object data) throws Throwable {
		
		Node lhs = node.getChild(AssignmentExpression.LHS);
		Node op = node.getChild(AssignmentExpression.OP);
		Node rhs = node.getChild(AssignmentExpression.RHS);

		lhs.accept(this, data);
		if (op != null) {
			op.accept(this, data);
			rhs.accept(this, data);
		}
		
		return null;
	}

	public Object visit(Block node, Object data) throws Throwable {
		node.getChildOrNull(Block.STATEMENTS).accept(this, data);
		
		return null;
	}

	public Object visit(BreakStatement node, Object data) throws Throwable {
		Node n = node.getChild(BreakStatement.LABEL);
		if (n != null) {
			n.accept(this, data);
		}
		
		return null;
	}

	public Object visit(CallExpression node, Object data) throws Throwable {
		node.getChild(CallExpression.EXPRESSION).accept(this, data);
		node.getChild(CallExpression.ARGUMENTS).accept(this, data);
		
		return null;
	}

	public Object visit(CaseBlock node, Object data) throws Throwable {
		visitChildren(node, data);
		
		return null;
	}

	public Object visit(CaseClause node, Object data) throws Throwable {
		node.getChild(CaseClause.EXPRESSION).accept(this, data);
		node.getChildOrNull(CaseClause.BODY).accept(this, data);
		
		return null;
	}

	public Object visit(CatchClause node, Object data) throws Throwable {
		node.getChild(CatchClause.IDENTIFIER).accept(this, data);
		node.getChild(CatchClause.BLOCK).accept(this, data);

		return null;
	}

	public Object visit(ConditionalExpression node, Object data) throws Throwable {
		node.getChild(ConditionalExpression.CONDITION).accept(this, data);
		
		Node trueExpression = node.getChild(ConditionalExpression.TRUE_EXPRESSION);
		Node falseExpression = node.getChild(ConditionalExpression.FALSE_EXPRESSION);
		if (trueExpression != null) {
			trueExpression.accept(this, data);
			falseExpression.accept(this, data);
		}
		
		return null;
	}

	public Object visit(ContinueStatement node, Object data) throws Throwable {
		Node label = node.getChild(ContinueStatement.LABEL);
		
		if (label != null) {
			label.accept(this, data);
		}
		
		return null;
	}

	public Object visit(DebuggerStatement node, Object data) throws Throwable {
		return null;
	}

	public Object visit(DefaultClause node, Object data) throws Throwable {
		node.getChildOrNull(DefaultClause.BODY).accept(this, data);
		
		return null;
	}

	public Object visit(DoStatement node, Object data) throws Throwable {
		node.getChild(DoStatement.BODY).accept(this, data);
		node.getChild(DoStatement.CONDITION).accept(this, data);
		
		return null;
	}

	public Object visit(ElementAccess node, Object data) throws Throwable {
		node.getChild(ElementAccess.EXPRESSION).accept(this, data);
		
		return null;
	}

	public Object visit(ElementList node, Object data) throws Throwable {
		for (int i=0; i<node.getNumChildren(); i++) {
			node.getChild(i).accept(this, data);
		}
		
		return null;
	}

	public Object visit(EmptyStatement node, Object data) throws Throwable {
		return null;
	}

	public Object visit(Expression node, Object data) throws Throwable {
		for (int i=0; i<node.getNumChildren(); i++) {
			node.getChild(i).accept(this, data);
		}
		
		return null;
	}

	public Object visit(ExpressionStatement node, Object data) throws Throwable {
		node.getChild(ExpressionStatement.EXPRESSION).accept(this, data);
		
		return null;
	}

	
	public Object visit(ForInStatement node, Object data) throws Throwable {
		Node initializer = node.getChild(ForInStatement.INITIALIZER);
		if (initializer != null) {
			initializer.accept(this, data);
		}
		node.getChild(ForInStatement.EXPRESSION).accept(this, data);
		node.getChild(ForInStatement.BODY).accept(this, data);
		
		return null;
	}

	public Object visit(ForStatement node, Object data) throws Throwable {
		Node initializer = node.getChild(ForStatement.INITIALIZER);
		if (initializer != null) {
			initializer.accept(this, data);
		}
		
		node.getChildOrNull(ForStatement.CONDITION).accept(this, data);
		node.getChildOrNull(ForStatement.STEP).accept(this, data);
		node.getChild(ForStatement.BODY).accept(this, data);
		
		return null;
	}

	public Object visit(FunctionDeclaration node, Object data) throws Throwable {
		node.getChild(FunctionDeclaration.IDENTIFIER).accept(this, data);
		node.getChildOrNull(FunctionDeclaration.PARAMETERS).accept(this, data);
		node.getChild(FunctionDeclaration.BODY).accept(this, data);
		return null;
	}

	public Object visit(FunctionExpression node, Object data) throws Throwable {
		node.getChildOrNull(FunctionDeclaration.IDENTIFIER).accept(this, data);
		node.getChildOrNull(FunctionDeclaration.PARAMETERS).accept(this, data);
		node.getChild(FunctionDeclaration.BODY).accept(this, data);
		return null;
	}

	public Object visit(Identifier node, Object data) throws Throwable {
		return null;
	}

	public Object visit(IfStatement node, Object data) throws Throwable {
		node.getChild(IfStatement.CONDITION).accept(this, data);
		node.getChild(IfStatement.STATEMENT).accept(this, data);
		
		if (node.getChild(IfStatement.ELSE) != null) {
			node.getChild(IfStatement.ELSE).accept(this, data);
		}
		
		return null;
	}

	public Object visit(InfixExpression node, Object data) throws Throwable {
		Node lhs = node.getChild(AssignmentExpression.LHS);
		Node op = node.getChild(AssignmentExpression.OP);
		Node rhs = node.getChild(AssignmentExpression.RHS);

		lhs.accept(this, data);
		if (op != null) {
			op.accept(this, data);
			rhs.accept(this, data);
		}

		return null;
	}

	public Object visit(LabelledStatement node, Object data) throws Throwable {
		
		node.getChild(LabelledStatement.LABEL).accept(this, data);
		node.getChild(LabelledStatement.STATEMENT).accept(this, data);
		
		return null;
	}

	public Object visit(Literal node, Object data) throws Throwable {
		if (node.getType() == Literal.ARRAY || node.getType() == Literal.OBJECT) {
			node.getChildOrNull(0).accept(this, data);
		}

		return null;
	}

	public Object visit(MemberAccess node, Object data) throws Throwable {
		node.getChild(MemberAccess.MEMBER).accept(this, data);
		
		return null;
	}

	public Object visit(MemberExpression node, Object data) throws Throwable {
		Node lhs = node.getChild(MemberExpression.OBJECT);
		Node rhs = node.getChild(MemberExpression.MEMBER_OR_ELEMENT);

		lhs.accept(this, data);
		if (rhs != null)
			rhs.accept(this, data);

		return null;
	}

	public Object visit(NewExpression node, Object data) throws Throwable {
		node.getChild(NewExpression.EXPRESSION).accept(this, data);

		return null;
	}

	public Object visit(Operator node, Object data) throws Throwable {
		return null;
	}

	public Object visit(ParameterList node, Object data) throws Throwable {
		for (int i=0; i<node.getNumChildren(); i++) {
			node.getChild(i).accept(this, data);
		}
		
		return null;
	}

	public Object visit(PostfixExpression node, Object data) throws Throwable {
		node.getChild(PostfixExpression.EXPRESSION).accept(this, data);
		node.getChildOrNull(PostfixExpression.OP).accept(this, data);
		
		return null;
	}

	public Object visit(PrimaryExpression node, Object data) throws Throwable {
		if (node.getChild(PrimaryExpression.EXPRESSION) instanceof Expression) {
			node.getChild(PrimaryExpression.EXPRESSION).accept(this, data);
		} else {
			node.getChild(PrimaryExpression.EXPRESSION).accept(this, data);
		}
		
		return null;
	}

	public Object visit(Property node, Object data) throws Throwable {
		node.getChild(Property.NAME).accept(this, data);
		node.getChild(Property.VALUE).accept(this, data);
		
		return null;
	}

	public Object visit(PropertyList node, Object data) throws Throwable {
		for (int i=0; i<node.getNumChildren(); i++) {
			node.getChild(i).accept(this, data);
		}
		
		return null;
	}

	public Object visit(ReturnStatement node, Object data) throws Throwable {
		if (node.getChild(ReturnStatement.EXPRESSION) != null) {
			node.getChild(ReturnStatement.EXPRESSION).accept(this, data);
		}
		return null;
	}

	public Object visit(SourceElements node, Object data) throws Throwable {
		for (int i=0; i<node.getNumChildren(); i++) {
			node.getChild(i).accept(this, data);
		}
		
		return null;
	}

	public Object visit(StatementList node, Object data) throws Throwable {
		for (int i=0; i<node.getNumChildren(); i++) {
			node.getChild(i).accept(this, data);
		}
		
		return null;
	}

	public Object visit(SwitchStatement node, Object data) throws Throwable {
		node.getChild(SwitchStatement.EXPRESSION).accept(this, data);
		node.getChild(SwitchStatement.CASE_BLOCK).accept(this, data);
		
		return null;
	}

	public Object visit(ThrowStatement node, Object data) throws Throwable {
		node.getChild(ThrowStatement.EXPRESSION).accept(this, data);
		return null;
	}

	public Object visit(TryStatement node, Object data) throws Throwable {
		node.getChild(TryStatement.BODY).accept(this, data);
		
		node.getChildOrNull(TryStatement.CATCH_BLOCK).accept(this, data);
		
		if (node.getChild(TryStatement.FINALLY_BLOCK) != null) {
			node.getChildOrNull(TryStatement.FINALLY_BLOCK).accept(this, data);
		}
		
		return null;
	}

	public Object visit(UnaryExpression node, Object data) throws Throwable {
		node.getChild(UnaryExpression.OP).accept(this, data);
		node.getChild(UnaryExpression.EXPRESSION).accept(this, data);
		
		return null;
	}

	public Object visit(VariableDeclaration node, Object data) throws Throwable {
		
		node.getChild(VariableDeclaration.IDENTIFIER).accept(this, data);
		if (node.getChild(VariableDeclaration.INITIALIZER) != null)	{
			node.getChild(VariableDeclaration.INITIALIZER).accept(this, data);
		}
		
		return null;
	}

	public Object visit(VariableDeclarationList node, Object data) throws Throwable {
		for (int i=0; i<node.getNumChildren(); i++) {
			node.getChild(i).accept(this, data);
		}
		
		return null;
	}

	public Object visit(VariableStatement node, Object data) throws Throwable {
		node.getChild(VariableStatement.DECLARATIONS).accept(this, data);
		
		return null;
	}

	public Object visit(WhileStatement node, Object data) throws Throwable {
		node.getChild(WhileStatement.CONDITION).accept(this, data);
		node.getChild(WhileStatement.BODY).accept(this, data);
		
		return null;
	}

	public Object visit(WithStatement node, Object data) throws Throwable {
		node.getChild(WhileStatement.CONDITION).accept(this, data);
		node.getChild(WhileStatement.BODY).accept(this, data);
		
		return null;
	}
}
