package visitors;

import parser.CatchClause;
import parser.FunctionDeclaration;
import parser.FunctionExpression;
import parser.LabelledStatement;
import parser.ParameterList;
import parser.SourceElements;
import parser.VariableDeclaration;

/**
 * Checks the semantics of the AST and creates the scopes where appropriate.
 */
public class SemanticCheckingVisitor extends BaseWalkingVisitor {

	@Override
	public Object visit(FunctionDeclaration node, Object data) throws Throwable {
		
		node.getEnclosingScope().put(node.getChild(FunctionDeclaration.IDENTIFIER).getValue().toString(), "variable");

		node.createEnclosedScope();
		return super.visit(node, data);
	}

	@Override
	public Object visit(CatchClause node, Object data) throws Throwable {

		node.createEnclosedScope();
		node.getEnclosedScope().put(node.getChild(FunctionDeclaration.IDENTIFIER).getValue().toString(), "variable");

		return super.visit(node, data);
	}

	@Override
	public Object visit(SourceElements node, Object data) throws Throwable {
		if (node.isTopLevel())
			node.createEnclosedScope();
		
		return super.visit(node, data);
	}

	@Override
	public Object visit(FunctionExpression node, Object data) throws Throwable {
		if (node.getChild(FunctionExpression.IDENTIFIER) != null)
			node.getEnclosingScope().put(node.getChild(FunctionExpression.IDENTIFIER).getValue().toString(), "variable");
		
		node.createEnclosedScope();
		return super.visit(node, data);
	}

	@Override
	public Object visit(LabelledStatement node, Object data) throws Throwable {
		
		node.getEnclosingScope().put(node.getChild(LabelledStatement.LABEL).getValue().toString(), "label");
		
		return super.visit(node, data);
	}

	@Override
	public Object visit(VariableDeclaration node, Object data) throws Throwable {
		
		node.getEnclosingScope().put(node.getChild(VariableDeclaration.IDENTIFIER).getValue().toString(), "variable");
		
		return super.visit(node, data);
	}

	@Override
	public Object visit(ParameterList node, Object data) throws Throwable {

		for (int i=0; i<node.getNumChildren(); i++) {
				node.getEnclosingScope().put(node.getChild(i).getValue().toString(), "variable");
		}
		
		return super.visit(node, data);
	}
	
}
