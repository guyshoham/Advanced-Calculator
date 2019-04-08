public class Div extends BinaryExpression implements Expression {
    private Expression left, right;

    public Div(Expression left, Expression right) {
        super(left, right);
        this.left = left;
        this.right = right;
    }

    public Div(Expression left, String right) {
        this(left, new Var(right));
    }

    public Div(Expression left, double right) {
        this(left, new Num(right));
    }

    public Div(String left, Expression right) {
        this(new Var(left), right);
    }

    public Div(String left, String right) {
        this(new Var(left), new Var(right));
    }

    public Div(String left, double right) {
        this(new Var(left), new Num(right));
    }

    public Div(double left, Expression right) {
        this(new Num(left), right);
    }

    public Div(double left, String right) {
        this(new Num(left), new Var(right));
    }

    public Div(double left, double right) {
        this(new Num(left), new Num(right));
    }

    @Override
    protected double calculate(Expression expression) throws Exception {
        if (expression.getRight().evaluate() == 0) {
            throw new Exception("cannot divide by zero.");
        }
        return expression.getLeft().evaluate() / expression.getRight().evaluate();
    }

    @Override
    public double evaluate() throws Exception {
        if (right.evaluate() == 0) {
            throw new Exception("cannot divide by zero.");
        }
        return left.evaluate() / right.evaluate();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression e1 = left.assign(var, expression);
        Expression e2 = right.assign(var, expression);
        return new Div(e1, e2);
    }

    @Override
    public Expression differentiate(String var) {
        return new Div(
                new Minus(
                        new Mult(left.differentiate(var), right),
                        new Mult(left, right.differentiate(var))),
                new Pow(right, 2));
    }

    @Override
    public Expression simplify() {
        return null;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " / " + right.toString() + ")";
    }
}
