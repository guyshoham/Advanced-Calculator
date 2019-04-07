import java.util.List;
import java.util.Map;

public class Minus extends BinaryExpression implements Expression {
    private Expression left, right;

    public Minus(Expression left, Expression right) {
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
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return 0;
    }

    @Override
    public double evaluate() throws Exception {
        return 0;
    }

    @Override
    public List<String> getVariables() {
        return getVariables(left, right);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        List<String> variables;
        if (!this.isContainVar(var, this)) {
            return this;
        } else {
            variables = left.getVariables();
            for (String variable : variables) {
                if (variable.equals(var)) {
                    left = left.assign(var, expression);
                }
            }
            variables = right.getVariables();
            for (String variable : variables) {
                if (variable.equals(var)) {
                    right = right.assign(var, expression);
                }
            }
            return new Plus(left, right);
        }    }

    @Override
    public String toString() {
        return "(" + left.toString() + " - " + right.toString() + ")";
    }
}

