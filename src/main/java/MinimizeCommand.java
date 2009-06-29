import java.io.InputStream;

import org.apache.commons.cli.CommandLine;

import parser.ASTNode;
import parser.JavascriptParser;
import parser.Visitor;
import visitors.CompressingVisitor;


public class MinimizeCommand implements Command {

	CommandLine cl = null;
	
	public MinimizeCommand(CommandLine cl)
	{
		this.cl = cl;
	}
	
	public void execute(InputStream in) throws Throwable {
		JavascriptParser parser = new JavascriptParser(in);
		parser.setTracing(cl.hasOption("trace"));
		ASTNode node = (ASTNode) parser.Program();
		Visitor visitor = new CompressingVisitor(System.out);
		node.accept(visitor, null);
	}

}
