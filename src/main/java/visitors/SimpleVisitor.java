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

public abstract class SimpleVisitor implements Visitor {

	protected abstract Object visit(Node node, Object data) throws Throwable;
	
	
	public Object visit(ArgumentList node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(AssignmentExpression node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(Block node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(BreakStatement node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(CallExpression node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(CaseBlock node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(CaseClause node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(CatchClause node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(ConditionalExpression node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(ContinueStatement node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(DebuggerStatement node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(DefaultClause node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(DoStatement node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(ElementAccess node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}


	public Object visit(ElementList node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}


	public Object visit(EmptyStatement node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(Expression node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(ExpressionStatement node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(ForInStatement node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(ForStatement node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(FunctionDeclaration node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(FunctionExpression node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(Identifier node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(IfStatement node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(InfixExpression node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(LabelledStatement node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(Literal node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(MemberAccess node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(MemberExpression node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(NewExpression node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(Operator node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(ParameterList node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(PostfixExpression node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(PrimaryExpression node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(Property node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(PropertyList node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(ReturnStatement node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(SourceElements node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(StatementList node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(SwitchStatement node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(ThrowStatement node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(TryStatement node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(UnaryExpression node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(VariableDeclaration node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(VariableDeclarationList node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(VariableStatement node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(WhileStatement node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

	
	public Object visit(WithStatement node, Object data) throws Throwable {
		return this.visit((Node)node, data);
	}

}
