import java.util.Map;
import java.util.TreeMap;

public class Test {

    public static void main(String[] args) throws Exception {
        Map<String, Double> assignment = new TreeMap<>();
        assignment.put("x", 2.0);
        assignment.put("y", 12.0);
        assignment.put("z", 2.0);


        //((2x + y) * 4)^x
        Expression e = new Pow(new Mult(new Plus(new Mult(new Num(2), new Var("x")),
                new Var("y")), new Num(4)), new Var("x"));
        /*System.out.println(e);
        System.out.println(e.evaluate(assignment));

        e = new Plus("x", new Div("y", 6));
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

        e = new Neg(5);
        System.out.println(e);
        System.out.println(e.evaluate(assignment));

        e = new Sin(Math.toRadians(90));
        System.out.println(e);
        System.out.println(e.evaluate(assignment));

        e = new Cos(Math.PI);
        System.out.println(e);
        System.out.println(e.evaluate(assignment));

        e = new Pow(new Var("x"), new Num(4));
        Expression de = e.differentiate("x");
        System.out.println(de);*/

        e = new Plus(new Plus("x", "x"), new Mult(2, "x"));
        //e = new Mult(3, "x");
        System.out.println(e);
        System.out.println(e.isSidesEqual());

    }
}
