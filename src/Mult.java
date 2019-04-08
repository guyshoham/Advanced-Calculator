import java.util.Map;

public class Mult extends BinaryExpression implements Expression {
    private Expression left, right;

    public Mult(Expression left, Expression right) {
        super(left, right);
        this.left = left;
        this.right = right;
    }

    public Mult(String left, Expression right) {
        this(new Var(left), right);
    }

    public Mult(Expression left, String right) {
        this(left, new Var(right));
    }

    public Mult(String left, String right) {
        this(new Var(left), new Var(right));
    }

    public Mult(String left, double right) {
        this(new Var(left), new Num(right));
    }

    public Mult(double left, String right) {
        this(new Num(left), new Var(right));
    }

    public Mult(double left, double right) {
        this(new Num(left), new Num(right));
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return 0;
    }

    @Override
    protected double calculate(Expression expression) throws Exception {
        return expression.getLeft().evaluate() * expression.getRight().evaluate();
    }

    @Override
    public double evaluate() throws Exception {
        return left.evaluate() * right.evaluate();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression e1 = left.assign(var, expression);
        Expression e2 = right.assign(var, expression);
        return new Mult(e1, e2);
    }

    @Override
    public Expression differentiate(String var) {
        return new Plus(new Mult(left.differentiate(var), right), new Mult(left, right.differentiate(var)));
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " * " + right.toString() + ")";
    }
}
