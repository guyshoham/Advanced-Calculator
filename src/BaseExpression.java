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
            for (String key : assignment.keySet()) {
                if (variable.equals(key)) {
                    double value = assignment.get(key);
                    expression = expression.assign(variable, new Num(value));
                }
            }
        }

        if (!expression.isNoVars()) {
            throw new Exception("one or more of the variables has not been assigned");
        }

        //evaluate expression without variables
        try {
            return expression.evaluate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

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
     * @return true if expression is not the same after simplification, means can be simplified more
     * @throws Exception exception
     */
    public boolean canBeSimplified(Expression expression) throws Exception {
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
