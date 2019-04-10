public class Neg extends UnaryExpression implements Expression {

    private Expression expression;

    public Neg(Expression expression) {
        super(expression);
        this.expression = expression;
    }

    public Neg(String expression) {
        this(new Var(expression));
    }

    public Neg(double expression) {
        this(new Num(expression));
    }

    @Override
    protected double calculate(Expression e) throws Exception {
        return -1 * e.getLeft().evaluate();
    }

    @Override
    public double evaluate() throws Exception {
        return -1 * expression.evaluate();
    }

    @Override
    public Expression assign(String var, Expression e) {
        return new Neg(e.assign(var, e));
    }

    @Override
    public Expression differentiate(String var) {
        return new Neg(expression.differentiate(var));
    }

    @Override
    public Expression simplify() throws Exception {
        return new Neg(expression.simplify());
    }

    @Override
    public String getSign() {
        return "(-";
    }
}
