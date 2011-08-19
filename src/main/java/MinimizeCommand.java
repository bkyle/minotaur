import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.commons.cli.CommandLine;

import parser.ASTNode;
import parser.JavascriptParser;
import visitors.ChecksumVisitor;
import visitors.CompressingVisitor;
import visitors.ObfuscatingVisitor;
import visitors.SemanticCheckingVisitor;


public class MinimizeCommand implements Command {

	CommandLine cl = null;
	
	public MinimizeCommand(CommandLine cl)
	{
		this.cl = cl;
	}
	
	
	public void execute(InputStream in, OutputStream out) throws Throwable {
		JavascriptParser parser = null;
		ASTNode node = null;

		if (this.cl.hasOption("checksum")) {
			OutputStreamWriter w = new OutputStreamWriter(out);
			w.write("Note: When combining --checksum and --minimize no obfuscation is performed.\n");
			w.flush();

			ChecksumVisitor v = null;
			
			parser = new JavascriptParser(in);
			parser.setTracing(cl.hasOption("trace"));
			node = (ASTNode) parser.Program();
			// Need to check semantics to build the scopes and such
			node.accept(new SemanticCheckingVisitor(), null);
			
			
			
			v = new ChecksumVisitor();
			node.accept(v, null);
			w.write("Original:  " + v.toString() + "\n");
			w.flush();
			
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			node.accept(new CompressingVisitor(baos), null);
			
			if (cl.hasOption("o")) {
				FileOutputStream o = null;
				try
				{
					o = new FileOutputStream(cl.getOptionValue("o"));
					o.write(baos.toByteArray());
				}
				finally
				{
					if (o != null)
						o.close();
				}
			}
			
			parser = new JavascriptParser(new ByteArrayInputStream(baos.toByteArray()));
			parser.setTracing(cl.hasOption("trace"));
			node = (ASTNode) parser.Program();
			v = new ChecksumVisitor();
			node.accept(v, null);
			w.write("Minimized: " + v.toString() + "\n");
			w.flush();
			
		} else {
			parser = new JavascriptParser(in);
			parser.setTracing(cl.hasOption("trace"));
			node = (ASTNode) parser.Program();
			node.accept(new SemanticCheckingVisitor(), null);
			node.accept(new ObfuscatingVisitor(), null);
			node.accept(new CompressingVisitor(out), null);
		}
	}
}
