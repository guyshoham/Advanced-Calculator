import java.util.Map;
import java.util.TreeMap;

public class Test {

    public static void main(String[] args) throws Exception {

        Expression e = new Plus("x", new Plus("y",6));
        Map<String, Double> assignment = new TreeMap<>();
        assignment.put("x", 8.0);
        assignment.put("y", 9.0);
        assignment.put("z", 0.0);

        System.out.println(e);
        System.out.println(e.evaluate(assignment));

    }
}
