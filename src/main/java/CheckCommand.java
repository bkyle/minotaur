import java.io.InputStream;

import org.apache.commons.cli.CommandLine;

import parser.ASTNode;
import parser.JavascriptParser;
import parser.Node;
import parser.Visitor;
import visitors.CompressingVisitor;


public class CheckCommand implements Command {

	CommandLine cl = null;
	
	public CheckCommand(CommandLine cl)
	{
		this.cl = cl;
	}
	
	public void execute(InputStream in) throws Throwable {
		JavascriptParser parser = new JavascriptParser(in);
		parser.setTracing(cl.hasOption("trace"));
		Node node = parser.Program();
		Visitor visitor = new CompressingVisitor(System.out);
		node.accept(visitor, null);
	}

}
