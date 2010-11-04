package visitors;

import java.util.Iterator;

import parser.ASTNode;
import parser.Scope;
import parser.ScopeRecord;
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

import org.apache.bcel.Constants;

import org.apache.bcel.generic.ArrayType;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.Type;

public class CodeGeneratingVisitor extends BaseVisitor implements Constants {
   

	private class State {
		
		ClassGen cg;
		ConstantPoolGen cp;
		MethodGen mg;
		InstructionList il;
		InstructionFactory factory;

		State (ClassGen cg, ConstantPoolGen cp, MethodGen mg, InstructionList il, InstructionFactory factory) {
			this.cg = cg;
			this.cp = cp;
			this.mg = mg;
			this.il = il;
			this.factory = factory;
		}

		State derive(InstructionList il) {
			return new State(this.cg, this.cp, this.mg, il, this.factory);
		}
	}



//	ClassGen cg = null;
//	ConstantPoolGen cp = null;
//	MethodGen mg = null;
//	InstructionList il = null;
//	InstructionFactory factory = null;

	public CodeGeneratingVisitor() {
//		cg = new ClassGen("MinotaurOutput", "java.lang.Object", "<generated>", ACC_PUBLIC | ACC_SUPER, null);
//		cp = cg.getConstantPool();
//		il = new InstructionList();
//		mg = new MethodGen(ACC_STATIC | ACC_PUBLIC, 
//						   Type.VOID, 
//						   new Type[]{new ArrayType(Type.STRING, 1) }, 
//						   new String[]{"args"},
//						   "main",
//						   "HelloWorld",
//						   il, cp);
//
//		factory = new InstructionFactory(cg);
//		il.append(factory.createFieldAccess("java.lang.System", "out", new ObjectType("java.io.PrintStream"), GETSTATIC));
//		il.append(new PUSH(cp, "hello, world!"));
//		il.append(factory.createInvoke("java.io.PrintStream", "print", Type.VOID, new Type[]{Type.STRING}, INVOKEVIRTUAL));
//		il.append(InstructionConstants.RETURN);
//
//		mg.setMaxStack();
//		cg.addMethod(mg.getMethod());
//		il.dispose();
//		cg.addEmptyConstructor(ACC_PUBLIC);
//
//		try {
//			cg.getJavaClass().dump("MinotaurOutput.class");
//		} catch (Throwable e) {
//			System.err.println(e.getMessage());
//			e.printStackTrace();
//			System.exit(1);
//		}
	}

	public Object visit(ArgumentList node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(AssignmentExpression node, Object data)
			throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(Block node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(BreakStatement node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(CallExpression node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(CaseBlock node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(CaseClause node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(CatchClause node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ConditionalExpression node, Object data)
			throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ContinueStatement node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(DebuggerStatement node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(DefaultClause node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(DoStatement node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ElementAccess node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ElementList node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(EmptyStatement node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(Expression node, Object data) throws Throwable {
		for (Iterator i=node.iterator(); i.hasNext(); ) {
			((ASTNode)i.next()).accept(this, data);
		}
		return null;
	}

	public Object visit(ExpressionStatement node, Object data) throws Throwable {
		node.getChild(ExpressionStatement.EXPRESSION).accept(this, data);
		return null;
	}

	public Object visit(ForInStatement node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ForStatement node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(FunctionDeclaration node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(FunctionExpression node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(Identifier node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(IfStatement node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(InfixExpression node, Object data) throws Throwable {
		State s = (State) data;
		node.getChild(InfixExpression.LHS).accept(this, data);
		node.getChild(InfixExpression.RHS).accept(this, data);
		
		String operator = (String) node.getChild(InfixExpression.OP).getValue();
		s.il.append(InstructionFactory.createBinaryOperation(operator, Type.LONG));

		return null;
	}

	public Object visit(LabelledStatement node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(Literal node, Object data) throws Throwable {
		State s = (State) data;
		if (node.getType() == Literal.NUMBER) {
			long l = Long.parseLong((String)node.getValue(), 10);
			s.il.append(new PUSH(s.cp, l));
		}

		return null;
	}

	public Object visit(MemberAccess node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(MemberExpression node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(NewExpression node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(Operator node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ParameterList node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(PostfixExpression node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(PrimaryExpression node, Object data) throws Throwable {
		node.getChild(PrimaryExpression.EXPRESSION).accept(this, data);
		return null;
	}

	public Object visit(Property node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(PropertyList node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ReturnStatement node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(SourceElements node, Object data) throws Throwable {

		ClassGen cg = new ClassGen("A", "java.lang.Object", "<generated>", ACC_PUBLIC | ACC_SUPER, null);
		ConstantPoolGen cp = cg.getConstantPool();
		InstructionList il = new InstructionList();
		MethodGen mg = new MethodGen(ACC_STATIC | ACC_PUBLIC, 
									 Type.VOID, 
									 new Type[]{new ArrayType(Type.STRING, 1) }, 
									 new String[]{"args"},
									 "main",
									 "HelloWorld",
									 il, cp);

		InstructionFactory factory = new InstructionFactory(cg);

		State s = new State(cg, cp, mg, il, factory);

		// Find all of the variable declarations
//		for (int i=0; i<node.getNumChildren(); i++) {
//			node.getChild(i).accept(ScopeProbeVisitor.getInstance(), scope);
//		}
		
		// Add local variables
//		for (Iterator<ScopeRecord> i=scope.iterator(); i.hasNext(); ) {
//			ScopeRecord record = i.next();
//		}

		// Generate the code
		for (int i=0; i<node.getNumChildren(); i++) {
			node.getChild(i).accept(this, s);
		}

		il.append(new org.apache.bcel.generic.L2I());
		il.append(factory.createInvoke("java.lang.System", "exit", Type.VOID, new Type[]{Type.INT}, INVOKESTATIC));
		il.append(InstructionConstants.RETURN);

		mg.setMaxStack();
		cg.addMethod(mg.getMethod());
		il.dispose();
		cg.addEmptyConstructor(ACC_PUBLIC);

		try {
			cg.getJavaClass().dump("A.class");
		} catch (Throwable e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}

		return null;
	}

	public Object visit(StatementList node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(SwitchStatement node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ThrowStatement node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(TryStatement node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(UnaryExpression node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(VariableDeclaration node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(VariableDeclarationList node, Object data)
			throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(VariableStatement node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(WhileStatement node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(WithStatement node, Object data) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	
}