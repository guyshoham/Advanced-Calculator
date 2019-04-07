public class Minus extends BinaryExpression implements Expression {
    private Expression left, right;

    public Minus(Expression left, Expression right) {
        super(left, right);
        this.left = left;
        this.right = right;
    }

    public Minus(String left, Expression right) {
        this(new Var(left), right);
    }

    public Minus(Expression left, String right) {
        this(left, new Var(right));
    }

    public Minus(String left, String right) {
        this(new Var(left), new Var(right));
    }

    public Minus(String left, double right) {
        this(new Var(left), new Num(right));
    }

    public Minus(double left, String right) {
        this(new Num(left), new Var(right));
    }

    public Minus(double left, double right) {
        this(new Num(left), new Num(right));
    }

    @Override
    protected double calculate(Expression expression) throws Exception {
        return expression.getLeft().evaluate() - expression.getRight().evaluate();
    }

    public double evaluate() throws Exception {
        return left.evaluate() - right.evaluate();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression e1 = left.assign(var, expression);
        Expression e2 = right.assign(var, expression);
        return new Minus(e1, e2);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " - " + right.toString() + ")";
    }
}

