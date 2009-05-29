import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.Parser;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.io.IOUtils;

import parser.JavascriptParser;
import parser.JavascriptParserVisitor;
import parser.SimpleNode;

import visitors.CompressingVisitor;

public class Main {

	private static final Logger LOGGER = Logger.getLogger("Main");
	
	static {
		
		Handler handler = new ConsoleHandler();
		handler.setFormatter(new Formatter() {

			@Override
			public String format(LogRecord record) {
				return record.getMessage();
			}
			
		});
		
		LOGGER.addHandler(new ConsoleHandler());
	}
	
	private static final Options OPTIONS = new Options();
	
	static {
		OPTIONS.addOption("c", "check", false, "Only check (validate) the source.");
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

		
		if (cl.hasOption('c')) {
			
			JavascriptParserVisitor visitor = new CompressingVisitor(System.out);
			
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
						n.jjtAccept(visitor, null);
					} catch (IOException e) {
						LOGGER.warning(MessageFormat.format("Could not find {0}", name));
						continue;
					} catch (parser.ParseException e) {
						LOGGER.log(Level.ALL, MessageFormat.format("Error parsing {0}\n{1}", name, e.getMessage()));
						continue;
					} finally {
						IOUtils.closeQuietly(in);
					}
				} while (i.hasNext());
				
			} else {
				JavascriptParser p = new JavascriptParser(System.in);
				try {
					SimpleNode n = p.Program();
					n.jjtAccept(visitor, null);
				} catch (parser.ParseException e) {
					LOGGER.log(Level.ALL, e.getMessage());
				}
			}
		}
			
		
		
	}
}
