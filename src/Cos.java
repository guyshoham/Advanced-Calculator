public class Cos extends UnaryExpression implements Expression {

    private Expression expression;

    public Cos(Expression expression) {
        super(expression);
        this.expression = expression;
    }

    public Cos(String expression) {
        this(new Var(expression));
    }

    public Cos(double expression) {
        this(new Num(expression));
    }

    @Override
    protected double calculate(Expression expression) throws Exception {
        return Math.cos(expression.getLeft().evaluate());
    }

    @Override
    public double evaluate() throws Exception {
        return Math.cos(expression.evaluate());
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Cos(expression.assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Sin(new Neg(expression.differentiate(var)));
    }

    @Override
    public Expression simplify() {
        return null;
    }

    @Override
    public String toString() {
        return "cos(" + expression.toString() + ")";
    }
}
