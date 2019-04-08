import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Num implements Expression {

    private double num;

    public Num(double num) {
        this.num = num;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) {
        return num;
    }

    @Override
    public double evaluate() {
        return num;
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }

    @Override
    public Expression differentiate(String var) {
        return new Num(0);
    }

    @Override
    public Expression simplify() {
        return this;
    }

    @Override
    public Expression getLeft() {
        return new Num(num);
    }

    @Override
    public Expression getRight() {
        return new Num(num);
    }

    @Override
    public boolean isSidesEqual() {
        return true;
    }

    @Override
    public boolean isNoVars() {
        return true;
    }

    @Override
    public String toString() {
        if (num % 1.0 == 0) {
            int retVal = (int) num;
            return String.valueOf(retVal);
        } else {
            double retVal = num;
            return String.valueOf(retVal);
        }
    }
}
