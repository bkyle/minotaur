package parser;

public interface Visitor {

	public Object visit(ArgumentList node, Object data) throws Throwable;
	public Object visit(AssignmentExpression node, Object data) throws Throwable;
	public Object visit(Block node, Object data) throws Throwable;
	public Object visit(BreakStatement node, Object data) throws Throwable;
	public Object visit(CallExpression node, Object data) throws Throwable;
	public Object visit(CaseBlock node, Object data) throws Throwable;
	public Object visit(CaseClause node, Object data) throws Throwable;
	public Object visit(CatchClause node, Object data) throws Throwable;
	public Object visit(ConditionalExpression node, Object data) throws Throwable;
	public Object visit(ContinueStatement node, Object data) throws Throwable;
	public Object visit(DebuggerStatement node, Object data) throws Throwable;
	public Object visit(DefaultClause node, Object data) throws Throwable;
	public Object visit(DoStatement node, Object data) throws Throwable;
	public Object visit(ElementAccess node, Object data) throws Throwable;
	public Object visit(ElementList node, Object data) throws Throwable;
	public Object visit(EmptyStatement node, Object data) throws Throwable;
	public Object visit(Expression node, Object data) throws Throwable;
	public Object visit(ExpressionStatement node, Object data) throws Throwable;
	public Object visit(ForInStatement node, Object data) throws Throwable;
	public Object visit(ForStatement node, Object data) throws Throwable;
	public Object visit(FunctionDeclaration node, Object data) throws Throwable;
	public Object visit(FunctionExpression node, Object data) throws Throwable;
	public Object visit(Identifier node, Object data) throws Throwable;
	public Object visit(IfStatement node, Object data) throws Throwable;
	public Object visit(InfixExpression node, Object data) throws Throwable;
	public Object visit(LabelledStatement node, Object data) throws Throwable;
	public Object visit(Literal node, Object data) throws Throwable;
	public Object visit(MemberAccess node, Object data) throws Throwable;
	public Object visit(MemberExpression node, Object data) throws Throwable;
	public Object visit(NewExpression node, Object data) throws Throwable;
	public Object visit(Operator node, Object data) throws Throwable;
	public Object visit(ParameterList node, Object data) throws Throwable;
	public Object visit(PostfixExpression node, Object data) throws Throwable;
	public Object visit(PrimaryExpression node, Object data) throws Throwable;
	public Object visit(Property node, Object data) throws Throwable;
	public Object visit(PropertyList node, Object data) throws Throwable;
	public Object visit(ReturnStatement node, Object data) throws Throwable;
	public Object visit(SourceElements node, Object data) throws Throwable;
	public Object visit(StatementList node, Object data) throws Throwable;
	public Object visit(SwitchStatement node, Object data) throws Throwable;
	public Object visit(ThrowStatement node, Object data) throws Throwable;
	public Object visit(TryStatement node, Object data) throws Throwable;
	public Object visit(UnaryExpression node, Object data) throws Throwable;
	public Object visit(VariableDeclaration node, Object data) throws Throwable;
	public Object visit(VariableDeclarationList node, Object data) throws Throwable;
	public Object visit(VariableStatement node, Object data) throws Throwable;
	public Object visit(WhileStatement node, Object data) throws Throwable;
	public Object visit(WithStatement node, Object data) throws Throwable;
	
}
