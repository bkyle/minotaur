import java.io.InputStream;
import java.io.OutputStream;

import parser.ASTNode;


public interface Command {

	public void execute(InputStream in, OutputStream out) throws Throwable;
	
}
