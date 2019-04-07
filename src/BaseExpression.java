import java.util.List;

abstract public class BaseExpression {

    private Expression left, right;

    public BaseExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public BaseExpression(Expression left) {
        this.left = left;
    }

    public boolean isContainVar(String var, Expression e) {
        List<String> variables = e.getVariables();
        for (String variable : variables) {
            if (variable.equals(var)) {
                return true;
            }
        }
        return false;
    }
}
