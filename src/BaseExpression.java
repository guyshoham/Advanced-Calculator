import java.util.ArrayList;
import java.util.List;
import java.util.Map;

abstract public class BaseExpression implements Expression {

    private Expression left, right;

    BaseExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    //Expression interface methods
    @Override
    public List<String> getVariables() {
        List<String> newList = new ArrayList<>(left.getVariables());
        if (right != null) {
            newList.addAll(right.getVariables());
        }
        return newList;
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
            if (right != null) {
                variables = right.getVariables();
                for (String variable : variables) {
                    if (variable.equals(var)) {
                        right = right.assign(var, expression);
                    }
                }
            }
        }
        return this;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        Expression expression = this;
        //assign all variables
        List<String> variables = this.getVariables();
        for (String variable : variables) {
            for (String s : assignment.keySet()) {
                if (variable.equals(s)) {
                    double d = assignment.get(s);
                    expression = expression.assign(variable, new Num(d));
                }
            }
        }

        //calculate expression without variables
        return this.calculate(expression);
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    protected abstract double calculate(Expression expression) throws Exception;

    private boolean isContainVar(String var, Expression e) {
        List<String> variables = e.getVariables();
        for (String variable : variables) {
            if (variable.equals(var)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNoVars() {
        return this.getVariables().isEmpty();
    }

    public Expression getExpNoVars() throws Exception {
        if (left.isNoVars() && !left.getClass().getTypeName().equals("Num")) {
            left = new Num(left.evaluate());
            this.getExpNoVars();
        }
        if (right.isNoVars() && !right.getClass().getTypeName().equals("Num")) {
            right = new Num(left.evaluate() + right.evaluate());
            this.getExpNoVars();
        }
        return this;
    }

    public boolean canBeSimplified(Expression e1, Expression e2) {
        return !e1.toString().equals(e2.toString());
    }

    @Override
    public String toString() {
        if (right == null) {
            return this.getSign() + left.toString() + ")";
        } else {
            return "(" + left.toString() + this.getSign() + right.toString() + ")";
        }
    }
}
