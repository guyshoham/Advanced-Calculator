import java.util.List;

abstract public class BaseExpression {

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
