import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Num implements Expression {

    private double num;

    public Num(double num) {
        this.num = num;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return num;
    }

    @Override
    public double evaluate() throws Exception {
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
    public String toString() {
        return String.valueOf(num);
    }
}
