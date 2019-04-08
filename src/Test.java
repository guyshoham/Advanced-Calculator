import java.util.Map;
import java.util.TreeMap;

public class Test {

    public static void main(String[] args) throws Exception {
        Map<String, Double> assignment = new TreeMap<>();
        assignment.put("x", 2.0);
        assignment.put("y", 12.0);
        assignment.put("z", 2.0);
        Expression e;

        e = new Num(0);
        System.out.println(e);
        e = new Num(1);
        System.out.println(e);


        /*e = new Plus(new Plus("x", "x"), new Mult(2, "x"));
        //e = new Mult(3, "x");
        System.out.println(e);
        System.out.println(e.isSidesEqual());
*/
    }
}
