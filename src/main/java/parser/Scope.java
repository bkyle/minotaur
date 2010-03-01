package parser;

import java.util.Map;
import java.util.HashMap;

public class Scope {

	public static final int CREATE_MODE_NEVER = 0;
	public static final int CREATE_MODE_LOCAL = 1;
	public static final int CREATE_MODE_GLOBAL = 2;
	
	
	public static Scope newInstance() {
		return new Scope();
	}

	private Scope parent = null;
	
	private int depth = -1;

	private Map<String, Object> table = new HashMap<String, Object>();

	private Scope() {
		
	}

	private Scope(Scope parent) {
		this.parent = parent;
	}

	/**
	 * Creates a new scope from this one and returns it.
	 */
	public Scope enter() {
		return new Scope(this);
	}

	/**
	 * Returns the parent scope
	 */
	public Scope exit() {
		return parent;
	}

	public Object lookup(String name, int createMode) {
		
		if (table.containsKey(name)) {
			return table.get(name);
		} else {
			if (((createMode & CREATE_MODE_LOCAL) != 0) ||
					(this.parent == null && (createMode & CREATE_MODE_GLOBAL) != 0)) {
				ScopeRecord record = new ScopeRecord(this, name);
				this.table.put(name, record);
				return record;
			}
			
			if (parent != null) {
				return parent.lookup(name, createMode);
			} else {
				return null;
			}
		}
	}
	
	public Object put(String name, String type) {
		ScopeRecord record = new ScopeRecord(this, name);
		record.setType(type);
		this.table.put(name, record);
		return record;
	}
	
	public int getDepth() {
		if (this.depth == -1) {
			if (this.parent == null)
				depth = 0;
			else
				depth = this.parent.getDepth() + 1;
		}
		
		return depth;
	}


}