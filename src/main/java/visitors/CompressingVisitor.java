package visitors;

import java.io.IOException;
import java.io.OutputStream;

import parser.ASTNode;
import parser.JavascriptParserTreeConstants;
import parser.JavascriptParserVisitor;  
import parser.SimpleNode;

public class CompressingVisitor implements JavascriptParserVisitor {

	OutputStream out = null;

	public CompressingVisitor(OutputStream out) {
		this.out = out;
	}

	public Object visit(SimpleNode node, Object data) {
		try {
			switch (((ASTNode) node).getId()) {
			case JavascriptParserTreeConstants.JJTIDENTIFIER:
				out.write(((String) node.jjtGetValue()).getBytes());
				break;
			case JavascriptParserTreeConstants.JJTLITERAL:
				node.jjtGetChild(0).jjtAccept(this, data);
				break;
			case JavascriptParserTreeConstants.JJTNULLLITERAL:
				out.write("null".getBytes());
				break;
			case JavascriptParserTreeConstants.JJTBOOLEANLITERAL:
				out.write(((String) node.jjtGetValue()).getBytes());
				break;
			case JavascriptParserTreeConstants.JJTNUMERICLITERAL:
				out.write(((String) node.jjtGetValue()).getBytes());
				break;
			case JavascriptParserTreeConstants.JJTSTRINGLITERAL:
				out.write(((String) node.jjtGetValue()).getBytes());
				break;
			case JavascriptParserTreeConstants.JJTREGEXLITERAL:
				out.write(((String) node.jjtGetValue()).getBytes());
				break;
				
			case JavascriptParserTreeConstants.JJTPRIMARYEXPRESSION:
				if (node.jjtGetNumChildren() == 0) {
					assert "this".equals(node.jjtGetValue());
					out.write(node.jjtGetValue().toString().getBytes());
				} else if (((ASTNode)node.jjtGetChild(0)).getId() == JavascriptParserTreeConstants.JJTEXPRESSION) {
					out.write("(".getBytes());
					node.jjtGetChild(0).jjtAccept(this, data);
					out.write(")".getBytes());
				} else {
					node.jjtGetChild(0).jjtAccept(this, data);
				}
				break;
				
			case JavascriptParserTreeConstants.JJTARRAYLITERAL:
				out.write("[".getBytes());
				if (node.jjtGetNumChildren() == 1)
					node.jjtGetChild(0).jjtAccept(this, data);
				out.write("]".getBytes());
				break;
				
			case JavascriptParserTreeConstants.JJTELEMENTLIST:
				for (int i=0; i<node.jjtGetNumChildren(); i++) {
					ASTNode child = (ASTNode) node.jjtGetChild(i);
					child.jjtAccept(this, data);
					if (i < node.jjtGetNumChildren()-1 && child.getId() != JavascriptParserTreeConstants.JJTELSION)
						out.write(",".getBytes());
				}
				break;
			case JavascriptParserTreeConstants.JJTELSION:
				out.write(",".getBytes());
				break;
				
			case JavascriptParserTreeConstants.JJTOBJECTLITERAL:
				out.write("{".getBytes());
				if (node.jjtGetNumChildren() == 1)
					node.jjtGetChild(0).jjtAccept(this, data);
				out.write("}".getBytes());
				break;
				
			case JavascriptParserTreeConstants.JJTPROPERTYNAMEANDVALUELIST:
				for (int i=0; i<node.jjtGetNumChildren(); i++) {
					node.jjtGetChild(i).jjtAccept(this, data);
					if (i < node.jjtGetNumChildren()-1)
						out.write(",".getBytes());
				}
				break;
				
			case JavascriptParserTreeConstants.JJTPROPERTYNAMEANDVALUE:
				node.jjtGetChild(0).jjtAccept(this, data);
				out.write(":".getBytes());
				node.jjtGetChild(1).jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTPROPERTYNAME:
				node.jjtGetChild(0).jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTMEMBEREXPRESSION:
				for (int i=0; i<node.jjtGetNumChildren(); i++)
					node.jjtGetChild(i).jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTMEMBEREXPRESSIONCONT:
			case JavascriptParserTreeConstants.JJTCALLEXPRESSIONCONT:
				for (int i=0; i<node.jjtGetNumChildren(); i++) {
					ASTNode child = (ASTNode) node.jjtGetChild(i);
					if (child.getId() == JavascriptParserTreeConstants.JJTEXPRESSION) {
						out.write("[".getBytes());
						child.jjtAccept(this, data);
						out.write("]".getBytes());
					} else if (child.getId() == JavascriptParserTreeConstants.JJTIDENTIFIER) {
						out.write(".".getBytes());
						child.jjtAccept(this, data);
					} else if (child.getId() == JavascriptParserTreeConstants.JJTARGUMENTS) {
						child.jjtAccept(this, data);
					}
				}
				break;
				
			case JavascriptParserTreeConstants.JJTNEWEXPRESSION:
				out.write("new ".getBytes());
				for (int i=0; i<node.jjtGetNumChildren(); i++)
					node.jjtGetChild(i).jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTCALLEXPRESSION:
				for (int i=0; i<node.jjtGetNumChildren(); i++)
					node.jjtGetChild(i).jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTARGUMENTS:
				out.write("(".getBytes());
				if (node.jjtGetNumChildren() == 1)
					node.jjtGetChild(0).jjtAccept(this, data);
				out.write(")".getBytes());
				break;
				
			case JavascriptParserTreeConstants.JJTARGUMENTLIST:
				for (int i=0; i<node.jjtGetNumChildren(); i++) {
					node.jjtGetChild(i).jjtAccept(this, data);
					if (i < node.jjtGetNumChildren()-1)
						out.write(",".getBytes());
				}
				break;
				
			case JavascriptParserTreeConstants.JJTLEFTHANDSIDEEXPRESSION:
				node.jjtGetChild(0).jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTPOSTFIXEXPRESSION:
				node.jjtGetChild(0).jjtAccept(this, data);
				if (node.jjtGetNumChildren() == 2)
					node.jjtGetChild(1).jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTPOSTFIXOPERATOR:
				out.write(node.jjtGetValue().toString().getBytes());
				break;
				
			case JavascriptParserTreeConstants.JJTUNARYEXPRESSION:
				if (node.jjtGetValue() != null) {
					String operator = (String) node.jjtGetValue();
					out.write(operator.getBytes());
					
					// Some of the prefix operators contain identifier-chars (delete, void, typeof)
					if (operator.length() > 2)
						out.write(" ".getBytes());
				}
				node.jjtGetChild(0).jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTMULTIPLICATIVEEXPRESSION:
				for (int i=0; i<node.jjtGetNumChildren(); i++) {
					node.jjtGetChild(i).jjtAccept(this, data);
					
					if (i < node.jjtGetNumChildren()-1)
						out.write(node.jjtGetValue().toString().getBytes());
				}
				break;

			case JavascriptParserTreeConstants.JJTADDITIVEEXPRESSION:
				for (int i=0; i<node.jjtGetNumChildren(); i++) {
					node.jjtGetChild(i).jjtAccept(this, data);
					
					if (i < node.jjtGetNumChildren()-1)
						out.write(node.jjtGetValue().toString().getBytes());
				}
				break;

			case JavascriptParserTreeConstants.JJTSHIFTEXPRESSION:
				for (int i=0; i<node.jjtGetNumChildren(); i++) {
					node.jjtGetChild(i).jjtAccept(this, data);
					
					if (i < node.jjtGetNumChildren()-1)
						out.write(node.jjtGetValue().toString().getBytes());
				}
				break;

			case JavascriptParserTreeConstants.JJTRELATIONALEXPRESSION:
			case JavascriptParserTreeConstants.JJTRELATIONALEXPRESSIONNOIN:
				for (int i=0; i<node.jjtGetNumChildren(); i++) {
					node.jjtGetChild(i).jjtAccept(this, data);
					
					if (i < node.jjtGetNumChildren()-1) {
						// Some of the prefix operators contain identifier-chars (in, instanceof)
						if (node.jjtGetValue().toString().length() > 2) {
							out.write(" ".getBytes());
							out.write(node.jjtGetValue().toString().getBytes());
							out.write(" ".getBytes());
						} else {
							out.write(node.jjtGetValue().toString().getBytes());
						}
					}
				}
				break;

			case JavascriptParserTreeConstants.JJTEQUALITYEXPRESSION:
			case JavascriptParserTreeConstants.JJTEQUALITYEXPRESSIONNOIN:
				for (int i=0; i<node.jjtGetNumChildren(); i++) {
					node.jjtGetChild(i).jjtAccept(this, data);
					
					if (i < node.jjtGetNumChildren()-1)
						out.write(node.jjtGetValue().toString().getBytes());
				}
				break;

			case JavascriptParserTreeConstants.JJTBITWISEANDEXPRESSION:
			case JavascriptParserTreeConstants.JJTBITWISEANDEXPRESSIONNOIN:
				for (int i=0; i<node.jjtGetNumChildren(); i++) {
					node.jjtGetChild(i).jjtAccept(this, data);
					
					if (i < node.jjtGetNumChildren()-1)
						out.write("&".getBytes());
				}
				break;

			case JavascriptParserTreeConstants.JJTBITWISEXOREXPRESSION:
			case JavascriptParserTreeConstants.JJTBITWISEXOREXPRESSIONNOIN:
				for (int i=0; i<node.jjtGetNumChildren(); i++) {
					node.jjtGetChild(i).jjtAccept(this, data);
					
					if (i < node.jjtGetNumChildren()-1)
						out.write("^".getBytes());
				}
				break;

			case JavascriptParserTreeConstants.JJTBITWISEOREXPRESSION:
			case JavascriptParserTreeConstants.JJTBITWISEOREXPRESSIONNOIN:
				for (int i=0; i<node.jjtGetNumChildren(); i++) {
					node.jjtGetChild(i).jjtAccept(this, data);
					
					if (i < node.jjtGetNumChildren()-1)
						out.write("|".getBytes());
				}
				break;

			case JavascriptParserTreeConstants.JJTLOGICALANDEXPRESSION:
			case JavascriptParserTreeConstants.JJTLOGICALANDEXPRESSIONNOIN:
				for (int i=0; i<node.jjtGetNumChildren(); i++) {
					node.jjtGetChild(i).jjtAccept(this, data);
					
					if (i < node.jjtGetNumChildren()-1)
						out.write("&&".getBytes());
				}
				break;

			case JavascriptParserTreeConstants.JJTLOGICALOREXPRESSION:
			case JavascriptParserTreeConstants.JJTLOGICALOREXPRESSIONNOIN:
				for (int i=0; i<node.jjtGetNumChildren(); i++) {
					node.jjtGetChild(i).jjtAccept(this, data);
					
					if (i < node.jjtGetNumChildren()-1)
						out.write("||".getBytes());
				}
				break;

			case JavascriptParserTreeConstants.JJTCONDITIONALEXPRESSION:
			case JavascriptParserTreeConstants.JJTCONDITIONALEXPRESSIONNOIN:
				SimpleNode expression = (SimpleNode) node.jjtGetChild(0);
				SimpleNode trueExpression = null;
				SimpleNode falseExpression = null;
				
				expression.jjtAccept(this, data);
				
				if (node.jjtGetNumChildren() == 3) {
					trueExpression = (SimpleNode) node.jjtGetChild(1);
					falseExpression = (SimpleNode) node.jjtGetChild(2);
					
					out.write("?".getBytes());
					trueExpression.jjtAccept(this, data);
					out.write(":".getBytes());
					falseExpression.jjtAccept(this, data);
				}
				break;
				
			case JavascriptParserTreeConstants.JJTASSIGNMENTEXPRESSION:
			case JavascriptParserTreeConstants.JJTASSIGNMENTEXPRESSIONNOIN:
				for (int i=0; i<node.jjtGetNumChildren(); i++)
					node.jjtGetChild(i).jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTASSIGNMENTOPERATOR:
				out.write(node.jjtGetValue().toString().getBytes());
				break;
				
			case JavascriptParserTreeConstants.JJTEXPRESSION:
			case JavascriptParserTreeConstants.JJTEXPRESSIONNOIN:
				for (int i=0; i<node.jjtGetNumChildren(); i++) {
					node.jjtGetChild(i).jjtAccept(this, data);
					if (i < node.jjtGetNumChildren()-1)
						out.write(",".getBytes());
				}
				break;
				
			case JavascriptParserTreeConstants.JJTSTATEMENT:
				node.jjtGetChild(0).jjtAccept(this, data);
				break;
			case JavascriptParserTreeConstants.JJTBLOCK:
				out.write("{".getBytes());
				for (int i=0; i<node.jjtGetNumChildren(); i++)
					node.jjtGetChild(i).jjtAccept(this, data);
				out.write("}".getBytes());
				break;
				
			case JavascriptParserTreeConstants.JJTSTATEMENTLIST:
				for (int i=0; i<node.jjtGetNumChildren(); i++)
					node.jjtGetChild(i).jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTVARIABLESTATEMENT:
				out.write("var ".getBytes());
				node.jjtGetChild(0).jjtAccept(this, data);
				out.write(";".getBytes());
				break;
				
			case JavascriptParserTreeConstants.JJTVARIABLEDECLARATIONLIST:
			case JavascriptParserTreeConstants.JJTVARIABLEDECLARATIONLISTNOIN:
				for (int i=0; i<node.jjtGetNumChildren(); i++) {
					node.jjtGetChild(i).jjtAccept(this, data);
					if (i < node.jjtGetNumChildren()-1)
						out.write(",".getBytes());
				}
				break;
				
			case JavascriptParserTreeConstants.JJTVARIABLEDECLARATION:
			case JavascriptParserTreeConstants.JJTVARIABLEDECLARATIONNOIN:
				for (int i=0; i<node.jjtGetNumChildren(); i++)
					node.jjtGetChild(i).jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTINITIALIZER:
			case JavascriptParserTreeConstants.JJTINITIALIZERNOIN:
				out.write("=".getBytes());
				node.jjtGetChild(0).jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTEMPTYSTATEMENT:
				out.write(";".getBytes());
				break;
				
			case JavascriptParserTreeConstants.JJTEXPRESSIONSTATEMENT:
				for (int i=0; i<node.jjtGetNumChildren(); i++)
					node.jjtGetChild(i).jjtAccept(this, data);
					out.write(";".getBytes());
				break;
				
			case JavascriptParserTreeConstants.JJTIFSTATEMENT:
				SimpleNode expression1 = (SimpleNode) node.jjtGetChild(0);
				SimpleNode bodyStatement = (SimpleNode) node.jjtGetChild(1);
				SimpleNode elseStatement = null;
				
				if (node.jjtGetNumChildren() == 3)
					elseStatement = (SimpleNode) node.jjtGetChild(2);
				
				out.write("if(".getBytes());
				expression1.jjtAccept(this, data);
				out.write(")".getBytes());
				bodyStatement.jjtAccept(this, data);
				
				if (elseStatement != null) {
					out.write("else ".getBytes());
					elseStatement.jjtAccept(this, data);
				}
				break;
				
			case JavascriptParserTreeConstants.JJTITERATIONSTATEMENT:
				node.jjtGetChild(0).jjtAccept(this, data);
				break;
			
			case JavascriptParserTreeConstants.JJTDOSTATEMENT:
				out.write("do".getBytes());
				node.jjtGetChild(0).jjtAccept(this, data);
				out.write("while(".getBytes());
				node.jjtGetChild(1).jjtAccept(this, data);
				out.write(");".getBytes());
				break;
				
			case JavascriptParserTreeConstants.JJTWHILESTATEMENT:
				out.write("while(".getBytes());
				node.jjtGetChild(0).jjtAccept(this, data);
				out.write(")".getBytes());
				node.jjtGetChild(1).jjtAccept(this, data);
				break;

			case JavascriptParserTreeConstants.JJTFORSTATEMENT:
				ASTNode initializer = null;
				ASTNode condition = null;
				ASTNode step = null;
				ASTNode statement = null;
				
				for (int i=0; i<node.jjtGetNumChildren(); i++) {
					ASTNode child = (ASTNode) node.jjtGetChild(i);
					if (JavascriptParserTreeConstants.JJTFORSTATEMENTINITIALIZER == child.getId())
						initializer = child;
					else if (JavascriptParserTreeConstants.JJTFORSTATEMENTCONDITION == child.getId())
						condition = child;
					else if (JavascriptParserTreeConstants.JJTFORSTATEMENTSTEP == child.getId())
						step = child;
					else if (JavascriptParserTreeConstants.JJTSTATEMENT == child.getId())
						statement = child;
				}
				
				out.write("for(".getBytes());
				if (initializer != null)
					initializer.jjtAccept(this, data);
				out.write(";".getBytes());
				if (condition != null)
					condition.jjtAccept(this, data);
				out.write(";".getBytes());
				if (step != null)
					step.jjtAccept(this, data);
				out.write(")".getBytes());
				statement.jjtAccept(this, data);
				break;

			case JavascriptParserTreeConstants.JJTFORSTATEMENTINITIALIZER:
				if (node.jjtGetValue() != null) {
					out.write(node.jjtGetValue().toString().getBytes());
					out.write(" ".getBytes());
				}
				node.jjtGetChild(0).jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTFORSTATEMENTCONDITION:
				node.jjtGetChild(0).jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTFORSTATEMENTSTEP:
				node.jjtGetChild(0).jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTFORINSTATEMENT:
				out.write("for(".getBytes());
				node.jjtGetChild(0).jjtAccept(this, data);
				out.write(" in ".getBytes());
				node.jjtGetChild(1).jjtAccept(this, data);
				out.write(")".getBytes());
				node.jjtGetChild(2).jjtAccept(this, data);
				break;

			case JavascriptParserTreeConstants.JJTFORINSTATEMENTINITIALIZER:
				if (node.jjtGetValue() != null)
					out.write(node.jjtGetValue().toString().getBytes());
				node.jjtGetChild(0).jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTCONTINUESTATEMENT:
				out.write("continue".getBytes());
				
				if (node.jjtGetNumChildren() == 1) {
					out.write(" ".getBytes());
					node.jjtGetChild(0).jjtAccept(this, data);
				}
				
				out.write(";".getBytes());
				break;
				
			case JavascriptParserTreeConstants.JJTBREAKSTATEMENT:
				out.write("break;".getBytes());
				break;
				
			case JavascriptParserTreeConstants.JJTRETURNSTATEMENT:
				out.write("return".getBytes());
				
				if (node.jjtGetNumChildren() == 1) {
					out.write(" ".getBytes());
					node.jjtGetChild(0).jjtAccept(this, data);
				}
				
				out.write(";".getBytes());
				break;
				
			case JavascriptParserTreeConstants.JJTWITHSTATEMENT:
				out.write("with(".getBytes());
				node.jjtGetChild(0).jjtAccept(this, data);
				out.write(")".getBytes());
				node.jjtGetChild(1).jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTSWITCHSTATEMENT:
				out.write("switch(".getBytes());
				node.jjtGetChild(0).jjtAccept(this, data);
				out.write(")".getBytes());
				node.jjtGetChild(1).jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTCASEBLOCK:
				out.write("{".getBytes());
				for (int i=0; i<node.jjtGetNumChildren(); i++)
					node.jjtGetChild(i).jjtAccept(this, data);
				out.write("}".getBytes());
				break;
				
			case JavascriptParserTreeConstants.JJTCASECLAUSES:
				for (int i=0; i<node.jjtGetNumChildren(); i++)
					node.jjtGetChild(i).jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTCASECLAUSE:
				out.write("case ".getBytes());
				node.jjtGetChild(0).jjtAccept(this, data);
				out.write(":".getBytes());
				
				if (node.jjtGetNumChildren() == 2)
					node.jjtGetChild(1).jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTDEFAULTCLAUSE:
				out.write("default:".getBytes());
				
				if (node.jjtGetNumChildren() == 1)
					node.jjtGetChild(0).jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTLABELLEDSTATEMENT:
				SimpleNode identifier = (SimpleNode) node.jjtGetChild(0);
				SimpleNode statement1 = (SimpleNode) node.jjtGetChild(1);
				
				identifier.jjtAccept(this, data);
				out.write(":".getBytes());
				statement1.jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTTHROWSTATEMENT:
				
				out.write("throw ".getBytes());
				node.jjtGetChild(0).jjtAccept(this, data);
				out.write(";".getBytes());
				break;
				
			case JavascriptParserTreeConstants.JJTTRYSTATEMENT:
				out.write("try".getBytes());
				for (int i=0; i<node.jjtGetNumChildren(); i++)
					node.jjtGetChild(i).jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTCATCH:
				SimpleNode identifier1 = (SimpleNode) node.jjtGetChild(0);
				SimpleNode block = (SimpleNode) node.jjtGetChild(1);
				
				out.write("catch(".getBytes());
				identifier1.jjtAccept(this, data);
				out.write(")".getBytes());
				block.jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTFINALLY:
				SimpleNode block1 = (SimpleNode) node.jjtGetChild(0);
				
				out.write("finally".getBytes());
				block1.jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTDEBUGGERSTATEMENT:
				out.write("debugger;".getBytes());
				break;
				
			case JavascriptParserTreeConstants.JJTFUNCTIONDECLARATION:
				SimpleNode name = null;
				SimpleNode parameters = null;
				SimpleNode body = null;
				
				for (int i=0; i<node.jjtGetNumChildren(); i++) {
					ASTNode child = (ASTNode) node.jjtGetChild(i);
					if (JavascriptParserTreeConstants.JJTIDENTIFIER == child.getId())
						name = child;
					else if (JavascriptParserTreeConstants.JJTFORMALPARAMETERLIST == child.getId())
						parameters = child;
					else if (JavascriptParserTreeConstants.JJTFUNCTIONBODY == child.getId())
						body = child;
				}
				
				out.write("function ".getBytes());
				name.jjtAccept(this, data);
				out.write("(".getBytes());
				if (parameters != null)
					parameters.jjtAccept(this, data);
				out.write("){".getBytes());
				if (body != null)
					body.jjtAccept(this, data);
				out.write("}".getBytes());
				break;
				
			case JavascriptParserTreeConstants.JJTFUNCTIONEXPRESSION:
				SimpleNode name1 = null;
				SimpleNode parameters1 = null;
				SimpleNode body1 = null;
				
				for (int i=0; i<node.jjtGetNumChildren(); i++) {
					ASTNode child = (ASTNode) node.jjtGetChild(i);
					if (JavascriptParserTreeConstants.JJTIDENTIFIER == child.getId())
						name1 = child;
					else if (JavascriptParserTreeConstants.JJTFORMALPARAMETERLIST == child.getId())
						parameters1 = child;
					else if (JavascriptParserTreeConstants.JJTFUNCTIONBODY == child.getId())
						body1 = child;
				}
				
				out.write("function".getBytes());
				if (name1 != null) {
					out.write(" ".getBytes());
					name1.jjtAccept(this, data);
				}
				out.write("(".getBytes());
				if (parameters1 != null)
					parameters1.jjtAccept(this, data);
				out.write("){".getBytes());
				if (body1 != null)
					body1.jjtAccept(this, data);
				out.write("}".getBytes());
				break;
				
			case JavascriptParserTreeConstants.JJTFORMALPARAMETERLIST:
				for (int i=0; i<node.jjtGetNumChildren(); i++) {
					node.jjtGetChild(i).jjtAccept(this, data);
					if (i < node.jjtGetNumChildren()-1)
						out.write(",".getBytes());
				}
				break;
				
			case JavascriptParserTreeConstants.JJTFUNCTIONBODY:
				for (int i=0; i<node.jjtGetNumChildren(); i++)
					node.jjtGetChild(i).jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTPROGRAM:
				for (int i = 0; i < node.jjtGetNumChildren(); i++)
					node.jjtGetChild(i).jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTSOURCEELEMENTS:
				for (int i = 0; i < node.jjtGetNumChildren(); i++)
					node.jjtGetChild(i).jjtAccept(this, data);
				break;
				
			case JavascriptParserTreeConstants.JJTSOURCEELEMENT:
				for (int i = 0; i < node.jjtGetNumChildren(); i++)
					node.jjtGetChild(i).jjtAccept(this, data);
				break;
				
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
}
