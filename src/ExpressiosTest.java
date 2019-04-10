import java.util.Map;
import java.util.TreeMap;

/**
 * Class ExpressiosTest.
 */
public class ExpressiosTest {

    private static final double E = 2.71;
    private static final double PI = Math.PI;


    /**
     * this main method test some expression cases
     *
     * @param args ignored
     * @throws Exception exception
     */
    public static void main(String[] args) throws Exception {
        Map<String, Double> assignment = new TreeMap<>();
        assignment.put("x", 2.0);
        assignment.put("y", 0.25);
        assignment.put("e", 2.71);
        Expression e, de;

        //(2x) + (sin(4y)) + (e^x)
        e = new Plus(new Plus(new Mult(2, "x"), new Sin(new Mult(4, "y"))), new Pow("e", "x"));
        System.out.println(e);
        System.out.println(e.evaluate(assignment));
        de = e.differentiate("x");
        System.out.println(de);
        System.out.println(de.evaluate(assignment));
        System.out.println(de.simplify());
    }

}
