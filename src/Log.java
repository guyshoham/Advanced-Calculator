import java.util.List;
import java.util.Map;

public class Log extends BinaryExpression implements Expression {
    private Expression base, num;

    public Log(Expression base, Expression num) {
        this.base = base;
        this.num = num;
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
        return null;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return null;
    }
}
