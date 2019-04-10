import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class BaseExpression.
 */
abstract class BaseExpression implements Expression {

    private Expression left, right;

    /**
     * class constructor.
     *
     * @param left  expression
     * @param right expression
     */
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

    /**
     * @param expression expression
     * @return value of expression
     * @throws Exception exception
     */
    protected abstract double calculate(Expression expression) throws Exception;

    /**
     * @return true if there is no vars in expression, false otherwise.
     */
    public boolean isNoVars() {
        return this.getVariables().isEmpty();
    }

    /**
     * this method simplify the expression and checks if the toString value is the same.
     * if so, that means the expression can not be simplified anymore
     *
     * @param expression first expression
     * @param e2         second expression. need to remove
     * @return true if expression is not the same after simplification, means can be simplified more
     * @throws Exception exception
     */
    //todo: remove e2
    public boolean canBeSimplified(Expression expression, Expression e2) throws Exception {
        return !expression.toString().equals(expression.simplify().toString());
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
