import java.util.Map;
import java.util.TreeMap;

public class Test {

    public static void main(String[] args) throws Exception {
        Map<String, Double> assignment = new TreeMap<>();
        assignment.put("x", 2.0);
        assignment.put("y", 12.0);
        assignment.put("z", 2.0);
        Expression e, f, g;

        e = new Plus("x", new Plus("y", "z"));
        f = new Pow(-6, 0.3);
        g = new Log(5, -5);

        System.out.println(f);
        System.out.println(f.evaluate(assignment));
        System.out.println(g);
        System.out.println(g.evaluate(assignment));

        /*e = new Div(5, 0);
        System.out.println(e);
        System.out.println(e.evaluate(assignment));*/

       /* e = new Pow("e", "x");
        System.out.println(e);
        System.out.println(e.differentiate("x"));
        System.out.println(e.differentiate("x").simplify());*/

        /*e = new Sin(new Mult(4, "x"));
        System.out.println(e.differentiate("x"));
        System.out.println(e.differentiate("x").simplify());
        System.out.println(e.differentiate("y"));
        System.out.println(e.differentiate("y").simplify());*/


       /* e = new Pow(new Plus(new Var("x"), new Var("y")), new Num(2));
        System.out.println(e.differentiate("x"));
        // the result is:
        // (((x + y) ^ 2.0) * (((1.0 + 0.0) * (2.0 / (x + y))) + (0.0 * log(e, (x + y)))))
        System.out.println(e.differentiate("x").simplify());
        // the result is:
        // (((x + y) ^ 2.0) * (2.0 / (x + y)))

        e = new Pow(new Var("e"), new Var("x"));
        System.out.println(e.differentiate("x"));
        // ((e ^ x) * ((0.0 * (x / e)) + (1.0 * log(e, e))))
        System.out.println(e.differentiate("x").simplify());
        // (e ^ x)*/
    }
}
