package visitors;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import parser.ASTNode;
import parser.JavascriptParserVisitor;
import parser.SimpleNode;

public class ChecksumVisitor implements JavascriptParserVisitor {

	MessageDigest digest = null;
	
	public ChecksumVisitor() {
		try {
			digest = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Object visit(SimpleNode node, Object data) {
		ASTNode astNode = (ASTNode) node;
		digest.update(Integer.valueOf(astNode.getId()).toString().getBytes());
		if (node.jjtGetValue() != null)
			digest.update(node.jjtGetValue().toString().getBytes());
		
		for (int i=0; i<node.jjtGetNumChildren(); i++)
			astNode.jjtGetChild(i).jjtAccept(this, data);
		
		return null;
	}
	
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		byte[] bytes = digest.digest();
		
		for (int i=0; i<bytes.length; i++) {
			buffer.append( Integer.toHexString((bytes[i] >>> 4) & 0x0F) );
			buffer.append( Integer.toHexString(bytes[i] & 0x0F) );
		}
		
		return buffer.toString();
	}
}
