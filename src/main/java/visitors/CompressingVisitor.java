package visitors;


import java.io.OutputStream;

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

public class CompressingVisitor implements Visitor {

	OutputStream out = null;

	public CompressingVisitor(OutputStream out) {
		this.out = out;
	}

	private void write(String s) throws Throwable {
		out.write(s.getBytes("UTF-8"));
	}
	
	private void visitChildren(Node node, Object data) throws Throwable {
		for (int i=0; i<node.getNumChildren(); i++)
			node.getChild(i).accept(this, data);
	}
	
	@Override
	public Object visit(ArgumentList node, Object data) throws Throwable {
		write("(");
		for (int i=0; i<node.getNumChildren(); i++) {
			node.getChild(i).accept(this, data);
			if (i < node.getNumChildren()-1)
				write(",");
		}
		write(")");
		
		return null;
	}

	@Override
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

	@Override
	public Object visit(Block node, Object data) throws Throwable {
		write("{");
		node.getChildOrNull(Block.STATEMENTS).accept(this, data);
		write("}");
		
		return null;
	}

	@Override
	public Object visit(BreakStatement node, Object data) throws Throwable {
		write("break");
		
		Node n = node.getChild(BreakStatement.LABEL);
		if (n != null) {
			write(" ");
			n.accept(this, data);
		}
		write(";");
		
		return null;
	}

	@Override
	public Object visit(CallExpression node, Object data) throws Throwable {
		node.getChild(CallExpression.EXPRESSION).accept(this, data);
		node.getChild(CallExpression.ARGUMENTS).accept(this, data);
		
		return null;
	}

	@Override
	public Object visit(CaseBlock node, Object data) throws Throwable {
		write("{");
		visitChildren(node, data);
		write("}");
		
		return null;
	}

	@Override
	public Object visit(CaseClause node, Object data) throws Throwable {
		write("case ");
		node.getChild(CaseClause.EXPRESSION).accept(this, data);
		write(":");
		node.getChildOrNull(CaseClause.BODY).accept(this, data);
		
		return null;
	}

	@Override
	public Object visit(CatchClause node, Object data) throws Throwable {
		write("catch(");
		node.getChild(CatchClause.IDENTIFIER).accept(this, data);
		write(")");
		node.getChild(CatchClause.BLOCK).accept(this, data);

		return null;
	}

	@Override
	public Object visit(ConditionalExpression node, Object data) throws Throwable {
		node.getChild(ConditionalExpression.CONDITION).accept(this, data);
		
		Node trueExpression = node.getChild(ConditionalExpression.TRUE_EXPRESSION);
		Node falseExpression = node.getChild(ConditionalExpression.FALSE_EXPRESSION);
		if (trueExpression != null) {
			write("?");
			trueExpression.accept(this, data);
			write(":");
			falseExpression.accept(this, data);
		}
		
		return null;
	}

	@Override
	public Object visit(ContinueStatement node, Object data) throws Throwable {
		Node label = node.getChild(ContinueStatement.LABEL);
		
		write("continue");
		if (label != null) {
			write(" ");
			label.accept(this, data);
		}
		write(";");
		
		return null;
	}

	@Override
	public Object visit(DebuggerStatement node, Object data) throws Throwable {
		write("debugger;");
		
		return null;
	}

	@Override
	public Object visit(DefaultClause node, Object data) throws Throwable {
		write("default:");
		node.getChildOrNull(DefaultClause.BODY).accept(this, data);
		
		return null;
	}

	@Override
	public Object visit(DoStatement node, Object data) throws Throwable {
		write("do");
		node.getChild(DoStatement.BODY).accept(this, data);
		write("while(");
		node.getChild(DoStatement.CONDITION).accept(this, data);
		write(");");
		
		return null;
	}

	@Override
	public Object visit(ElementAccess node, Object data) throws Throwable {
		write("[");
		node.getChild(ElementAccess.EXPRESSION).accept(this, data);
		write("]");
		
		return null;
	}

	@Override
	public Object visit(ElementList node, Object data) throws Throwable {
		for (int i=0; i<node.getNumChildren(); i++) {
			node.getChild(i).accept(this, data);
			if (i < node.getNumChildren()-1)
				write(",");
		}
		
		return null;
	}

	@Override
	public Object visit(EmptyStatement node, Object data) throws Throwable {
		write(";");
		return null;
	}

	@Override
	public Object visit(Expression node, Object data) throws Throwable {
		for (int i=0; i<node.getNumChildren(); i++) {
			node.getChild(i).accept(this, data);
			
			if (i < node.getNumChildren()-1)
				write(",");
		}
		
		return null;
	}

