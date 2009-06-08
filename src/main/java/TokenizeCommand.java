import java.io.InputStream;

import parser.JavascriptParserTokenManager;
import parser.SimpleCharStream;
import parser.Token;

public class TokenizeCommand implements Command {

	public void execute(InputStream in) throws Exception {
		
		SimpleCharStream stream = new SimpleCharStream(in);
		JavascriptParserTokenManager tokenizer = new JavascriptParserTokenManager(stream);
		
		System.out.println("Position\tToken\tImage");
		
		Token token = tokenizer.getNextToken();
		while (token.kind != JavascriptParserTokenManager.EOF) {
			System.out.printf("%d:%d\t%s\t%s\n", 
					token.beginLine, 
					token.beginColumn, 
					JavascriptParserTokenManager.tokenImage[token.kind], 
					token.image);
			token = tokenizer.getNextToken();
		}
	}

}
