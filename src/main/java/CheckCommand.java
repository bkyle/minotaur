import java.io.InputStream;

import org.apache.commons.cli.CommandLine;

import parser.ASTNode;
import parser.JavascriptParser;
import parser.JavascriptParserVisitor;
import visitors.CompressingVisitor;


public class CheckCommand implements Command {

	CommandLine cl = null;
	
	public CheckCommand(CommandLine cl)
	{
		this.cl = cl;
	}
	
	public void execute(InputStream in) throws Exception {
		JavascriptParser parser = new JavascriptParser(in);
		parser.setTracing(cl.hasOption("trace"));
		ASTNode node = (ASTNode) parser.Program();
		JavascriptParserVisitor visitor = new CompressingVisitor(System.out);
		node.jjtAccept(visitor, null);
	}

}
