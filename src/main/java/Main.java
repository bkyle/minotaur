import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Comparator;
import java.util.Iterator;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.Parser;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.io.IOUtils;

public class Main {

	private static final Options OPTIONS = new Options();
	
	static {
		/*
		 *  Commands
		 */
		OPTIONS.addOption(new CommandOption("c", "check", false, "[Command] Only check (validate) the source."));
		OPTIONS.addOption(new CommandOption("m", "minimize", false, "[Command] Minimize the source."));
		OPTIONS.addOption(new CommandOption(null, "checksum", false, "[Command] Calculate a checksum of the source."));
		OPTIONS.addOption(new CommandOption(null, "tokenize", false, "[Command] Print the output of the lexer."));
		OPTIONS.addOption(new CommandOption(null, "compile", false, "[Command] Compile the source into a Java class."));
		
		/*
		 *  Options
		 */
		OPTIONS.addOption("s", "source", true, "Provide literal source instead of code from a file.");
		OPTIONS.addOption(null, "trace", false, "Trace the parse of the source file.");
		OPTIONS.addOption("o", null, true, "Write intermediate results to a file.  Useful for debugging minimize.");
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
		
		if (cl.hasOption("check")) {
			// There is no command for checking the source, the parse will either
			// work or it won't.
			command = new CheckCommand(cl);
		} else if (cl.hasOption("minimize")) {
			// Check for minimize first since mimimize has special handling for the
			// --checksum command.
			command = new MinimizeCommand(cl);
		} else if (cl.hasOption("checksum")) {
			command = new ChecksumCommand(cl);
		} else if (cl.hasOption("tokenize")) {
			command = new TokenizeCommand();
		} else if (cl.hasOption("compile")) {
			command = new CompileCommand(cl);
		}
	
		if (command == null) {
			HelpFormatter help = new HelpFormatter();
			help.setOptionComparator(new Comparator() {
					public int compare(Object a, Object b) {
						if (a instanceof CommandOption && b instanceof CommandOption) {
							return 0;
						} else if (a instanceof CommandOption && !(b instanceof CommandOption)) {
							return -1;
						} else if (!(a instanceof CommandOption && b instanceof CommandOption)) {
							return 1;
						} else {
							return 0;
						}
					}

					public boolean equals(Object o) {
						return false;
					}
			});
			help.printHelp("minotaur [command] [options] [file]", OPTIONS);
			System.exit(0);
		}

		if (cl.hasOption('s')) {
			try {
				String source = cl.getOptionValue('s');
				ByteArrayInputStream in = new ByteArrayInputStream(source.getBytes());
				command.execute(in, System.out);
			} catch (Throwable t) {
				System.err.println(t.getMessage());
				t.printStackTrace();
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
						command.execute(in, System.out);
					} catch (IOException e) {
						System.err.println(MessageFormat.format("Could not find {0}", name));
						System.exit(2);
					} catch (parser.ParseException e) {
						System.err.println(MessageFormat.format("Error parsing {0}\n{1}", name, e.getMessage()));
						System.exit(1);
					} catch (Throwable t) {
						System.err.println(t.getMessage());
						t.printStackTrace();
						System.exit(3);
					} finally {
						IOUtils.closeQuietly(in);
					}
				} while (i.hasNext());
				
			} else {
				try {
					command.execute(System.in, System.out);
				} catch (Throwable t) {
					System.err.println(t.getMessage());
					System.exit(1);
				}
			}
		}
		
		System.exit(0);
	}
}
