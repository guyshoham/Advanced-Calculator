import java.util.ArrayList;
import java.util.List;
import java.util.Map;

abstract public class BinaryExpression extends BaseExpression implements Expression {

    private Expression left, right;

    public BinaryExpression(Expression left, Expression right) {
        super(left, right);
        this.left = left;
        this.right = right;
    }

    public List<String> getVariables() {
        List<String> newList = new ArrayList<>(left.getVariables());
        newList.addAll(right.getVariables());
        return newList;
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

    protected abstract double calculate(Expression expression) throws Exception;

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
        }
        return this;
    }

    public boolean isSidesEqual() throws Exception {
        if (left.getVariables().isEmpty() && right.getVariables().isEmpty()) {
            double leftValue = left.evaluate();
            double rightValue = right.evaluate();
            return leftValue == rightValue;
        }
        if (left.getClass().getTypeName().equals("Num") && right.getClass().getTypeName().equals("Num")) {
            return left.toString().equals(right.toString());
        }

        left = left.assign("x", new Num(1));
        right = right.assign("x", new Num(1));
        double leftValue = left.evaluate();
        double rightValue = right.evaluate();
        return leftValue == rightValue;
        /*if (left.getClass().getTypeName().equals("Var") && right.getClass().getTypeName().equals("Var")) {
            return left.toString().equals(right.toString());
        }
        if (left.getClass().getTypeName().equals("Var") && right.getClass().getTypeName().equals("Num")) {
            return left.toString().equals(right.toString());
        }
        if (left.getClass().getTypeName().equals("Num") && right.getClass().getTypeName().equals("Var")) {
            return left.toString().equals(right.toString());
        }*/
        //return left.isSidesEqual() && right.isSidesEqual();
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }
}