	@Override
	public Object visit(ExpressionStatement node, Object data) throws Throwable {
		node.getChild(ExpressionStatement.EXPRESSION).accept(this, data);
		write(";");
		
		return null;
	}

	
@Override
	public Object visit(ForInStatement node, Object data) throws Throwable {
		write("for(");
		Node initializer = node.getChild(ForInStatement.INITIALIZER);
		if (initializer != null) {
			if (initializer instanceof VariableDeclarationList)
				write("var ");
			
			initializer.accept(this, data);
		}
		write(" in " );
		node.getChild(ForInStatement.EXPRESSION).accept(this, data);
		write(")");
		node.getChild(ForInStatement.BODY).accept(this, data);
		
		return null;
	}

	@Override
	public Object visit(ForStatement node, Object data) throws Throwable {
		
		write("for(");

		Node initializer = node.getChild(ForStatement.INITIALIZER);
		if (initializer != null) {
			if (initializer instanceof VariableDeclarationList)
				write("var ");
			
			initializer.accept(this, data);
		}
		
		write(";");
		node.getChildOrNull(ForStatement.CONDITION).accept(this, data);
		write(";");
		node.getChildOrNull(ForStatement.STEP).accept(this, data);
		write(")");
		node.getChild(ForStatement.BODY).accept(this, data);
		
		return null;
	}

	@Override
	public Object visit(FunctionDeclaration node, Object data) throws Throwable {
		write("function ");
		node.getChild(FunctionDeclaration.IDENTIFIER).accept(this, data);
		write("(");
		node.getChildOrNull(FunctionDeclaration.PARAMETERS).accept(this, data);
		write("){");
		node.getChild(FunctionDeclaration.BODY).accept(this, data);
		write("}");
		
		return null;
	}

	@Override
	public Object visit(FunctionExpression node, Object data) throws Throwable {
		write("function ");
		node.getChildOrNull(FunctionDeclaration.IDENTIFIER).accept(this, data);
		write("(");
		node.getChildOrNull(FunctionDeclaration.PARAMETERS).accept(this, data);
		write("){");
		node.getChild(FunctionDeclaration.BODY).accept(this, data);
		write("}");
		
		return null;
	}

	@Override
	public Object visit(Identifier node, Object data) throws Throwable {
		write(node.getValue().toString());
		
		return null;
	}

	@Override
	public Object visit(IfStatement node, Object data) throws Throwable {
		write("if(");
		node.getChild(IfStatement.CONDITION).accept(this, data);
		write(")");
		node.getChild(IfStatement.STATEMENT).accept(this, data);
		
		if (node.getChild(IfStatement.ELSE) != null) {
			write(" else ");
			node.getChild(IfStatement.ELSE).accept(this, data);
		}
		
		return null;
	}

	@Override
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

	@Override
	public Object visit(LabelledStatement node, Object data) throws Throwable {
		
		node.getChild(LabelledStatement.LABEL).accept(this, data);
		write(":");
		node.getChild(LabelledStatement.STATEMENT).accept(this, data);
		
		return null;
	}

	@Override
	public Object visit(Literal node, Object data) throws Throwable {
		if (node.getType() == Literal.ARRAY) {
			write("[");
			node.getChildOrNull(0).accept(this, data);
			write("]");
		} else if (node.getType() == Literal.OBJECT) {
			write("{");
			node.getChildOrNull(0).accept(this, data);
			write("}");
		} else {
			if (node.getValue() != null)
				write(node.getValue().toString());
			else
				write("null");
		}

		return null;
	}

	@Override
	public Object visit(MemberAccess node, Object data) throws Throwable {
		write(".");
		node.getChild(MemberAccess.MEMBER).accept(this, data);
		
		return null;
	}

	@Override
	public Object visit(MemberExpression node, Object data) throws Throwable {
		Node lhs = node.getChild(MemberExpression.OBJECT);
		Node rhs = node.getChild(MemberExpression.MEMBER_OR_ELEMENT);

		lhs.accept(this, data);
		if (rhs != null)
			rhs.accept(this, data);

		return null;
	}

	@Override
	public Object visit(NewExpression node, Object data) throws Throwable {
		write("new ");
		node.getChild(NewExpression.EXPRESSION).accept(this, data);

		return null;
	}

	@Override
	public Object visit(Operator node, Object data) throws Throwable {
		
		String value = node.getValue().toString();
		
		// instanceof, typeof...
		if (value.length() > 2 || value == "in") {
			write(" ");
			write(value);
			write(" ");
		} else {
			write(value);
		}
		
		return null;
	}

	@Override
	public Object visit(ParameterList node, Object data) throws Throwable {
		for (int i=0; i<node.getNumChildren(); i++) {
			node.getChild(i).accept(this, data);
			if (i < node.getNumChildren()-1)
				write(",");
		}
		
		return null;
	}

