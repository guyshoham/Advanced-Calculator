import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Var implements Expression {

    private String var;

    /**
     * class constructor.
     *
     * @param variable variable
     */
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
    public Expression assign(String string, Expression expression) {
        if (this.var.equals(string)) {
            return expression;
        } else {
            return this;
        }
    }

    @Override
    public Expression differentiate(String string) {
        if (string.equals(this.var)) {
            return new Num(1);
        } else {
            return this;
        }
    }

    @Override
    public Expression simplify() {
        return this;
    }

    @Override
    public Expression getLeft() {
        return new Var(var);

    }

    @Override
    public Expression getRight() {
        return new Var(var);
    }

    @Override
    public boolean isNoVars() {
        return false;
    }

    @Override
    public String getSign() {
        return "";
    }

    @Override
    public String toString() {
        return var;
    }
}
