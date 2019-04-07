import java.util.Map;
import java.util.TreeMap;

public class Test {

    public static void main(String[] args) throws Exception {
        Map<String, Double> assignment = new TreeMap<>();
        assignment.put("x", 8.0);
        assignment.put("y", 12.0);
        assignment.put("z", 2.0);

        Expression e = new Plus("x", new Div("y", 6));
        System.out.println(e);
        System.out.println(e.evaluate(assignment));

        e = new Pow("z", new Pow(2, 3));
        System.out.println(e);
        System.out.println(e.evaluate(assignment));

        e = new Log(2, 256);
        System.out.println(e);
        System.out.println(e.evaluate(assignment));

        e = new Plus(new Mult(new Num(2), new Var("x")), new Var("y"));
        System.out.println(e);
        System.out.println(e.evaluate(assignment));
    }
}
