import java.io.InputStream;
import java.io.OutputStream;

public class NullCommand implements Command {

	public void execute(InputStream in, OutputStream out) throws Exception {
		// Do Nothing!
	}

}
