import java.util.List;
import java.util.Map;

public class Div extends BinaryExpression implements Expression {
    private Expression left, right;

    public Div(Expression left, Expression right) {
        super(left, right);
        this.left = left;
        this.right = right;
    }

    public Div(String left, Expression right) {
        this(new Var(left), right);
    }

    public Div(Expression left, String right) {
        this(left, new Var(right));
    }

    public Div(String left, String right) {
        this(new Var(left), new Var(right));
    }

    public Div(String left, double right) {
        this(new Var(left), new Num(right));
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
    public String toString() {
        return "(" + left.toString() + " / " + right.toString() + ")";
    }
}
