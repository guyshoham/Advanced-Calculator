import java.util.Map;
import java.util.TreeMap;

public class Test {

    public static void main(String[] args) throws Exception {
        Map<String, Double> assignment = new TreeMap<>();
        assignment.put("x", 2.0);
        assignment.put("y", 12.0);
        assignment.put("z", 2.0);
        Expression e;

      /*  e = new Plus(new Mult(5, 6), "x");
        System.out.println(e);
        System.out.println(e.simplify());*/

        e = new Pow(new Plus(new Var("x"), new Var("y")), new Num(2));
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
        // (e ^ x)


        /*e = new Plus(new Plus("x", "x"), new Mult(2, "x"));
        //e = new Mult(3, "x");
        System.out.println(e);
        System.out.println(e.isSidesEqual());
*/
    }
}
