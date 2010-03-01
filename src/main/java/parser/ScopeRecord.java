package parser;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * A scope record is a record placed inside the scope for each identifier.
 * The record stores metadata about the scoped item so that it can be referred
 * to later.  For example, a list of positions within the AST where this identifier
 * is referenced could be created by storing a list as a property. 
 */
public class ScopeRecord {

	public static final String OWNER = "owner";
	public static final String NAME = "name";
	public static final String TYPE = "type";

	private Map<String, Object> data = new HashMap<String, Object>();

		
	/*
	 * Pre-declared (special case) record properties.
	 */
	
	private Scope owner = null;
	private String name = null;
	private String type = null;
	
	ScopeRecord(Scope scope, String name) {
		this.owner = scope;
		this.name = name;
	}

	public Scope getOwner() {
		return owner;
	}

	public void setOwner(Scope owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object get(String property) {
		if (property.equals(ScopeRecord.OWNER))
			return this.getOwner();
		else if (property.equals(ScopeRecord.NAME))
			return this.getName();
		else if (property.equals(ScopeRecord.TYPE))
			return this.getType();
		else 
			return this.data.get(property);
	}
	
	public void put(String property, Object value) {
		if (property.equals(ScopeRecord.OWNER) ||
				property.equals(ScopeRecord.NAME) ||
				property.equals(ScopeRecord.TYPE))
			throw new IllegalArgumentException(MessageFormat.format("{0} is a reserved property.", property));
		else
			this.data.put(property, value);
		
	}
	
}
