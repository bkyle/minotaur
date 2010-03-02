package visitors;

import parser.FunctionDeclaration;
import parser.FunctionExpression;
import parser.Identifier;
import parser.LabelledStatement;
import parser.Node;
import parser.ParameterList;
import parser.ReturnStatement;
import parser.Scope;
import parser.VariableDeclaration;

/**
 * Adds all of the identifiers within a scope to the passed Scope.
 */
public class ScopeProbeVisitor extends BaseWalkingVisitor {

	private static ScopeProbeVisitor instance = null;
	
	public static ScopeProbeVisitor getInstance() {
		if (ScopeProbeVisitor.instance == null)
			ScopeProbeVisitor.instance = new ScopeProbeVisitor();
		return ScopeProbeVisitor.instance;
	}
	
	@Override
	public Object visit(FunctionDeclaration node, Object data) throws Throwable {
		
		Scope scope = (Scope)data;
		scope.put(node.getChild(FunctionDeclaration.IDENTIFIER).getValue().toString(), "variable");
		
		// Don't descend into functions since they have their own scope.
		return null;
	}

	@Override
	public Object visit(FunctionExpression node, Object data) throws Throwable {
		Scope scope = (Scope)data;
		
		if (node.getChild(FunctionExpression.IDENTIFIER) != null)
			scope.put(node.getChild(FunctionExpression.IDENTIFIER).getValue().toString(), "variable");
		
		// Don't descend into functions since they have their own scope.
		return null;
	}

	@Override
	public Object visit(LabelledStatement node, Object data) throws Throwable {
		
		Scope scope = (Scope)data;
		scope.put(node.getChild(LabelledStatement.LABEL).getValue().toString(), "label");
		
		return super.visit(node, data);
	}

	@Override
	public Object visit(VariableDeclaration node, Object data) throws Throwable {
		
		Scope scope = (Scope)data;
		scope.put(node.getChild(VariableDeclaration.IDENTIFIER).getValue().toString(), "variable");
		
		return super.visit(node, data);
	}

	@Override
	public Object visit(ParameterList node, Object data) throws Throwable {
		
		Scope scope = (Scope)data;
		
		for (int i=0; i<node.getNumChildren(); i++) {
				scope.put(node.getChild(i).getValue().toString(), "variable");
		}
		
		return super.visit(node, data);
	}
	
	public Object visit(ReturnStatement node, Object data) throws Throwable {
		super.visit(node, data);
		return null;
	}

	
	
}
