import java.util.*;

public class Plus extends BinaryExpression implements Expression {

    private Expression left, right;

    public Plus(Expression left, Expression right) {
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
    public double evaluate(Map<String, Double> assignment) throws Exception {
        List<String> variables = this.getVariables();
        for (String variable : variables) {
            for (String s : assignment.keySet()) {
                if (variable.equals(s)) {
                    double d = assignment.get(s);
                    this.assign(variable, new Num(d));
                }
            }
        }
        return left.evaluate() + right.evaluate();
    }

    @Override
    public double evaluate() throws Exception {
        if (left.getClass().getTypeName().equals("Num") && right.getClass().getTypeName().equals("Num")) {
            double num1 = left.evaluate();
            double num2 = right.evaluate();
            return num1 + num2;
        }
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
        }
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " + " + right.toString() + ")";
    }
}
