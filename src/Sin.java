public class Sin extends UnaryExpression implements Expression {

    private Expression expression;

    public Sin(Expression expression) {
        super(expression);
        this.expression = expression;
    }

    public Sin(String expression) {
        this(new Var(expression));
    }

    public Sin(double expression) {
        this(new Num(expression));
    }

    @Override
    protected double calculate(Expression expression) throws Exception {
        return Math.sin(expression.getLeft().evaluate());
    }

    @Override
    public double evaluate() throws Exception {
        return Math.sin(expression.evaluate());
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Sin(expression.assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Mult(new Cos(expression), expression.differentiate(var));
    }

    @Override
    public Expression simplify() {
        return null;
    }

    @Override
    public String toString() {
        return "sin(" + expression.toString() + ")";
    }
}
