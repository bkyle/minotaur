import java.io.InputStream;

import org.apache.commons.cli.CommandLine;

import parser.ASTNode;
import parser.JavascriptParser;
import parser.Node;
import parser.ParseException;
import visitors.ChecksumVisitor;


public class ChecksumCommand implements Command {

	CommandLine cl = null;
	
	public ChecksumCommand(CommandLine cl)
	{
		this.cl = cl;
	}
	
	public void execute(InputStream in) throws Throwable {
		JavascriptParser parser = new JavascriptParser(in);
		parser.setTracing(cl.hasOption("trace"));
		Node node = parser.Program();
		ChecksumVisitor v = new ChecksumVisitor();
		node.accept(v, null);
		System.out.println(v.toString());
	}

}
