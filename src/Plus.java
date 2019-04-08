public class Plus extends BinaryExpression implements Expression {

    private Expression left, right;

    public Plus(Expression left, Expression right) {
        super(left, right);
        this.left = left;
        this.right = right;
    }

    public Plus(String left, Expression right) {
        this(new Var(left), right);
    }

    public Plus(Expression left, String right) {
        this(left, new Var(right));
    }

    public Plus(String left, String right) {
        this(new Var(left), new Var(right));
    }

    public Plus(String left, double right) {
        this(new Var(left), new Num(right));
    }

    public Plus(double left, String right) {
        this(new Num(left), new Var(right));
    }

    public Plus(double left, double right) {
        this(new Num(left), new Num(right));
    }

    @Override
    protected double calculate(Expression expression) throws Exception {
        return expression.getLeft().evaluate() + expression.getRight().evaluate();
    }

    public double evaluate() throws Exception {
        return left.evaluate() + right.evaluate();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression e1 = left.assign(var, expression);
        Expression e2 = right.assign(var, expression);
        return new Plus(e1, e2);
    }

    @Override
    public Expression differentiate(String var) {
        return new Plus(left.differentiate(var), right.differentiate(var));
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " + " + right.toString() + ")";
    }
}
