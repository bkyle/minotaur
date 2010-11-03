import org.apache.commons.cli.Option;

public class CommandOption extends Option {

	public CommandOption(String opt, boolean hasArg, String description) {
		super(opt, hasArg, description);
	}

	public CommandOption(String opt, String description) {
		super(opt, description);
	}

	public CommandOption(String opt, String longOpt, boolean hasArg, String description) {
		super(opt, longOpt, hasArg, description);
	}

}