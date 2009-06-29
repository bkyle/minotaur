package visitors;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import parser.Node;

public class ChecksumVisitor extends SimpleVisitor {

	MessageDigest digest = null;
	
	public ChecksumVisitor() {
		try {
			digest = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Object visit(Node node, Object data) throws Throwable {
		digest.update(Integer.valueOf(node.getClass().getName().hashCode()).toString().getBytes());
		if (node.getValue() != null)
			digest.update(node.getValue().toString().getBytes());
		
		for (int i=0; i<node.getNumChildren(); i++)
			node.getChildOrNull(i).accept(this, data);
		
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
