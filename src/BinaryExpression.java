import java.util.ArrayList;
import java.util.List;

abstract public class BinaryExpression extends BaseExpression {

    private Expression left, right;

    public List<String> getVariables(Expression e1, Expression e2) {
        List<String> newList = new ArrayList<>(e1.getVariables());
        newList.addAll(e2.getVariables());
        return newList;
    }


}
