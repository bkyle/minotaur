import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Iterator;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.Parser;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.io.IOUtils;

import parser.ASTNode;
import parser.JavascriptParser;
import parser.SimpleNode;

public class Main {

	private static final Options OPTIONS = new Options();
	
	static {
		/*
		 *  Commands
		 */
		OPTIONS.addOption("c", "check", false, "Only check (validate) the source.");
		OPTIONS.addOption("m", "minimize", false, "Minimize the source.");
		
		/*
		 *  Options
		 */
		OPTIONS.addOption("s", "source", true, "Provide literal source instead of code from a file.");
	}
	
	public static void main(String[] args) {
		
		CommandLine cl = null;
		try {
			Parser p = new PosixParser();
			cl = p.parse(OPTIONS, args);
		} catch (ParseException e) {
			System.err.println(e.getMessage());
			System.exit(2);
		}

		Command command = null;
		
		if (cl.hasOption('c')) {
			// There is no command for checking the source, the parse will either
			// work or it won't.
			command = new NullCommand();
		} else if (cl.hasOption('m')) {
			command = new MinimizeCommand();
		}
	
		if (cl.hasOption('s')) {
			try {
				String source = cl.getOptionValue('s');
				ByteArrayInputStream in = new ByteArrayInputStream(source.getBytes());
				JavascriptParser p = new JavascriptParser(in);
				SimpleNode n = p.Program();
				command.execute((ASTNode) n);
			} catch (parser.ParseException e) {
				System.err.println(e.getMessage());
				System.exit(1);
			}
		} else {
			if (cl.getArgList().size() > 0) {
				Iterator i = cl.getArgList().iterator();
				do {
				
					String name = (String) i.next();
					File file = new File(name);
					InputStream in = null;
					try {
						in = new FileInputStream(file);
						JavascriptParser p = new JavascriptParser(in);
						SimpleNode n = p.Program();
						command.execute((ASTNode) n);
					} catch (IOException e) {
						System.err.println(MessageFormat.format("Could not find {0}", name));
						System.exit(2);
					} catch (parser.ParseException e) {
						System.err.println(MessageFormat.format("Error parsing {0}\n{1}", name, e.getMessage()));
						System.exit(1);
					} finally {
						IOUtils.closeQuietly(in);
					}
				} while (i.hasNext());
				
			} else {
				JavascriptParser p = new JavascriptParser(System.in);
				try {
					SimpleNode n = p.Program();
					command.execute((ASTNode) n);
				} catch (parser.ParseException e) {
					System.err.println(e.getMessage());
					System.exit(1);
				}
			}
		}
		
		System.exit(0);
	}
}
