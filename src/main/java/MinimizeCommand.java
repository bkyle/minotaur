import parser.ASTNode;
import parser.JavascriptParserVisitor;
import visitors.CompressingVisitor;


public class MinimizeCommand implements Command {

	JavascriptParserVisitor visitor = new CompressingVisitor(System.out);

	public void execute(ASTNode node) {
		node.jjtAccept(visitor, null);
	}

}
