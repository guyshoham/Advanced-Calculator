import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class BaseExpression.
 */
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

    /**
     * @return left expression.
     */
    public Expression getLeft() {
        return left;
    }

    /**
     * @return right expression.
     */
    public Expression getRight() {
        return right;
    }

    protected abstract double calculate(Expression expression) throws Exception;

    public boolean isNoVars() {
        return this.getVariables().isEmpty();
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
