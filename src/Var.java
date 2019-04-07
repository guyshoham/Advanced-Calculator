import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Var implements Expression {

    private String var;

    public Var(String variable) {
        this.var = variable;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (assignment.containsKey(var)) {
            return assignment.get(var);
        } else {
            throw new Exception("cannot evaluate expression, too many variables.");
        }
    }

    @Override
    public double evaluate() throws Exception {
        throw new Exception("cannot evaluate expression, too many variables.");
    }

    @Override
    public List<String> getVariables() {
        List<String> list = new ArrayList<>();
        list.add(var);
        return list;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this.var.equals(var)) {
            return expression;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return var;
    }
}