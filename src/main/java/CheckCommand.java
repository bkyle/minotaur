import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.cli.CommandLine;

import parser.JavascriptParser;


public class CheckCommand implements Command {

	CommandLine cl = null;
	
	public CheckCommand(CommandLine cl)
	{
		this.cl = cl;
	}
	
	public void execute(InputStream in, OutputStream out) throws Throwable {
		JavascriptParser parser = new JavascriptParser(in);
		parser.setTracing(cl.hasOption("trace"));
		parser.Program();
	}

}
