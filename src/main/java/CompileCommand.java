import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.cli.CommandLine;

import parser.ASTNode;
import parser.JavascriptParser;

import visitors.CodeGeneratingVisitor;


public class CompileCommand implements Command {

	CommandLine cl = null;
	
	public CompileCommand(CommandLine cl)
	{
		this.cl = cl;
	}
	
	public void execute(InputStream in, OutputStream out) throws Throwable {
		JavascriptParser parser = new JavascriptParser(in);
		parser.setTracing(cl.hasOption("trace"));
		ASTNode node  = (ASTNode) parser.Program();
		node.accept(new CodeGeneratingVisitor(), null);
	}

}
