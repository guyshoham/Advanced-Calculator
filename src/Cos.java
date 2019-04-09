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
    protected double calculate(Expression e) throws Exception {
        return Math.cos(e.getLeft().evaluate());
    }

    @Override
    public double evaluate() throws Exception {
        return Math.cos(expression.evaluate());
    }

    @Override
    public Expression assign(String var, Expression e) {
        return new Cos(e.assign(var, e));
    }

    @Override
    public Expression differentiate(String var) {
        return new Mult(new Neg(new Sin(expression)), expression.differentiate(var));
    }

    @Override
    public Expression simplify() throws Exception {
        return new Cos(expression.simplify());
    }

    @Override
    public String toString() {
        return "cos(" + expression.toString() + ")";
    }
}
