import java.util.ArrayList;
import java.util.List;
import java.util.Map;

abstract public class UnaryExpression extends BaseExpression implements Expression {
    private Expression expression;

    public UnaryExpression(Expression expression) {
        super(expression);
        this.expression = expression;
    }

    public List<String> getVariables() {
        return new ArrayList<>(expression.getVariables());
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
            variables = expression.getVariables();
            for (String variable : variables) {
                if (variable.equals(var)) {
                    expression = expression.assign(var, expression);
                }
            }
        }
        return this;
    }

    public Expression getLeft() {
        return expression;
    }

    public Expression getRight() {
        return expression;
    }
}
