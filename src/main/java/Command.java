import java.io.InputStream;

import parser.ASTNode;


public interface Command {

	public void execute(InputStream in) throws Exception;
	
}
