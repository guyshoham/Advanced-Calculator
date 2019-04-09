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
    protected double calculate(Expression expression) throws Exception {
        return -1 * expression.getLeft().evaluate();
    }

    @Override
    public double evaluate() throws Exception {
        return -1 * expression.evaluate();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Neg(expression.assign(var, expression));
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
    public String toString() {
        return "(-" + expression.toString() + ")";
    }
}
