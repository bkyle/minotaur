package parser;

public interface Visitable {

	public Object accept(Visitor v, Object data) throws Throwable;
	
}
