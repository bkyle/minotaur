import parser.ASTNode;
import visitors.ChecksumVisitor;


public class ChecksumCommand implements Command {

	public void execute(ASTNode node) {
		ChecksumVisitor v = new ChecksumVisitor();
		node.jjtAccept(v, null);
		System.out.println(v.toString());
	}

}
