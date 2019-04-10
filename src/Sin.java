public class Sin extends UnaryExpression implements Expression {

    private Expression expression;

    /**
     * class constructor.
     *
     * @param expression expression
     */
    public Sin(Expression expression) {
        super(expression);
        this.expression = expression;
    }

    /**
     * class constructor.
     *
     * @param expression expression
     */
    public Sin(String expression) {
        this(new Var(expression));
    }

    /**
     * class constructor.
     *
     * @param expression expression
     */
    public Sin(double expression) {
        this(new Num(expression));
    }

    @Override
    protected double calculate(Expression e) throws Exception {
        return Math.sin(e.getLeft().evaluate());
    }

    @Override
    public double evaluate() throws Exception {
        return Math.sin(expression.evaluate());
    }

    @Override
    public Expression assign(String var, Expression e) {
        return new Sin(e.assign(var, e));
    }

    @Override
    public Expression differentiate(String var) {
        return new Mult(new Cos(expression), expression.differentiate(var));
    }

    @Override
    public Expression simplify() throws Exception {
        return new Sin(expression.simplify());
    }

    @Override
    public String getSign() {
        return "sin(";
    }
}
