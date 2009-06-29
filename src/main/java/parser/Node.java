package parser;

import java.util.ArrayList;
import java.util.List;

public abstract class Node implements Visitable {

	public static class Null extends Node {

		public static final Node T = new Null();
		
		public Object accept(Visitor v, Object data) {
			return data;
		}
		
	}
	
	Object value = null;

	public Object getValue()
	{
		return value;
	}
	
	protected void setValue(Object value)
	{
		this.value = value;
	}
	
	private Node[] children = null;

	public int getNumChildren()
	{
		if (children == null)
			return 0;
		else
			return children.length;
	}

	public Node getChild(int i)
	{
		if (children == null || i > children.length-1)
			return null;
		else
			return children[i];
	}
	
	public Node getChildOrNull(int i)
	{
		if (children == null || i > children.length-1) {
			return Null.T;
		} else {
			if (children[i] == null)
				return Null.T;
			else
				return children[i];
		}
	}
	
	protected void addChild(Node n, int i)
	{
		if (children == null) {
			children = new Node[i+1];
		} else if (i >= children.length) {
			Node[] tmp = new Node[i+1];
			System.arraycopy(children, 0, tmp, 0, children.length);
			children = tmp;
		}
		
		children[i] = n;
	}


	/*
	 * jjtree compat
	 */
	
	protected void jjtAddChild(Node n, int index)
	{
		addChild(n, index);
	}
	
	public void jjtOpen()
	{
		
	}
	
	public void jjtSetParent(Node node)
	{
		
	}
	
	public void jjtClose()
	{
		
	}
	
	
	
	
}
