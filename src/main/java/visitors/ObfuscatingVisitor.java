package visitors;

import java.util.Iterator;

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
import parser.Scope;
import parser.ScopeRecord;
import parser.SourceElements;
import parser.StatementList;
import parser.SwitchStatement;
import parser.ThrowStatement;
import parser.TryStatement;
import parser.UnaryExpression;
import parser.VariableDeclaration;
import parser.VariableDeclarationList;
import parser.VariableStatement;
import parser.WhileStatement;
import parser.WithStatement;

public class ObfuscatingVisitor extends BaseWalkingVisitor {
	
	private static final String OBFUSCATED_IDENTIFIER = "obfuscated-identifier";

	private Scope scope = Scope.newInstance();

	
	private void obfuscate(Identifier node) {
		ScopeRecord record = (ScopeRecord) this.scope.lookup(node.getValue().toString(), Scope.CREATE_MODE_NEVER);
		if (record == null)
			return;
		
		String obfuscatedIdentifier = (String) record.get(ObfuscatingVisitor.OBFUSCATED_IDENTIFIER);
		if (obfuscatedIdentifier == null) {
			
			
			// Don't obfuscate global identifiers;
			if (record.getOwner().getDepth() > 0) {
				obfuscatedIdentifier = "_" + Math.abs(this.scope.indexOf(node.getValue().toString()));
			} else {
				obfuscatedIdentifier = node.getValue().toString();
			}
			
			record.put(ObfuscatingVisitor.OBFUSCATED_IDENTIFIER, obfuscatedIdentifier);
		}
		
		node.setValue(obfuscatedIdentifier);
	}
	
	@Override
	public Object visit(ParameterList node, Object data) throws Throwable {
		for (Iterator i=node.iterator(); i.hasNext();)
		{
			((Node)i.next()).accept(this, Boolean.TRUE);
		}
		return null;
	}
	
	@Override
	public Object visit(CatchClause node, Object data) throws Throwable {
		
		scope.enter();

		Node identifier = node.getChildOrNull(CatchClause.IDENTIFIER);
		
		identifier.accept(ScopeProbeVisitor.getInstance(), scope);
		
		if (identifier instanceof Identifier)
			obfuscate((Identifier) identifier);
		
		node.getChildOrNull(CatchClause.BLOCK).accept(this, Boolean.FALSE);
		
		scope.exit();

		return null;
	}

	@Override
	public Object visit(FunctionDeclaration node, Object data) throws Throwable {
		
		Node name = node.getChildOrNull(FunctionExpression.IDENTIFIER);
		if (name instanceof Identifier)
			name.accept(this, Boolean.TRUE);
		
		scope = scope.enter();
		node.getChildOrNull(FunctionDeclaration.PARAMETERS).accept(ScopeProbeVisitor.getInstance(), scope);
		node.getChildOrNull(FunctionDeclaration.BODY).accept(ScopeProbeVisitor.getInstance(), scope);
		
		node.getChildOrNull(FunctionDeclaration.PARAMETERS).accept(this, Boolean.FALSE);
		node.getChildOrNull(FunctionDeclaration.BODY).accept(this, Boolean.FALSE);
		
		scope = scope.exit();

		return null;
	}

	@Override
	public Object visit(FunctionExpression node, Object data) throws Throwable {
		
		Node name = node.getChildOrNull(FunctionExpression.IDENTIFIER);
		if (name instanceof Identifier)
			name.accept(this, Boolean.TRUE);
		
		scope = scope.enter();
		node.getChildOrNull(FunctionExpression.PARAMETERS).accept(ScopeProbeVisitor.getInstance(), scope);
		node.getChildOrNull(FunctionExpression.BODY).accept(ScopeProbeVisitor.getInstance(), scope);
		
		node.getChildOrNull(FunctionExpression.PARAMETERS).accept(this, Boolean.FALSE);
		node.getChildOrNull(FunctionExpression.BODY).accept(this, Boolean.FALSE);
		
		scope = scope.exit();

		return null;
	}

	@Override
	public Object visit(Identifier node, Object data) throws Throwable {
		Boolean obfuscate = (Boolean)data;
		if (obfuscate != null && obfuscate.booleanValue() == true) {
			obfuscate(node);
		}
		return null;
	}

	@Override
	public Object visit(SourceElements node, Object data) throws Throwable {
		
		for (int i=0; i<node.getNumChildren(); i++) {
			node.getChild(i).accept(ScopeProbeVisitor.getInstance(), scope);
		}
		
		return super.visit(node, Boolean.FALSE);
	}
	
	
	
	public Object visit(PrimaryExpression node, Object data) throws Throwable {
		for (Iterator i=node.iterator(); i.hasNext(); ) {
			((Node)i.next()).accept(this, Boolean.TRUE);
		}
		return null;
	}

	public Object visit(VariableDeclaration node, Object data) throws Throwable {
		for (Iterator i=node.iterator(); i.hasNext(); ) {
			((Node)i.next()).accept(this, Boolean.TRUE);
		}
		return null;
	}

	public Object visit(ContinueStatement node, Object data) throws Throwable {
		for (Iterator i=node.iterator(); i.hasNext(); ) {
			((Node)i.next()).accept(this, Boolean.TRUE);
		}
		return null;
	}
	
	public Object visit(BreakStatement node, Object data) throws Throwable {
		for (Iterator i=node.iterator(); i.hasNext(); ) {
			((Node)i.next()).accept(this, Boolean.TRUE);
		}
		return null;
	}

	public Object visit(LabelledStatement node, Object data) throws Throwable {
		for (Iterator i=node.iterator(); i.hasNext(); ) {
			((Node)i.next()).accept(this, Boolean.TRUE);
		}
		return null;
	}

	@Override
	public Object visit(ArgumentList node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(AssignmentExpression node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(Block node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(CallExpression node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(CaseBlock node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(CaseClause node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(ConditionalExpression node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(DebuggerStatement node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(DefaultClause node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(DoStatement node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(ElementAccess node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(ElementList node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(EmptyStatement node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(Expression node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(ExpressionStatement node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(ForInStatement node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(ForStatement node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(IfStatement node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(InfixExpression node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(Literal node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(MemberAccess node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(MemberExpression node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(NewExpression node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(Operator node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(PostfixExpression node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(Property node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(PropertyList node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(ReturnStatement node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(StatementList node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(SwitchStatement node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(ThrowStatement node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(TryStatement node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(UnaryExpression node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(VariableDeclarationList node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(VariableStatement node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(WhileStatement node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}

	@Override
	public Object visit(WithStatement node, Object data) throws Throwable {
		return super.visit(node, Boolean.FALSE);
	}
	
	
	
}