	@Override
	public Object visit(PostfixExpression node, Object data) throws Throwable {
		node.getChild(PostfixExpression.EXPRESSION).accept(this, data);
		node.getChildOrNull(PostfixExpression.OP).accept(this, data);
		
		return null;
	}

	@Override
	public Object visit(PrimaryExpression node, Object data) throws Throwable {
		if (node.getChild(PrimaryExpression.EXPRESSION) instanceof Expression) {
			write("(");
			node.getChild(PrimaryExpression.EXPRESSION).accept(this, data);
			write(")");
		} else {
			node.getChild(PrimaryExpression.EXPRESSION).accept(this, data);
		}
		
		return null;
	}

	@Override
	public Object visit(Property node, Object data) throws Throwable {
		node.getChild(Property.NAME).accept(this, data);
		write(":");
		node.getChild(Property.VALUE).accept(this, data);
		
		return null;
	}

	@Override
	public Object visit(PropertyList node, Object data) throws Throwable {
		for (int i=0; i<node.getNumChildren(); i++) {
			node.getChild(i).accept(this, data);
			if (i < node.getNumChildren()-1)
				write(",");
		}
		
		return null;
	}

	@Override
	public Object visit(ReturnStatement node, Object data) throws Throwable {
		write("return");
		if (node.getChild(ReturnStatement.EXPRESSION) != null) {
			write(" ");
			node.getChild(ReturnStatement.EXPRESSION).accept(this, data);
		}
		
		write(";");
		
		return null;
	}

	@Override
	public Object visit(SourceElements node, Object data) throws Throwable {
		for (int i=0; i<node.getNumChildren(); i++) {
			node.getChild(i).accept(this, data);
		}
		
		return null;
	}

	@Override
	public Object visit(StatementList node, Object data) throws Throwable {
		for (int i=0; i<node.getNumChildren(); i++) {
			node.getChild(i).accept(this, data);
		}
		
		return null;
	}

	@Override
	public Object visit(SwitchStatement node, Object data) throws Throwable {
		write("switch(");
		node.getChild(SwitchStatement.EXPRESSION).accept(this, data);
		write(")");
		node.getChild(SwitchStatement.CASE_BLOCK).accept(this, data);
		
		return null;
	}

	@Override
	public Object visit(ThrowStatement node, Object data) throws Throwable {
		write("throw ");
		node.getChild(ThrowStatement.EXPRESSION).accept(this, data);
		write(";");
		return null;
	}

	@Override
	public Object visit(TryStatement node, Object data) throws Throwable {
		write("try");
		node.getChild(TryStatement.BODY).accept(this, data);
		
		node.getChildOrNull(TryStatement.CATCH_BLOCK).accept(this, data);
		
		if (node.getChild(TryStatement.FINALLY_BLOCK) != null) {
			write("finally");
			node.getChildOrNull(TryStatement.FINALLY_BLOCK).accept(this, data);
		}
		
		return null;
	}

	@Override
	public Object visit(UnaryExpression node, Object data) throws Throwable {
		node.getChild(UnaryExpression.OP).accept(this, data);
		node.getChild(UnaryExpression.EXPRESSION).accept(this, data);
		
		return null;
	}

	@Override
	public Object visit(VariableDeclaration node, Object data) throws Throwable {
		
		node.getChild(VariableDeclaration.IDENTIFIER).accept(this, data);
		if (node.getChild(VariableDeclaration.INITIALIZER) != null)	{
			write("=");
			node.getChild(VariableDeclaration.INITIALIZER).accept(this, data);
		}
		
		return null;
	}

	@Override
	public Object visit(VariableDeclarationList node, Object data) throws Throwable {
		for (int i=0; i<node.getNumChildren(); i++) {
			node.getChild(i).accept(this, data);
			if (i < node.getNumChildren()-1)
				write(",");
		}
		
		return null;
	}

	@Override
	public Object visit(VariableStatement node, Object data) throws Throwable {
		write("var ");
		node.getChild(VariableStatement.DECLARATIONS).accept(this, data);
		write(";");
		
		return null;
	}

	@Override
	public Object visit(WhileStatement node, Object data) throws Throwable {
		write("while(");
		node.getChild(WhileStatement.CONDITION).accept(this, data);
		write(")");
		node.getChild(WhileStatement.BODY).accept(this, data);
		
		return null;
	}

	@Override
	public Object visit(WithStatement node, Object data) throws Throwable {
		write("with(");
		node.getChild(WhileStatement.CONDITION).accept(this, data);
		write(")");
		node.getChild(WhileStatement.BODY).accept(this, data);
		
		return null;
	}
}
